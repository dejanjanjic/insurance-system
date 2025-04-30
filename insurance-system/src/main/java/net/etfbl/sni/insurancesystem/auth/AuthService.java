package net.etfbl.sni.insurancesystem.auth;

import jakarta.transaction.Transactional;
import net.etfbl.sni.insurancesystem.config.JwtService;
import net.etfbl.sni.insurancesystem.dtos.*;
import net.etfbl.sni.insurancesystem.exception.EmailNotFoundException;
import net.etfbl.sni.insurancesystem.exception.WrongActivationCodeException;
import net.etfbl.sni.insurancesystem.exception.WrongPasswordException;
import net.etfbl.sni.insurancesystem.model.User;
import net.etfbl.sni.insurancesystem.repository.UserRepository;
import net.etfbl.sni.insurancesystem.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository, EmailService emailService, JwtService jwtService, AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    public RegisterResponseDTO login(LoginRequestDTO request) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User doesn't exist.");
        } catch (BadCredentialsException e) {
            throw new WrongPasswordException("Wrong password.");
        } catch (Exception e) {
            System.err.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Authentication failed.", e);
        }
        User user = userRepository
                .findByUsername(request.getUsername())
                .orElse(null);

        String activationCode = UUID.randomUUID().toString().substring(0, 5);
        assert user != null;
        user.setActivationCode(activationCode);
        userRepository.save(user);

        String emailTo = user.getMail();
        emailService.sendActivationEmail(emailTo, activationCode);

        return new RegisterResponseDTO(user);
    }

    @Transactional
    public LoginResponseDTO verifyCode(VerificationCodeDTO verificationCodeDTO){
        User user = userRepository
                .findByMail(verificationCodeDTO.getMail())
                .orElseThrow(() -> new EmailNotFoundException("Email isn't associated with any account."));

        if(!verificationCodeDTO.getVerificationCode().equals(user.getActivationCode())){
            throw new WrongActivationCodeException("Activation codes don't match");
        }

        user.setActivationCode(null);
        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new LoginResponseDTO(token);
    }
}

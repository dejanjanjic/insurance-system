package net.etfbl.sni.insurancesystem.service;

import net.etfbl.sni.insurancesystem.dtos.RegisterResponseDTO;
import net.etfbl.sni.insurancesystem.enums.Role;
import net.etfbl.sni.insurancesystem.exception.EmailAlreadyExistsException;
import net.etfbl.sni.insurancesystem.exception.UsernameAlreadyExistsException;
import net.etfbl.sni.insurancesystem.model.User;
import net.etfbl.sni.insurancesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public RegisterResponseDTO register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username is already in use.");
        }
        if(userRepository.findByMail(user.getMail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email is already in use");
        }
        user.setRole(Role.ROLE_CLIENT);
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return new RegisterResponseDTO(userRepository.save(user));
    }
}

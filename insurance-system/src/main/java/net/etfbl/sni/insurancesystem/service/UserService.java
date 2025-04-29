package net.etfbl.sni.insurancesystem.service;

import net.etfbl.sni.insurancesystem.exception.UserAlreadyExistsException;
import net.etfbl.sni.insurancesystem.model.User;
import net.etfbl.sni.insurancesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent() ||
                userRepository.findByMail(user.getMail()).isPresent()) {
            throw new UserAlreadyExistsException("User with username \"" + user.getUsername() + "\" already exists.");
        }
        return userRepository.save(user);
    }
}

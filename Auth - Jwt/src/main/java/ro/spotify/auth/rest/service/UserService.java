package ro.spotify.auth.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.spotify.auth.model.user.Role;
import ro.spotify.auth.model.user.User;
import ro.spotify.auth.rest.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    private void saveUser(User user){
        userRepository.save(user);
    }

    public void createUserDB(){

        Role userRole = roleService.getRoleById(2);
        var user = User.builder()
                .lastName("artist")
                .firstName("artist")
                .email("artist")
                .password(passwordEncoder.encode("artist"))
                .createLocalDateTime(LocalDateTime.now())
                .role(userRole)
                .build();
        saveUser(user);

        userRole = roleService.getRoleById(1);
        user = User.builder()
                .lastName("user")
                .firstName("user")
                .email("user")
                .password(passwordEncoder.encode("user"))
                .createLocalDateTime(LocalDateTime.now())
                .role(userRole)
                .build();
        saveUser(user);

    }

}
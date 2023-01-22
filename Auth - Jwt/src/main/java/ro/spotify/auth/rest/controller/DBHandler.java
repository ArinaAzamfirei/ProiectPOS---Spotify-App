package ro.spotify.auth.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.spotify.auth.rest.service.RoleService;
import ro.spotify.auth.rest.service.UserService;

@Component
@RequiredArgsConstructor
public class DBHandler implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        roleService.createRoleDB();
        userService.createUserDB();
    }
}

package ro.spotify.auth.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.spotify.auth.model.user.Role;
import ro.spotify.auth.rest.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRoleById(Integer roleID){
        return roleRepository.findById(roleID).orElseThrow(); //TODO: create exception if the role doesnt exist
    }

    private void createRole(Role role){
        roleRepository.save(role);
    }

    public void createRoleDB(){
        Role roleResult;
        roleResult = Role.builder()
                .type("USER")
                .build();
        createRole(roleResult);

        roleResult = Role.builder()
                .type("ARTIST")
                .build();
        createRole(roleResult);
    }

}
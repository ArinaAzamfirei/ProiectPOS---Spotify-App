package ro.spotify.auth.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.spotify.auth.model.user.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}

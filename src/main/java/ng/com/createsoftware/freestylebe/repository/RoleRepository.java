package ng.com.createsoftware.freestylebe.repository;

import ng.com.createsoftware.freestylebe.model.ERole;
import ng.com.createsoftware.freestylebe.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(ERole name);
}

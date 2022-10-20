package ng.com.createsoftware.freestylebe.repository;

import ng.com.createsoftware.freestylebe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByStagename(String stagename);

     

    Boolean existsByStagename(String stagename);

    Boolean existsByEmail(String email);
}

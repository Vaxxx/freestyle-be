package ng.com.createsoftware.freestylebe.repository;

import ng.com.createsoftware.freestylebe.model.Genre;
import ng.com.createsoftware.freestylebe.model.SecModel;
import ng.com.createsoftware.freestylebe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecModelRepository extends JpaRepository<SecModel, Long> {
    Optional<SecModel> findByUser(User user);
}

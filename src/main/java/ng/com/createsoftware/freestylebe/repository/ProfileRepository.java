package ng.com.createsoftware.freestylebe.repository;

import ng.com.createsoftware.freestylebe.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    
}

package ng.com.createsoftware.freestylebe.repository;

import ng.com.createsoftware.freestylebe.model.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Long>{
    
}

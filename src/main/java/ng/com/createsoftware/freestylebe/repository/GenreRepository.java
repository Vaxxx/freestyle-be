package ng.com.createsoftware.freestylebe.repository;

import ng.com.createsoftware.freestylebe.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long>{
    
}

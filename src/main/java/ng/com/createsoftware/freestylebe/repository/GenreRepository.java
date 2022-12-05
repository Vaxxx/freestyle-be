package ng.com.createsoftware.freestylebe.repository;

import ng.com.createsoftware.freestylebe.model.Genre;
import ng.com.createsoftware.freestylebe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long>{
    List<Genre> findAllByUser(User user);

    Optional<Genre> findByUser(User user);
}

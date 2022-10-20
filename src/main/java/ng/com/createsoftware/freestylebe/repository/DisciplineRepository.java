package ng.com.createsoftware.freestylebe.repository;

import ng.com.createsoftware.freestylebe.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Long>{
    
}

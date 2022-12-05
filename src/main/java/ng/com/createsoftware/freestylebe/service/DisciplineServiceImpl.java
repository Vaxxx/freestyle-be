package ng.com.createsoftware.freestylebe.service;

import ng.com.createsoftware.freestylebe.model.Discipline;
import ng.com.createsoftware.freestylebe.model.User;
import ng.com.createsoftware.freestylebe.repository.DisciplineRepository;
import ng.com.createsoftware.freestylebe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplineServiceImpl implements DisciplineService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Override
    public Discipline getDisciplineByUser(Long userId) {
        User user = userRepository.findById(userId).get();
        return disciplineRepository.findByUser(user).orElseThrow(() ->
                new IllegalArgumentException("User cannot be found"));
    }

    @Override
    public Discipline editDiscipline(Discipline discipline, Long userId) {
        Discipline disciplineToEdit = getDisciplineByUser(userId);

        disciplineToEdit.setName(discipline.getName());
        return disciplineRepository.save(disciplineToEdit);
    }
}

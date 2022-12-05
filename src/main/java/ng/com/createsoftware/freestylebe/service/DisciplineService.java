package ng.com.createsoftware.freestylebe.service;

import ng.com.createsoftware.freestylebe.model.Discipline;

public interface DisciplineService {

    Discipline getDisciplineByUser(Long userId);

    //edit profile
    Discipline editDiscipline(Discipline discipline, Long userId) ;
}

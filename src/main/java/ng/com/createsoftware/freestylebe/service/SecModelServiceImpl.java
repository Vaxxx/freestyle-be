package ng.com.createsoftware.freestylebe.service;

import ng.com.createsoftware.freestylebe.model.Genre;
import ng.com.createsoftware.freestylebe.model.SecModel;
import ng.com.createsoftware.freestylebe.model.User;
import ng.com.createsoftware.freestylebe.repository.SecModelRepository;
import ng.com.createsoftware.freestylebe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SecModelServiceImpl implements SecModelService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecModelRepository secModelRepository;


    @Override
    public SecModel getSecModelByUser(Long userId) {
        User user = userRepository.findById(userId).get();
        return secModelRepository.findByUser(user).orElseThrow(() ->
                new IllegalArgumentException("User cannot be found"));
    }

    @Override
    public SecModel editSecModel(SecModel secModel, Long userId) {
        SecModel secModelToEdit = getSecModelByUser(userId);

        secModelToEdit.setQuestion(secModel.getQuestion());
        secModelToEdit.setAnswer(secModel.getAnswer());
        return secModelRepository.save(secModelToEdit);
    }
}

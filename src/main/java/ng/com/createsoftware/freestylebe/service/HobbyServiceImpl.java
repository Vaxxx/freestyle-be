package ng.com.createsoftware.freestylebe.service;

import ng.com.createsoftware.freestylebe.model.Hobby;
import ng.com.createsoftware.freestylebe.model.User;
import ng.com.createsoftware.freestylebe.repository.HobbyRepository;
import ng.com.createsoftware.freestylebe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HobbyServiceImpl implements HobbyService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    @Override
    public Hobby getHobbyByUser(Long userId) {
        User user = userRepository.findById(userId).get();
        return hobbyRepository.findByUser(user).orElseThrow(() ->
                new IllegalArgumentException("User cannot be found"));
    }

    @Override
    public Hobby editHobby(Hobby hobby, Long userId) {
        Hobby hobbyToEdit = getHobbyByUser(userId);

        hobbyToEdit.setName(hobby.getName());
        return hobbyRepository.save(hobbyToEdit);
    }
}

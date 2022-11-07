package ng.com.createsoftware.freestylebe.service;

import ng.com.createsoftware.freestylebe.model.User;
import ng.com.createsoftware.freestylebe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> users = StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
          return users;

    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new  UsernameNotFoundException("No User found with  ID: " + id));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User editUser(Long userId, User user) {
       User editedUser = getUserById(userId);
       editedUser.setStagename(user.getStagename());
       editedUser.setEmail(user.getEmail());
       editedUser.setAge(user.getAge());
       userRepository.save(editedUser);
       return editedUser;
    }
}

package ng.com.createsoftware.freestylebe.service;

import ng.com.createsoftware.freestylebe.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUser(Long id);
    User editUser(Long userId, User user);

    Boolean existsByStagename(String stagename);
}

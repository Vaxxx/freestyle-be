package ng.com.createsoftware.freestylebe.controller;


import lombok.AllArgsConstructor;
import ng.com.createsoftware.freestylebe.model.User;
import ng.com.createsoftware.freestylebe.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

     private UserService userService;

     @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @PostMapping("/editUser/{id}")
    public ResponseEntity<User> editUser(@PathVariable final Long id, @RequestBody User user){
         User editedUser = userService.editUser(id, user);
         return new ResponseEntity<>(editedUser, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}

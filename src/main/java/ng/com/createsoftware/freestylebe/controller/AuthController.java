package ng.com.createsoftware.freestylebe.controller;

import ng.com.createsoftware.freestylebe.dto.request.LoginRequest;
import ng.com.createsoftware.freestylebe.dto.request.RegisterRequest;
import ng.com.createsoftware.freestylebe.dto.response.JwtResponse;
import ng.com.createsoftware.freestylebe.dto.response.MessageResponse;
import ng.com.createsoftware.freestylebe.model.ERole;
import ng.com.createsoftware.freestylebe.model.Role;
import ng.com.createsoftware.freestylebe.model.User;
import ng.com.createsoftware.freestylebe.repository.RoleRepository;
import ng.com.createsoftware.freestylebe.repository.UserRepository;
import ng.com.createsoftware.freestylebe.security.jwt.JwtUtils;
import ng.com.createsoftware.freestylebe.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    

    @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;


  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getStagename(), loginRequest.getPassword() )
    );

     SecurityContextHolder.getContext().setAuthentication(authentication);
     String jwt = jwtUtils.generateJwtToken(authentication);

     UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
     List<String>roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

       return ResponseEntity.ok(new JwtResponse(jwt, 
                  userDetails.getId(),
                  userDetails.getUsername(),
                  userDetails.getEmail(),
                  roles
           ));     

      }


      @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
    if (userRepository.existsByStagename(registerRequest.getStagename())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Stage Name is already taken!"));
    }

    if (userRepository.existsByEmail(registerRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email Address is already in use!"));
    }

    // Create new user's account
    // User user = new User(registerRequest.getStagename(), 
    //          registerRequest.getEmail(),
    //            encoder.encode(registerRequest.getPassword()));

    User user = new User(registerRequest.getFirstname(), registerRequest.getLastname(),registerRequest.getStagename(), 
    registerRequest.getEmail(),
      encoder.encode(registerRequest.getPassword()));


    List<String> strRoles = registerRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "editor":
          Role editorRole = roleRepository.findByName(ERole.ROLE_EDITOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(editorRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

}

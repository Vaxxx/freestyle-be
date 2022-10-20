package ng.com.createsoftware.freestylebe.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/page")
public class HomeController {

    @GetMapping("/")
    public String homePage(){
        return "Freestyle League";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
      return  "This is a dashboard";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('EDITOR') or hasRole('ADMIN')")
    public String userAccess() {
      return "User Content.";
    }
  
    @GetMapping("/editor")
    @PreAuthorize("hasRole('EDITOR')")
    public String moderatorAccess() {
      return "EDITOR Board.";
    }
  
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
      return "Admin Board.";
    }

    
}

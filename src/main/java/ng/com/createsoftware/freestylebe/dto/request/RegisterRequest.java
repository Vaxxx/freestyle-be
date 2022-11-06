package ng.com.createsoftware.freestylebe.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class RegisterRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String stagename;

    @NotBlank
    private String age;
  
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
  
    private List<String> role;
  
    @NotBlank 
    private String password;
  
}

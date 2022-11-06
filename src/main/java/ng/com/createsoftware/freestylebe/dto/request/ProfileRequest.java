package ng.com.createsoftware.freestylebe.dto.request;

import lombok.Data;

import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProfileRequest {

    private Long id;
    private String firstname;
    private String lastname;
    private String phone;
    private String city;
    private String country;
    private String bio;
    private List<String> genre = new ArrayList<>();
    private List<String> hobby = new ArrayList<>();
    private List<String> discipline = new ArrayList<>();
    @Lob 
    private byte[] picture;
    @Lob 
    private byte[] banner;

    private String question;
    private String answer;
    
     
    private Long user_id;

}

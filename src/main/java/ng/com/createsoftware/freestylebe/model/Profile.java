package ng.com.createsoftware.freestylebe.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="profile")
@NoArgsConstructor
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;


    @NotBlank
    @Size(max = 20)
    private String firstname;

    @NotBlank
    @Size(max = 20)
    private String lastname;

    @NotBlank
    @Size(min=10)
    private String phone;


    private String city;
    private String country;

    private String bio;

    private String pictureName;
    private String bannerName;

//    private String picture;
//    private String banner;
    @Lob
    private byte[] picturez;
    @Lob
    private byte[] bannerz;

    public Profile(User user, String city, String country, String bio) {
        this.user = user;
        this.city = city;
        this.country = country;
        this.bio = bio;
    }

//    public Profile(User user, String firstname, String lastname, String phone,
//                   String city, String country, String bio, String picture,
//                   String banner) {
//        this.user = user;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.phone = phone;
//        this.city = city;
//        this.country = country;
//        this.bio = bio;
//        this.picture = picture;
//        this.banner = banner;
//    }


//    public Profile(User user, String firstname, String lastname, String phone, String city, String country, String bio, byte[] picture, byte[] banner) {
//        this.user = user;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.phone = phone;
//        this.city = city;
//        this.country = country;
//        this.bio = bio;
//        this.picturez = picture;
//        this.bannerz = banner;
//    }

    public Profile(User user, String firstname, String lastname, String phone, String city, String country, String bio, String pictureName, String bannerName, byte[] picturez, byte[] bannerz) {
        this.user = user;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.city = city;
        this.country = country;
        this.bio = bio;
        this.pictureName = pictureName;
        this.bannerName = bannerName;
        this.picturez = picturez;
        this.bannerz = bannerz;
    }
}

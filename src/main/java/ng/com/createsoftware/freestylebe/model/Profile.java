package ng.com.createsoftware.freestylebe.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="profile")
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;

    private String city;
    private String country;
    private String bio;
   
    @OneToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Genre> genres = new ArrayList<>();

    @OneToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Hobby> hobbies = new ArrayList<>();

    @OneToMany(cascade= CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Discipline> disciplines = new ArrayList<>();

    @Lob 
    private byte[] picture;
    @Lob 
    private byte[] banner;

    public Profile(User user, String city, String country, String bio) {
        this.user = user;
        this.city = city;
        this.country = country;
        this.bio = bio;
    }
}

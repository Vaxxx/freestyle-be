package ng.com.createsoftware.freestylebe.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Table(name="testimage")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String phone;

//    @Lob
//    private byte[] photos;

    private String banner;
    private String picture;

//    public TestImage(String title, String phone, byte[] photos) {
//        this.title = title;
//        this.phone = phone;
//        this.photos = photos;
//    }
    public TestImage(String title, String phone,String banner, String picture) {
        this.title = title;
        this.phone = phone;
        this.banner = banner;
        this.picture = picture;
    }
}


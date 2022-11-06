package ng.com.createsoftware.freestylebe.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "hobbies")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    @NotBlank
    @Size(min=3, max=40)
    private String name;

    public Hobby(@NotBlank @Size(min = 3, max = 40) String name) {
        this.name = name;
    }

    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    public Hobby(@NotBlank @Size(min = 3, max = 40) String name, User user) {
        this.name = name;
        this.user = user;
    }

    
}

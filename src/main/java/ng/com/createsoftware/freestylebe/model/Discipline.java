package ng.com.createsoftware.freestylebe.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "discipline")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    @NotBlank
    @Size(min=3, max=40)
    private String name;

    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    public Discipline(String name) {
        this.name = name;
    }

    public Discipline(@NotBlank @Size(min = 3, max = 40) String name, User user) {
        this.name = name;
        this.user = user;
    }

    
}

package ng.com.createsoftware.freestylebe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "rolez")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
  
    public Role(ERole name) {
        this.name = name;
      }
}

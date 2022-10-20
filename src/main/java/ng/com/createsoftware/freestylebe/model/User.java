package ng.com.createsoftware.freestylebe.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name="users")
@Entity
@Data
@NoArgsConstructor
public class User {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String firstname;

  @NotBlank
  @Size(max = 20)
  private String lastname;

  @NotBlank
  @Size(max = 20)
  @Column(unique=true)
  private String stagename;

  @NotBlank
  @Email
  private String email;

  @NotBlank 
  private String password;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinTable(
          name = "roles_users",
          joinColumns = {@JoinColumn(name = "users_id")},
          inverseJoinColumns = {@JoinColumn(name = "roles_id")}
  )
	private Set<Role> roles = new HashSet<>();

  @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public User(@NotBlank @Size(max = 20) String stagename, @NotBlank @Email String email,
        @NotBlank @Size(max = 25) String password) {
      this.stagename = stagename;
      this.email = email;
      this.password = password;
    }

    public User(@NotBlank @Size(max = 20) String firstname, @NotBlank @Size(max = 20) String lastname,
        @NotBlank @Size(max = 20) String stagename, @NotBlank @Email String email, @NotBlank String password) {
      this.firstname = firstname;
      this.lastname = lastname;
      this.stagename = stagename;
      this.email = email;
      this.password = password;
    }

 
    
    


}

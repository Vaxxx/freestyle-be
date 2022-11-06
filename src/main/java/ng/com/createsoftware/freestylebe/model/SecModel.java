package ng.com.createsoftware.freestylebe.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name="SecurityDb")
@NoArgsConstructor
public class SecModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;


    @NotBlank
    @Size(min = 2)
    private String question;

    @NotBlank
    @Size(min = 2)
    private String answer;

    public SecModel(User user, String question, String answer) {
        this.user = user;
        this.question = question;
        this.answer = answer;
    }
    public SecModel( String question, String answer) {

        this.question = question;
        this.answer = answer;
    }


}

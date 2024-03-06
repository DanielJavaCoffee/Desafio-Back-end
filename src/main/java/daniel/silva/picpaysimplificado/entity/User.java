package daniel.silva.picpaysimplificado.entity;

import daniel.silva.picpaysimplificado.enuns.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter()
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @CPF
    @Column(unique = true)
    private String CPF;
    @Email
    @Column(length = 150, unique = true)
    private String email;
    @Column(length = 250)
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private BigDecimal balance;
}

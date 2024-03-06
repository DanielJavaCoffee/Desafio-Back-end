package daniel.silva.picpaysimplificado.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "payer_id", nullable = false)
    private User payer;
    @ManyToOne
    @JoinColumn(name = "payee_id", nullable = false)
    private User payee;
    @Column(nullable = false)
    private LocalDateTime dateTransaction;

}

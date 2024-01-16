package sapient.bank.customer.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Transactions")
public class Transaction {
    @Column
    @Id@GeneratedValue
    private Integer transactionId;
    @Column
    private String customerEmail;
    @Column
    private String transactionType;
    @Column
    private float transactionAmt;
}

package sapient.bank.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {
    private String customerEmail;
    private String customerAadhar;
    private String customerName;
    private String customerPhone;
    private float balance = 0.0f;
}

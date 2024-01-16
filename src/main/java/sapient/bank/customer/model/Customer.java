package sapient.bank.customer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
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
@Entity
@Table(name="Customer")
public class Customer {
    @Column
    @Id
    @NotEmpty @Email(message = "please enter valid email id!")
    private String customerEmail;
    @Column
    @NotEmpty
    @Size(min = 14, message="Enter 14 digit aadhar number!")
    private String customerAadhar;
    @Column
    @NotEmpty
    private String customerName;
    @Column
    @NotEmpty
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$")
    private String customerPassword;
    @Column
    private String customerAddress;
    @Column
    private String customerGender;
    @Column
    @NotEmpty
    @Size(min = 10, message = "enter 10 digit mobile number")
    private String customerPhone;
    @Column
    private float balance = 0.0f;
}

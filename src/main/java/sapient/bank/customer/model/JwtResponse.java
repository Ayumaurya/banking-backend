package sapient.bank.customer.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {
    private String jwtToken;
    private String username;
    private String customerName;
    private String customerPhone;
    private String customerAadhar;
    private float balance;
}

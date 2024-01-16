package sapient.bank.customer.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Setter
@Getter
public class ControllerException {
    String error_code;
    String error_message;

    public ControllerException(String error_code, String error_message) {
        this.error_code = error_code;
        this.error_message = error_message;
    }

    public ControllerException() {
    }
}

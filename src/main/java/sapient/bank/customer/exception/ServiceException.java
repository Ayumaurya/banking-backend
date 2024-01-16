package sapient.bank.customer.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ServiceException extends RuntimeException {
    String error_code;
    String error_message;

    public ServiceException(String error_code, String error_message) {
        this.error_code = error_code;
        this.error_message = error_message;
    }

    public ServiceException() {
    }
}

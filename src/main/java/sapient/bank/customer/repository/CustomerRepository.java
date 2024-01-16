package sapient.bank.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sapient.bank.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findBycustomerEmail(String email);
}

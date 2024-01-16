package sapient.bank.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sapient.bank.customer.model.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllBycustomerEmail(String email);
}

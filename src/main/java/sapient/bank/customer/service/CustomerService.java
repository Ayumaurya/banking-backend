package sapient.bank.customer.service;

import org.springframework.http.ResponseEntity;
import sapient.bank.customer.model.Customer;
import sapient.bank.customer.model.Transaction;

import java.util.List;

public interface CustomerService {
    public Customer register(Customer customer);
    public Customer login(String email, String password);
    public Customer deposit(String email, float depositAmt);
    public Customer withdraw(String email, float withdrawAmt);

//    public List<Customer> allCustomer();

//    public Customer getCustomerById(String email);
    public String applyLoan(Customer customer, String loantype, String loanAmt);
    public String applyCC(Customer customer);
    public List<Transaction> getTransaction(String email);
}

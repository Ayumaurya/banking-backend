package sapient.bank.customer.dao;

import sapient.bank.customer.model.Customer;

import java.util.List;

public interface CustomerDao {
    public Customer deposit(Customer c, float depositAmt);
    public Customer withdrawal(Customer c, float withdrawAmt);
    public String applyLoan(Customer c, float loanAmt, String loanType);
    public String applyCC(Customer c, List<String> transaction);
}

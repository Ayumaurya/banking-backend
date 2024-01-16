package sapient.bank.customer.dao;

import org.springframework.stereotype.Component;
import sapient.bank.customer.model.Customer;

import java.util.List;

@Component
public class CustomerDao_Impl implements CustomerDao{
    @Override
    public Customer deposit(Customer c, float depositAmt) {
        //write your code here
        c.setBalance(c.getBalance()+depositAmt);
        return c;
    }

    @Override
    public Customer withdrawal(Customer c, float withdrawAmt) {
        //write your code here

        if (withdrawAmt > 0) {
                if (c.getBalance() - withdrawAmt >= 0) {
                    c.setBalance(c.getBalance()-withdrawAmt);
                    return c;
                } else return null;
            }
        else {
            return null;
        }
    }

    @Override
    public String applyLoan(Customer c, float loanAmt, String loanType) {
        //write your code here
        return "Congratulations! You are eligible for loan,";
    }

    @Override
    public String applyCC(Customer c, List<String> transaction) {
        // write your code here
        return "Yay!! You are eligible for Credit Card.";
    }
}

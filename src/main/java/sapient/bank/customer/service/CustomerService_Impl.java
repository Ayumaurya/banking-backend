package sapient.bank.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapient.bank.customer.dao.CustomerDao;
import sapient.bank.customer.exception.ServiceException;
import sapient.bank.customer.model.Customer;
import sapient.bank.customer.model.Transaction;
import sapient.bank.customer.repository.CustomerRepository;
import sapient.bank.customer.repository.TransactionRepository;

import java.util.List;

@Service
public class CustomerService_Impl implements CustomerService{
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    CustomerDao customerDao;
    @Autowired
    TransactionRepository transactionRepo;
    @Override
    public Customer register(Customer customer) {
        try{
            return customerRepo.save(customer);
        }catch(IllegalAccessError e){
            throw new ServiceException("602","fill the correct credentials");
        }catch(Exception e){
            throw new ServiceException("601", "OOPS! Something went wrong.");
        }
    }

    @Override
    public Customer login(String email, String password) {
        Customer c = customerRepo.findBycustomerEmail(email);
        if(c==null) return null;
        return c.getCustomerPassword().equals(password) ? c:null;
    }

    @Override
    public Customer deposit(String email, float depositAmt) {
        Customer customer = customerRepo.findBycustomerEmail(email);
        Customer c = customerDao.deposit(customer, depositAmt);
        Transaction t = new Transaction();
        t.setCustomerEmail(c.getCustomerEmail());
        t.setTransactionAmt(depositAmt);
        t.setTransactionType("deposit");
        transactionRepo.save(t);
        return customerRepo.save(c);
    }

    @Override
    public Customer withdraw(String email, float withdrawAmt) {
        Customer customer = customerRepo.findBycustomerEmail(email);
        Customer c = customerDao.withdrawal(customer, withdrawAmt);

        Transaction t = new Transaction();
        t.setCustomerEmail(c.getCustomerEmail());
        t.setTransactionAmt(withdrawAmt);
        t.setTransactionType("withdraw");
        transactionRepo.save(t);
        return customerRepo.save(c);
    }

//    @Override
//    public List<Customer> allCustomer() {
//        return customerRepo.findAll();
//    }

//    @Override
//    public Customer getCustomerById(String email) {
//        return null;
//    }

    @Override
    public String applyLoan(Customer customer, String loanType, String loanAmt) {
        float amt = Float.parseFloat(loanAmt);
        try{
            return customerDao.applyLoan(customer, amt, loanType);
        }catch(Exception e){
            throw new ServiceException("601", "OOPS! Something went wrong.");
        }

    }

    @Override
    public String applyCC(Customer customer) {
        try{
            return customerDao.applyCC(customer,null);
        }catch(Exception e){
            throw new ServiceException("601", "OOPS! Something went wrong in service layer.");
        }
    }

    @Override
    public List<Transaction> getTransaction(String email){
        try{
            return transactionRepo.findAllBycustomerEmail(email);
        }catch(IllegalArgumentException e){
            throw new ServiceException("601", "OOPS! nothing to fetch.");
        }
        catch(Exception e){
            throw new ServiceException("601","OOPS! Something went wrong in service layer.");
        }
    }
}

package sapient.bank.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sapient.bank.customer.exception.ServiceException;
import sapient.bank.customer.model.Customer;
import sapient.bank.customer.model.User;
import sapient.bank.customer.repository.CustomerRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // load user from database
        Customer customer = null;
        User user = new User();
        try{
            customer = customerRepo.findBycustomerEmail(username);
            if(customer == null){
                throw new ServiceException("801", "User Not Found!");
            }
            else{
                user.setUsername(customer.getCustomerEmail());
                user.setPassword(passwordEncoder.encode(customer.getCustomerPassword()));
                user.setCustomerAadhar(customer.getCustomerAadhar());
                user.setCustomerName(customer.getCustomerName());
                user.setCustomerPhone(customer.getCustomerPhone());
                user.setBalance(customer.getBalance());
            }
        }catch(Exception e){
            throw new ServiceException("701", "Error");
        }
        return user;
    }
}

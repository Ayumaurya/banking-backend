package sapient.bank.customer.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapient.bank.customer.exception.ControllerException;
import sapient.bank.customer.exception.ServiceException;
import sapient.bank.customer.model.Customer;
import sapient.bank.customer.model.Transaction;
import sapient.bank.customer.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000/")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    Customer c;
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Customer c){
        try{
            return new ResponseEntity<Customer>(customerService.register(c), HttpStatus.OK);
        }catch(ServiceException e){
            return new ResponseEntity<ControllerException>(new ControllerException(e.getError_code(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity<ControllerException>(new ControllerException("701", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/login/{credentials}")
    public Customer login(@PathVariable String credentials){
        String[] arrOfStr = credentials.split("&", 2);
        return customerService.login(arrOfStr[0],arrOfStr[1]);
    }
    @PutMapping("/deposit/{email}/{depositAmt}")
    public Customer deposit(@PathVariable String email, @PathVariable String depositAmt){
        float amt = Float.parseFloat(depositAmt);
        return customerService.deposit(email, amt);
    }
    @PutMapping("/withdraw/{email}/{withdrawAmt}")
    public Customer withdraw(@PathVariable String email, @PathVariable String withdrawAmt,@RequestBody Customer c){
        float amt = Float.parseFloat(withdrawAmt);
        return customerService.withdraw(email, amt);
    }
    @GetMapping("/applyLoan/{loanType}/{loanAmt}")
    public ResponseEntity<?> applyLoan(@PathVariable String loanType, @PathVariable String loanAmt, @RequestBody Customer c){
        try{
            String result = customerService.applyLoan(c, loanType, loanAmt);
            return new ResponseEntity<String>(result, HttpStatus.OK);
        }catch (ServiceException e){
            ControllerException ce = new ControllerException(e.getError_code(), e.getError_message());
            return new  ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity<ControllerException>(new ControllerException("701",e.getMessage()),HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/applyCC")
    public ResponseEntity<?> applyCC(@RequestBody Customer c){
        try{
            return new ResponseEntity<String>(customerService.applyCC(c), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/transactions/{customerEmail}")
    public ResponseEntity<?> getTransactions(@PathVariable ("customerEmail") String email){
        try{
            return new ResponseEntity<List<Transaction>>(customerService.getTransaction(email), HttpStatus.OK);
        }
        catch(ServiceException e){
            return new ResponseEntity<ControllerException>(new ControllerException(e.getError_code(),e.getError_message()),HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}

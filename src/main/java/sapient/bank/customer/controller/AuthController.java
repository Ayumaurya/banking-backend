package sapient.bank.customer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import sapient.bank.customer.model.Customer;
import sapient.bank.customer.model.JwtRequest;
import sapient.bank.customer.model.JwtResponse;
import sapient.bank.customer.model.User;
import sapient.bank.customer.security.JwtHelper;
import sapient.bank.customer.service.CustomerService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000/")
public class AuthController {

        @Autowired
        private UserDetailsService userDetailsService;

        @Autowired
        private AuthenticationManager manager;


        @Autowired
        private JwtHelper helper;
        @Autowired
        private CustomerService customerService;

        private Logger logger = LoggerFactory.getLogger(AuthController.class);


        @PostMapping("/login")
        public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

            this.doAuthenticate(request.getUsername(), request.getPassword());


            User user = (User) userDetailsService.loadUserByUsername(request.getUsername());
            String token = this.helper.generateToken(user);

            JwtResponse response = JwtResponse.builder()
                    .jwtToken(token)
                    .username(user.getUsername())
                    .customerAadhar(user.getCustomerAadhar())
                    .customerName(user.getCustomerName())
                    .customerPhone(user.getCustomerPhone())
                    .balance(user.getBalance()).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        private void doAuthenticate(String email, String password) {

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
            try {
                manager.authenticate(authentication);


            } catch (BadCredentialsException e) {
                throw new BadCredentialsException(" Invalid Username or Password  !!");
            }

        }

        @ExceptionHandler(BadCredentialsException.class)
        public String exceptionHandler() {
            return "Credentials Invalid !!";
        }

        @PostMapping("/create-user")
        public Customer createUser(@RequestBody Customer customer){
            return customerService.register(customer);
        }
}

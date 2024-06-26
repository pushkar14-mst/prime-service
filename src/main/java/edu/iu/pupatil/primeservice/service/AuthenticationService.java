package edu.iu.pupatil.primeservice.service;

import edu.iu.pupatil.primeservice.model.Customer;
import edu.iu.pupatil.primeservice.repository.AuthenticationDBRepository;
import edu.iu.pupatil.primeservice.repository.IAuthenticationRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthenticationService implements IAuthenticationService, UserDetailsService {
    AuthenticationDBRepository authenticationRepository;

    public AuthenticationService(AuthenticationDBRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public boolean register(Customer customer) throws IOException {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String passwordEncoded = bcrypt.encode(customer.getPassword());
        customer.setPassword(passwordEncoded);
        authenticationRepository.save(customer);
        return true;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Customer customer = authenticationRepository.findByUsername(username);
            if (customer == null) {
                throw new UsernameNotFoundException("");
            }
            return User.withUsername(username).password(customer.getPassword()).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

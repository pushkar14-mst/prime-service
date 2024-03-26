package edu.iu.pupatil.primeservice.controller;

import edu.iu.pupatil.primeservice.model.Customer;
import edu.iu.pupatil.primeservice.service.IAuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthenticationController {
    private final IAuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationController(AuthenticationManager authenticationManager,IAuthenticationService authenticationService){
        this.authenticationManager=authenticationManager;
        this.authenticationService=authenticationService;
    }
    @PostMapping("/register")
    public boolean register(@RequestBody Customer customer){
        try {
            return authenticationService.register(customer);
        }
        catch (IOException e){
            throw new RuntimeException((e));
        }
    }
    @PostMapping("/login")
    public String login(@RequestBody Customer customer){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customer.getUsername(),customer.getPassword()));
        return "Success!";
    }
}

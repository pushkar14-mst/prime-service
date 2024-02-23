package edu.iu.pupatil.primeservice.service;

import org.springframework.stereotype.Service;

@Service
public class PrimeService implements IPrimeService {
    public boolean isPrime(long number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}

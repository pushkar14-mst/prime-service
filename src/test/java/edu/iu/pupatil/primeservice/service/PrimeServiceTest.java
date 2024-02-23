package edu.iu.pupatil.primeservice.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimeServiceTest {
    PrimeService primeService = new PrimeService();
    @Test
    void _45_is_not_prime() {
        int number = 45;
        boolean expected = false;
        boolean actual = primeService.isPrime(number);
        assertEquals(expected, actual);
    }
    @Test
    void _13_is_prime() {
        int number = 2;
        boolean expected = true;
        boolean actual = primeService.isPrime(number);
        assertEquals(expected, actual);
    }
    @Test
    void _7_is_prime() {
        long number = 7;
        boolean expected = true;
        boolean actual = primeService.isPrime(number);
        assertEquals(expected, actual);
    }

}
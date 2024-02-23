package edu.iu.pupatil.primeservice.controller;

import edu.iu.pupatil.primeservice.service.IPrimeService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/prime")
public class PrimesController {
    IPrimeService _primeService;
    public PrimesController(IPrimeService primeService) {
        this._primeService = primeService;
    }
    @GetMapping("/{n}")
    public boolean isPrime(@PathVariable long n) {
        return _primeService.isPrime(n);
    }
}

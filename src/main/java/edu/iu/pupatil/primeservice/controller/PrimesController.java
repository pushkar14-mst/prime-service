package edu.iu.pupatil.primeservice.controller;

import edu.iu.pupatil.primeservice.rabbitmq.MQSender;
import edu.iu.pupatil.primeservice.service.IPrimeService;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin("*")
@RequestMapping("/prime")
public class PrimesController {
    IPrimeService _primeService;
    private final MQSender mqSender;
    public PrimesController(IPrimeService primeService, MQSender mqSender) {
        this._primeService = primeService;
        this.mqSender = mqSender;
    }

    @GetMapping("/{n}")
    public boolean isPrime(@PathVariable long n) {
        boolean result= _primeService.isPrime(n);
        mqSender.sendMessage(n, result);
        return result;
    }
}
package com.register.registration.Controller;

import com.register.registration.Service.ServiceImpl.PaymentService;
import com.register.registration.dto.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest) {
        String paymentResult = paymentService.processPayment(paymentRequest.getUserId(), paymentRequest.getBillId());
        return ResponseEntity.ok(paymentResult);
    }

}


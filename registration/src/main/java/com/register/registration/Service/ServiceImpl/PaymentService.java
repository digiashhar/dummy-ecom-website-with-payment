package com.register.registration.Service.ServiceImpl;

import com.register.registration.entity.Bill;
import com.register.registration.entity.PaymentHistory;
import com.register.registration.repository.BillRepository;
import com.register.registration.repository.PaymentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// PaymentService
@Service
public class PaymentService {

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;
    @Autowired
    private BillRepository billRepository;

    public String processPayment(Long userId, Long billId) {
        // Check if the provided userId and billId are valid
        // (You may want to perform additional validation based on your requirements)

        // Check if the bill exists
        Optional<Bill> optionalBill = billRepository.findById(billId);
        if (optionalBill.isPresent()) {
            Bill bill = optionalBill.get();

            // Assuming a simple successful payment logic here
            boolean paymentSuccess = performPaymentLogic(userId, bill);

            // Save payment history
            savePaymentHistory(userId, billId, paymentSuccess, bill.getTotalPrice());

            // Return result based on payment success
            if (paymentSuccess) {
                // Include total price in the success message
                return "Payment successful! Total Price: " + bill.getTotalPrice();
            } else {
                return "Payment failed.";
            }
        } else {
            return "Bill not found.";
        }
    }

    private boolean performPaymentLogic(Long userId, Bill bill) {
        // Your payment processing logic goes here
        // For example, deduct the total price from the user's account
        // Simulating success for demonstration purposes
        return true;
    }

    private void savePaymentHistory(Long userId, Long billId, boolean paymentSuccess, double totalPrice) {
        PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistory.setUserId(userId);
        paymentHistory.setBillId(billId);
        paymentHistory.setPaymentStatus(paymentSuccess ? "Success" : "Failed");
        paymentHistory.setTotalPrice(totalPrice);
        //paymentHistory.setTimestamp(LocalDateTime.now());

        paymentHistoryRepository.save(paymentHistory);
    }
}


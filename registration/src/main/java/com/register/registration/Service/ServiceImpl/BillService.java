package com.register.registration.Service.ServiceImpl;

import com.register.registration.entity.Bill;
import com.register.registration.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

        @Autowired
        private CartService cartService;

        @Autowired
        private BillRepository billRepository;

        public void processBill(Long userId) {
            double totalPrice = calculateTotalPrice(userId);

            // Check if a Bill entry exists for the user
            List<Bill> existingBills = billRepository.findByUserId(userId);

            if (!existingBills.isEmpty()) {
                // If a Bill entry exists, update the total price
                Bill existingBill = existingBills.get(0);
                existingBill.setTotalPrice(totalPrice);
                billRepository.save(existingBill);
            } else {
                // If no Bill entry exists, create a new Bill entity
                Bill newBill = new Bill();
                newBill.setUserId(userId);
                newBill.setTotalPrice(totalPrice);
                billRepository.save(newBill);
            }
        }

        public double calculateTotalPrice(Long userId) {
            return cartService.getTotalCartCost(userId);
        }

        public Long getBillIdByUserId(Long userId) {
            List<Bill> bills = billRepository.findByUserId(userId);

            if (!bills.isEmpty()) {
                // Handle the situation where multiple bills are found
                // For now, returning the bill ID of the first item in the list
                return bills.get(0).getBillId();
            } else {
                return null;
            }
        }
    }


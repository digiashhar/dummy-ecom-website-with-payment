package com.register.registration.Controller;


import com.register.registration.Service.ServiceImpl.BillService;
import com.register.registration.dto.BillResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/process")
    //POST url should be - /api/bills/processBill?userId=123
    public ResponseEntity<String> processBill(@RequestParam Long userId) {
        billService.processBill(userId);
        return ResponseEntity.ok("Bill processed successfully!");
    }

    @GetMapping("/totalPrice/user={userId}")
    public ResponseEntity<Double> calculateTotalPrice(@PathVariable Long userId) {
        double totalPrice = billService.calculateTotalPrice(userId);
        return ResponseEntity.ok(totalPrice);
    }

    @GetMapping("/BillId/user={userId}")
    public ResponseEntity<Long> getBillIdByUserId(@PathVariable Long userId) {
        Long billId = billService.getBillIdByUserId(userId);

        if (billId != null) {
            return ResponseEntity.ok(billId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

package com.register.registration.entity;
import jakarta.persistence.*;
import lombok.Data;
// PaymentHistory entity
@Data
@Entity
@Table(name = "payment_history")
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "bill_id", nullable = false)
    private Long billId;

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus;
    @Column(name = "total_price", nullable = false)
    private double totalPrice;
}

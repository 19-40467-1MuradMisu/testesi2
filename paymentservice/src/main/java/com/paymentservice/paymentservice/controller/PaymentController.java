package com.paymentservice.paymentservice.controller;
import com.paymentservice.paymentservice.dto.OrderDto;
import com.paymentservice.paymentservice.dto.PaymentStatus;
import com.paymentservice.paymentservice.model.Payment;
import com.paymentservice.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // Endpoint to manually trigger payment processing
    @PostMapping("/payment")
    public ResponseEntity<String> createPayment(@RequestBody OrderDto orderDto) {


         // aitaao ekta method createPayment() jekhane payment create kora hobe
        paymentService.createPayment(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Payment created successfully");

        /*  Payment payment = paymentRepository.findById(orderDto.getId())
        .orElseThrow(() -> new RuntimeException("Payment not found"));

    // Change status
    payment.setPaymentStatus(PaymentStatus.PAYMENT_COMPLETED);
    paymentRepository.save(payment);

    // Update OrderDto and send to Kafka
    orderDto.setPaymentStatus(PaymentStatus.PAYMENT_COMPLETED);
    kafkaTemplate.send("paymentTopic", orderDto);

    return ResponseEntity.ok("Payment completed and sent to delivery.");
    */
}
}
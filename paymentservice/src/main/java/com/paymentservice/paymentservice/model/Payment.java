package com.paymentservice.paymentservice.model;


import java.math.BigDecimal;

import com.paymentservice.paymentservice.dto.PaymentStatus;
import com.paymentservice.paymentservice.dto.DeliveryStatus;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "paymenttable")
@Builder
public class Payment {
    @Id
    private Integer orderId;
    private Integer userId;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}

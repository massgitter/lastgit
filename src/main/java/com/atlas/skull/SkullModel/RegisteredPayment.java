package com.atlas.skull.SkullModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "registeredPayment")

public class RegisteredPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cancelationPeriod;
    private int enterpriseBankAccountId;
    private String uniEnterpriseId;
    private String merchantReference;
    private String uniTransactionId;
    private String msg;
    private LocalDateTime validUntil;
    private String bankTransactionRef;
    private String msgFromBank;
    private LocalDateTime registeredOn;
    private LocalDateTime lastUPdatedON;
    private LocalDateTime paidAt;
    private int bankID;
    private String bankName;
    private Long basePayment;
    private float total;
    private float penaltyAmount;
    private float vat;
    private int paidWithPenality;
    private int printedNumber;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "paymentStatus")
    private PaymentStatus paymentStatus;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "BatchPaimentRegister_id")
    private BatchPaimentRegister batchPaimentRegister;
}

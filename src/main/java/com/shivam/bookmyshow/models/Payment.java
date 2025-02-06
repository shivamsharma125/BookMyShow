package com.shivam.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Payment extends BaseModel{
    private int amount;
    private Date paymentTime;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
    private String referenceNo;
}

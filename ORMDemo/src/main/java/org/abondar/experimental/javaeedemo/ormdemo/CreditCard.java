package org.abondar.experimental.javaeedemo.ormdemo;

import javax.persistence.*;

@Entity
@Table(name="credit_card")
public class CreditCard {

    @Id
    private String number;

    private String expiryDate;

    private Integer controllNumber;

    @Enumerated(EnumType.STRING)
    private CreditCardType creditCardType;

    public  CreditCard(){}

    public CreditCard(String number, String expiryDate, Integer controllNumber, CreditCardType creditCardType) {
        this.number = number;
        this.expiryDate = expiryDate;
        this.controllNumber = controllNumber;
        this.creditCardType = creditCardType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getControllNumber() {
        return controllNumber;
    }

    public void setControllNumber(Integer controllNumber) {
        this.controllNumber = controllNumber;
    }

    public CreditCardType getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "number='" + number + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", controllNumber=" + controllNumber +
                ", creditCardType=" + creditCardType +
                '}';
    }
}

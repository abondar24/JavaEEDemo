package org.abondar.experimental.javaeedemo.docdemo.model;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CreditCard {


    @XmlAttribute(required = true)
    private String number;

    @XmlElement(name="expiry-date",defaultValue = "01/20")
    private String expiryDate;

    private String type;

    @XmlElement(name = "control-number")
    private Integer controlNumber;



    public CreditCard(String number, String expiryDate, Integer controlNumber, String type) {
        this.number = number;
        this.expiryDate = expiryDate;
        this.controlNumber = controlNumber;
        this.type = type;
    }

    public CreditCard() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(Integer controlNumber) {
        this.controlNumber = controlNumber;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "number='" + number + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", controlNumber=" + controlNumber +
                ", type='" + type + '\'' +
                '}';
    }
}

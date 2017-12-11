package org.abondar.experimental.javaeedemo.soapdemo.exception;

import javax.xml.ws.WebFault;

@WebFault(name = "CardValidationFault")
public class CardValidatorException extends Exception {

    public CardValidatorException(){
        super();
    }

    public CardValidatorException(String message){
        super(message);
    }
}

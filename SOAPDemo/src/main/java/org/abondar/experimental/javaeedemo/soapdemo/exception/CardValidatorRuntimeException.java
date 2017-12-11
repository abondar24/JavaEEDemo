package org.abondar.experimental.javaeedemo.soapdemo.exception;

import javax.xml.ws.WebFault;

@WebFault(name = "CardValidationFault")
public class CardValidatorRuntimeException extends RuntimeException{

    public CardValidatorRuntimeException(){
        super();
    }

    public CardValidatorRuntimeException(String message){
        super(message);
    }
}

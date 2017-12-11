package org.abondar.experimental.javaeedemo.soapdemo.exception;

import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.WebFault;
import javax.xml.ws.soap.SOAPFaultException;

@WebFault
public class CardValidatorSOAPFaultException extends SOAPFaultException{
    /**
     * Constructor for SOAPFaultException
     *
     * @param fault {@code SOAPFault} representing the fault
     * @see SOAPFactory#createFault
     **/
    public CardValidatorSOAPFaultException(SOAPFault fault) {
        super(fault);
    }
}

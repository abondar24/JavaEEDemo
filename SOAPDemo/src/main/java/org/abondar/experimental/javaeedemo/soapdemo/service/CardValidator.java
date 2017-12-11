package org.abondar.experimental.javaeedemo.soapdemo.service;

import org.abondar.experimental.javaeedemo.soapdemo.exception.CardValidatorException;
import org.abondar.experimental.javaeedemo.soapdemo.exception.CardValidatorRuntimeException;
import org.abondar.experimental.javaeedemo.soapdemo.exception.CardValidatorSOAPFaultException;
import org.abondar.experimental.javaeedemo.soapdemo.model.CreditCard;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.WebServiceContext;

@WebService(endpointInterface = "org.abondar.experimental.javaeedemo.soapdemo.service.Validator")
public class CardValidator implements Validator {

    @Resource
    private WebServiceContext context;

    @Override
    @WebMethod(operationName = "ValidateCreditCard")
    @WebResult(name = "IsValid")
    public boolean validate(@WebParam(name = "Credit-Card") CreditCard creditCard) {


        Character lastDigit = creditCard.getNumber().charAt(creditCard.getNumber().length() - 1);
        return Integer.parseInt(lastDigit.toString()) % 2 == 0;

    }

    @WebResult(name = "IsValid")
    public boolean secureValidate(@WebParam(name = "Credit-Card") CreditCard creditCard) {

        if (!context.isUserInRole("Admin"))
            throw new SecurityException("Only Admins can validate cards");

        Character lastDigit = creditCard.getNumber().charAt(creditCard.getNumber().length() - 1);
        return Integer.parseInt(lastDigit.toString()) % 2 == 0;

    }

    @WebResult(name = "IsValid")
    public boolean validateWithException(@WebParam(name = "Credit-Card") CreditCard creditCard) throws CardValidatorException {

        Character lastDigit = creditCard.getNumber().charAt(creditCard.getNumber().length() - 1);
        if (Integer.parseInt(lastDigit.toString()) % 2 == 0) {
            return true;
        } else {
            throw new CardValidatorException("Credit card number is invalid");
        }

    }


    @WebResult(name = "IsValid")
    public boolean validateWithRuntimeException(@WebParam(name = "Credit-Card") CreditCard creditCard) throws
            CardValidatorRuntimeException {

        Character lastDigit = creditCard.getNumber().charAt(creditCard.getNumber().length() - 1);
        if (Integer.parseInt(lastDigit.toString()) % 2 == 0) {
            return true;
        } else {
            throw new CardValidatorRuntimeException("Credit card number is invalid");
        }
    }

    @WebResult(name = "IsValid")
    public boolean validateWithSOAPException(@WebParam(name = "Credit-Card") CreditCard creditCard) throws SOAPException {

        Character lastDigit = creditCard.getNumber().charAt(creditCard.getNumber().length() - 1);
        if (Integer.parseInt(lastDigit.toString()) % 2 == 0) {
            return true;
        } else {
            SOAPFactory soapFactory = SOAPFactory.newInstance();
            SOAPFault fault = soapFactory.createFault("Credit card number is invalid", new QName("ValidationFault"));
            throw new CardValidatorSOAPFaultException(fault);
        }
    }

    public WebServiceContext getContext() {
        return context;
    }

    public void setContext(WebServiceContext context) {
        this.context = context;
    }

}

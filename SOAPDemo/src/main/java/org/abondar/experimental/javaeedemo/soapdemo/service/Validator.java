package org.abondar.experimental.javaeedemo.soapdemo.service;

import org.abondar.experimental.javaeedemo.soapdemo.model.CreditCard;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Validator {
    public boolean validate(CreditCard creditCard);


}

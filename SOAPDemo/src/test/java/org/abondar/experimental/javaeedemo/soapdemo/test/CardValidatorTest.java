package org.abondar.experimental.javaeedemo.soapdemo.test;

import org.abondar.experimental.javaeedemo.soapdemo.exception.CardValidatorException;
import org.abondar.experimental.javaeedemo.soapdemo.exception.CardValidatorRuntimeException;
import org.abondar.experimental.javaeedemo.soapdemo.exception.CardValidatorSOAPFaultException;
import org.abondar.experimental.javaeedemo.soapdemo.model.CreditCard;
import org.abondar.experimental.javaeedemo.soapdemo.service.CardValidator;
import org.abondar.experimental.javaeedemo.soapdemo.service.Validator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceContext;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CardValidatorTest {

    @Mock
    private WebServiceContext mockedContext;

    @Test
    public void checkCreditCardValidityTest() {

        CreditCard creditCard = new CreditCard("12341234", "10/10", 1234, "VISA");

        CardValidator cardValidator = new CardValidator();
        assertTrue("Credit card should be valid", cardValidator.validate(creditCard));

        creditCard.setNumber("12341233");
        assertFalse("Credit card should not be valid", cardValidator.validate(creditCard));

    }


    @Test(expected = CardValidatorException.class)
    public void throwCardValidatorExceptionTest() throws Exception {

        CreditCard creditCard = new CreditCard("12341234", "10/10", 1234, "VISA");

        CardValidator cardValidator = new CardValidator();
        assertTrue("Credit card should be valid", cardValidator.validateWithException(creditCard));
        creditCard.setNumber("12341233");
        cardValidator.validateWithException(creditCard);
    }


    @Test(expected = CardValidatorRuntimeException.class)
    public void throwCardValidatorRuntimeExceptionTest() throws Exception {

        CreditCard creditCard = new CreditCard("12341234", "10/10", 1234, "VISA");

        CardValidator cardValidator = new CardValidator();
        assertTrue("Credit card should be valid", cardValidator.validateWithException(creditCard));
        creditCard.setNumber("12341233");
        cardValidator.validateWithRuntimeException(creditCard);
    }


    @Test(expected = CardValidatorSOAPFaultException.class)
    public void throwCardValidatorSOAPExceptionTest() throws Exception {

        CreditCard creditCard = new CreditCard("12341234", "10/10", 1234, "VISA");

        CardValidator cardValidator = new CardValidator();
        assertTrue("Credit card should be valid", cardValidator.validateWithException(creditCard));
        creditCard.setNumber("12341233");
        cardValidator.validateWithSOAPException(creditCard);
    }


    @Test
    public void checkCreditCardValidityForAdminsTest() {

        when(mockedContext.isUserInRole("Admin")).thenReturn(true);

        CreditCard creditCard = new CreditCard("12341234", "10/10", 1234, "VISA");

        CardValidator cardValidator = new CardValidator();
        cardValidator.setContext(mockedContext);
        assertTrue("Credit card should be valid", cardValidator.secureValidate((creditCard)));


        creditCard.setNumber("12341233");
        assertFalse("Credit card should not be valid", cardValidator.secureValidate((creditCard)));

    }

    @Test(expected = SecurityException.class)
    public void checkCreditCardValidityForNonAdminsTest() {

        when(mockedContext.isUserInRole("Admin")).thenReturn(false);

        CreditCard creditCard = new CreditCard("12341234", "10/10", 1234, "VISA");

        CardValidator cardValidator = new CardValidator();
        cardValidator.setContext(mockedContext);

        assertTrue("Credit card should be valid", cardValidator.secureValidate(creditCard));

        creditCard.setNumber("12341233");
        assertFalse("Credit card should not be valid", cardValidator.secureValidate((creditCard)));
    }


    @Test
    public void checkCreditCardValidityIntegrationTest() throws MalformedURLException {

        Endpoint endpoint = Endpoint.publish("http://localhost:8081/SOAPDemo/CardValidator", new CardValidator());
        assertTrue(endpoint.isPublished());
        assertEquals("http://schemas.xmlsoap.org/wsdl/soap/http", endpoint.getBinding().getBindingID());

        URL wsdlDocumentLocation = new URL("http://localhost:8081/SOAPDemo/CardValidator?wsdl");
        String namespaceURI = "http://service.soapdemo.javaeedemo.experimental.abondar.org/";
        String servicePart = "CardValidatorService";
        String portName = "CardValidatorPort";
        QName serviceQN = new QName(namespaceURI, servicePart);
        QName portQN = new QName(namespaceURI, portName);

        Service service = Service.create(wsdlDocumentLocation, serviceQN);
        Validator cardValidator = service.getPort(portQN, Validator.class);

        CreditCard creditCard = new CreditCard("12341234", "10/10", 1234, "VISA");

        assertTrue("Credit card should be valid", cardValidator.validate(creditCard));
        creditCard.setNumber("12341233");
        assertFalse("Credit card should not be valid", cardValidator.validate(creditCard));

        endpoint.stop();
        assertFalse(endpoint.isPublished());
    }
}

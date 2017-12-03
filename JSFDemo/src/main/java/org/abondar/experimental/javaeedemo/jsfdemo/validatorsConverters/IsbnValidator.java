package org.abondar.experimental.javaeedemo.jsfdemo.validatorsConverters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("isbnValidator")
public class IsbnValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        String componentValue = value.toString();

        Pattern pattern = Pattern.compile("(?=[-0-9xX]{13}$)");
        Matcher matcher = pattern.matcher(componentValue);

        if (!matcher.find()){
            String message = MessageFormat.format("{0} not a valid isbn format",componentValue);
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,message,message);
            throw new ValidatorException(facesMessage);
        }
    }
}

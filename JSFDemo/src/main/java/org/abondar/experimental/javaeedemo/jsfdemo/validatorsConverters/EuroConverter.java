package org.abondar.experimental.javaeedemo.jsfdemo.validatorsConverters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.DecimalFormat;

@FacesConverter("euroConverter")
public class EuroConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        float amountInDollars = Float.parseFloat(value.toString());
        double amountInEuros = amountInDollars*1.15;
        DecimalFormat df = new DecimalFormat("###,##0.##");
        return df.format(amountInEuros);
    }
}

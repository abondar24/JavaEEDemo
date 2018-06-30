package org.abondar.experimental.richdemo.dataieration;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarConverter extends XmlAdapter<String,Calendar> {
    @Override
    public Calendar unmarshal(String v) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY).parse(v));
        return calendar;
    }

    @Override
    public String marshal(Calendar v) throws Exception {
        return null;
    }
}

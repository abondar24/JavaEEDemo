package org.abondar.experimental.richdemo.controllers;

import org.richfaces.model.CalendarDataModel;
import org.richfaces.model.CalendarDataModelItem;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Named("calendarModel")
@ApplicationScoped
public class CalendarModel implements CalendarDataModel {

    private static final String WEEKEND_DAY_CLASS = "wdc";
    private static final String BUSY_DAY_CLASS = "bdc";
    private static final String BOUNDARY_DAY_CLASS ="rf-ca-boundary-class";

    private boolean checkBusyDay(Calendar calendar){
        return (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY ||
        calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY);
    }

    private boolean checkWeekend(Calendar calendar){
        return (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
        calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY);
    }

    @Override
    public CalendarDataModelItem[] getData(Date[] dates) {
        CalendarDataModelItem[] items = new CalendarDataModelItem[dates.length];
        Calendar current = GregorianCalendar.getInstance();
        Calendar today = GregorianCalendar.getInstance();
        today.setTime(new Date());

        for (int i=0; i<dates.length;i++) {
            current.setTime(dates[i]);
            CalendarModelItem modelItem = new CalendarModelItem();
            if (current.before(today)) {
                modelItem.setEnabled(true);
                modelItem.setStyleClass(BOUNDARY_DAY_CLASS);
            } else if (checkBusyDay(current)){
                modelItem.setEnabled(false);
                modelItem.setStyleClass(BUSY_DAY_CLASS);
            } else if (checkWeekend(current)){
                modelItem.setEnabled(false);
                modelItem.setStyleClass(WEEKEND_DAY_CLASS);
            } else {
                modelItem.setEnabled(true);
                modelItem.setStyleClass("");
            }
            items[i] = modelItem;
        }
        return items;
    }

    @Override
    public Object getToolTip(Date date) {
        return null;
    }
}

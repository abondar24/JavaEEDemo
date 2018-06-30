package org.abondar.experimental.richdemo.dataieration;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class GameDescriptor implements Serializable {

    private String city;
    private String country;
    private String continent;
    private String flagName;
    private int number;
    private Calendar fromDate = new GregorianCalendar(Locale.GERMANY);
    private Calendar toDate = new GregorianCalendar(Locale.GERMANY);
    private Seasons season;
    private Statuses status = Statuses.passed;


    @XmlElement
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @XmlElement
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @XmlElement
    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
    @XmlElement
    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }
    @XmlElement
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Calendar getFromDate() {
        return fromDate;
    }

    public void setFromDate(Calendar fromDate) {
        this.fromDate = fromDate;
    }

    public Calendar getToDate() {
        return toDate;
    }

    public void setToDate(Calendar toDate) {
        this.toDate = toDate;
    }
    @XmlElement
    public Seasons getSeason() {
        return season;
    }

    public void setSeason(Seasons season) {
        this.season = season;
    }
    @XmlElement
    public Statuses getStatus() {
        return status;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }

    public int getYear() {
        return fromDate.get(Calendar.YEAR);
    }
    public String getFrom() {
        if (!status.equals(Statuses.canceled)) {
            return fromDate.get(Calendar.DAY_OF_MONTH) + " "
                    + fromDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        } else return "";
    }
    public String getTo() {
        if (!status.equals(Statuses.canceled)) {
            return toDate.get(Calendar.DAY_OF_MONTH) + " "
                    + toDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        } else return "";
    }
}

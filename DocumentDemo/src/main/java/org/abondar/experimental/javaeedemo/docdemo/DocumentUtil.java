package org.abondar.experimental.javaeedemo.docdemo;

import org.abondar.experimental.javaeedemo.docdemo.model.CreditCard;
import org.abondar.experimental.javaeedemo.docdemo.model.Customer;
import org.abondar.experimental.javaeedemo.docdemo.model.Order;
import org.abondar.experimental.javaeedemo.docdemo.model.OrderLine;
import org.abondar.experimental.javaeedemo.docdemo.parsers.OrderDomUtil;
import org.abondar.experimental.javaeedemo.docdemo.parsers.OrderJsonUtil;
import org.abondar.experimental.javaeedemo.docdemo.parsers.XsltTransformer;

import javax.json.JsonObject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class DocumentUtil {

    private static OrderJsonUtil orderJsonUtil = new OrderJsonUtil();

    public static void xsltTransforming() {
        System.out.println("Order xslt transforming");
        String order = new XsltTransformer().transformOrder();
        System.out.println(order);
    }

    public static String marshallCreditCard(CreditCard creditCard) throws JAXBException {
        StringWriter writer = new StringWriter();

        JAXBContext context = JAXBContext.newInstance(CreditCard.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(creditCard, writer);
        System.out.println("POJO to marshall " + creditCard);
        return writer.toString();
    }

    public static CreditCard unMarshallCreditCard(String xmlFilename) throws Exception {
        StringReader reader = new StringReader(readFile(xmlFilename));
        JAXBContext context = JAXBContext.newInstance(CreditCard.class);
        Unmarshaller u = context.createUnmarshaller();

        return (CreditCard) u.unmarshal(reader);
    }

    public static String createXmlByDom() throws Exception {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date date = formatter.parse("24/09/2017");
        Order order = new Order(date, 24);

        OrderLine orderLine1 = new OrderLine("Cars", 23.5, 1);
        OrderLine orderLine2 = new OrderLine("Salo", 34.99, 2);

        System.out.println("Transforming order pojos to xml");
        System.out.println(order);
        System.out.println(orderLine1);
        System.out.println(orderLine2);
        return new OrderDomUtil().buildOrderXML(order, Arrays.asList(orderLine1, orderLine2));

    }

    public static String buildOrderJson() throws Exception {
        Customer customer = new Customer("Alex", "Bondar", "abondar@me.com", "+4912341234");
        CreditCard creditCard = new CreditCard("1357", "10/20", 234, "Visa");
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date date = formatter.parse("24/09/2017");
        Order order = new Order(date, 24);

        OrderLine orderLine1 = new OrderLine("Cars", 23.5, 1);
        OrderLine orderLine2 = new OrderLine("Salo", 34.99, 2);

        System.out.println("Transforming order pojos to json");
        System.out.println(order);
        System.out.println(customer);
        System.out.println(orderLine1);
        System.out.println(orderLine2);
        System.out.println(creditCard);
        JsonObject orderJSON = orderJsonUtil.buildOrderJson(customer, order,
                Arrays.asList(orderLine1, orderLine2), creditCard);

        return orderJSON.toString();
    }



    public static String parseOrderJsonAndReturnEmail() throws Exception {
        return orderJsonUtil.parseOrderJsonAndReturnEmail("src/main/resources/order.json");
    }


    public static String generateOrderJson() throws Exception {
        Customer customer = new Customer("Alex", "Bondar", "abondar@me.com", "+4912341234");
        CreditCard creditCard = new CreditCard("1357", "10/20", 234, "Visa");
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date date = formatter.parse("24/09/2017");
        Order order = new Order(date, 24);

        OrderLine orderLine1 = new OrderLine("Cars", 23.5, 1);
        OrderLine orderLine2 = new OrderLine("Salo", 34.99, 2);

        System.out.println("Generating json based on pojos");
        System.out.println(order);
        System.out.println(customer);
        System.out.println(orderLine1);
        System.out.println(orderLine2);
        System.out.println(creditCard);

        return orderJsonUtil.generatePurchaseOrder(customer, order,
                Arrays.asList(orderLine1, orderLine2), creditCard);
    }


    public static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            return sb.toString().replace("  ", "");
        } finally {
            br.close();
        }
    }

    public static String convertDateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }


}

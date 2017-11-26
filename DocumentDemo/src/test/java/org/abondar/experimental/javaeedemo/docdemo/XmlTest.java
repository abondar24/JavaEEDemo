package org.abondar.experimental.javaeedemo.docdemo;


import org.abondar.experimental.javaeedemo.docdemo.DocumentUtil;
import org.abondar.experimental.javaeedemo.docdemo.model.CreditCard;
import org.abondar.experimental.javaeedemo.docdemo.model.Customer;
import org.abondar.experimental.javaeedemo.docdemo.model.Order;
import org.abondar.experimental.javaeedemo.docdemo.parsers.*;
import org.xml.sax.SAXException;
import org.abondar.experimental.javaeedemo.docdemo.model.OrderLine;
import org.junit.Test;

import javax.json.JsonObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.abondar.experimental.javaeedemo.docdemo.DocumentUtil.readFile;
import static org.junit.Assert.assertEquals;

public class XmlTest {



    @Test
    public void parseOrderLinesSaxTest() throws Exception {
        List<OrderLine> parseOrderLines = new OrderSaxParser().parseOrderLines();
        assertEquals(2, parseOrderLines.size());

        OrderLine orderLine = parseOrderLines.get(0);
        assertEquals("Cars", orderLine.getItem());
        assertEquals(Integer.valueOf(1), orderLine.getQuantity());
        assertEquals(Double.valueOf(23.5), orderLine.getUnitPrice());

        orderLine = parseOrderLines.get(1);
        assertEquals("Salo", orderLine.getItem());
        assertEquals(Integer.valueOf(2), orderLine.getQuantity());
        assertEquals(Double.valueOf(34.99), orderLine.getUnitPrice());
    }

    @Test(expected = SAXException.class)
    public void parseAndValidateOrderSaxTest() throws Exception {

        OrderSaxParserWithValidation parser = new OrderSaxParserWithValidation();
        parser.parseOrderFile();
        System.out.println(parser.getOutput());
    }


    @Test
    public void parseOrderLinesDomTest() throws Exception {
        List<OrderLine> parseOrderLines = new OrderDomUtil().parseOrderLines();
        assertEquals(2, parseOrderLines.size());

        OrderLine orderLine = parseOrderLines.get(0);
        assertEquals("Cars", orderLine.getItem());
        assertEquals(Integer.valueOf(1), orderLine.getQuantity());
        assertEquals(Double.valueOf(23.5), orderLine.getUnitPrice());

        orderLine = parseOrderLines.get(1);
        assertEquals("Salo", orderLine.getItem());
        assertEquals(Integer.valueOf(2), orderLine.getQuantity());
        assertEquals(Double.valueOf(34.99), orderLine.getUnitPrice());
    }


    @Test(expected = SAXException.class)
    public void parseOrderLinesDomValidationTest() throws Exception {
        List<OrderLine> parseOrderLines = new OrderDomUtil().parseOrderLinesWithValidation();
        assertEquals(2, parseOrderLines.size());

        OrderLine orderLine = parseOrderLines.get(0);
        assertEquals("Cars", orderLine.getItem());
        assertEquals(Integer.valueOf(1), orderLine.getQuantity());
        assertEquals(Double.valueOf(23.5), orderLine.getUnitPrice());

    }


    @Test
    public void xsltTransformOrderTest() throws Exception {
        String orderXml = "<html>\n" +
                "   <body>\n" +
                "      <h2>Sold Items</h2>\n" +
                "      <table border=\"1\">\n" +
                "         <tr>\n" +
                "            <th>Title</th>\n" +
                "            <th>Quantity</th>\n" +
                "            <th>Unit Price</th>\n" +
                "         </tr>\n" +
                "         <tr>\n" +
                "            <td>Cars</td>\n" +
                "            <td>1</td>\n" +
                "            <td>23.5</td>\n" +
                "         </tr>\n" +
                "         <tr>\n" +
                "            <td>Salo</td>\n" +
                "            <td>2</td>\n" +
                "            <td bgcolor=\"#FF0000\">34.99</td>\n" +
                "         </tr>\n" +
                "      </table>\n" +
                "   </body>\n" +
                "</html>";

        String orderXML = new XsltTransformer().transformOrder();
        assertEquals(orderXml, orderXML);

    }

    @Test
    public void domBuildOrderXmlTest() throws Exception {
        String orderXML = DocumentUtil.createXmlByDom().replace("\n","").replace("   ","");
        assertEquals(readFile("src/main/resources/order1.xml"), orderXML);

    }

    @Test
    public void jaxbMarshallCreditCardTest() throws Exception {
        CreditCard creditCard = new CreditCard("12345678", "10/20", 566, "Visa");
        assertEquals(readFile("src/main/resources/creditcard.xml"),
                DocumentUtil.marshallCreditCard(creditCard));

    }


    @Test
    public void shouldUnmarshallACreditCard() throws Exception {
        CreditCard creditCard = new CreditCard("12345678", "10/20", 566, "Visa");

        CreditCard unmarshalled = DocumentUtil.unMarshallCreditCard("src/main/resources/creditcard.xml");

        assertEquals( creditCard.getNumber(),unmarshalled.getNumber());
        assertEquals(creditCard.getExpiryDate(),unmarshalled.getExpiryDate());
        assertEquals(creditCard.getControlNumber(),unmarshalled.getControlNumber());
        assertEquals( creditCard.getType(),unmarshalled.getType());
    }

}

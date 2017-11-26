package org.abondar.experimental.javaeedemo.docdemo.parsers;

import org.abondar.experimental.javaeedemo.docdemo.model.OrderLine;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OrderSaxParser extends DefaultHandler {

    private List<OrderLine> orderLines = new ArrayList<>();

    private OrderLine orderLine;

    private Boolean dealingWithUnitPrice = false;

    private StringBuilder unitPriceBuilder;

    public List<OrderLine> parseOrderLines(){

        try {
            File xml = Paths.get("src/main/resources/order.xml").toFile();

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            saxParser.parse(xml,this);
        } catch (SAXException | IOException| ParserConfigurationException ex){
            System.out.println(ex.getMessage());
        }

        return orderLines;
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes attrs)
            throws SAXException{

        switch (qualifiedName){
            case "order_line":
                orderLine = new OrderLine();
                for(int i=0;i<attrs.getLength();i++){
                    switch (attrs.getLocalName(i)){
                        case "item":
                            orderLine.setItem(attrs.getValue(i));
                            break;
                        case "quantity":
                            orderLine.setQuantity(Integer.valueOf(attrs.getValue(i)));
                            break;
                    }
                }
                break;

            case "unit_price":
                dealingWithUnitPrice = true;
                unitPriceBuilder = new StringBuilder();
                break;
        }
    }


    @Override
    public void characters(char[] ch, int start,int len) throws SAXException{
        if (dealingWithUnitPrice){
            unitPriceBuilder.append(ch,start,len);
        }
    }


    @Override
    public void endElement(String namespaceURI, String localName, String qualifiedName)
            throws SAXException{

        switch (qualifiedName){
            case "order_line":
                orderLines.add(orderLine);
                break;

            case "unit_price":
                orderLine.setUnitPrice(Double.valueOf(unitPriceBuilder.toString()));
                dealingWithUnitPrice = false;
                break;
        }
    }
}

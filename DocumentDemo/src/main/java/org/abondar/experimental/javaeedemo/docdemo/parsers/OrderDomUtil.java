package org.abondar.experimental.javaeedemo.docdemo.parsers;


import com.jcabi.xml.XMLDocument;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import org.abondar.experimental.javaeedemo.docdemo.DocumentUtil;
import org.abondar.experimental.javaeedemo.docdemo.model.Order;
import org.abondar.experimental.javaeedemo.docdemo.model.OrderLine;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderDomUtil {

    public List<OrderLine> parseOrderLines() {

        List<OrderLine> orderLines = new ArrayList<>();
        try {
            File xmlDocument = Paths.get("src/main/resources/order.xml").toFile();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlDocument);

            NodeList orderLinesNode = document.getElementsByTagName("order_line");

            orderLines = parseNode(orderLinesNode);
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            System.out.println(ex.getMessage());
        }

        return orderLines;
    }

    public List<OrderLine> parseOrderLinesWithValidation() throws SAXException, IOException, ParserConfigurationException {

        File xmlDocument = Paths.get("src/main/resources/bad_order.xml").toFile();
        File xmlSchema = Paths.get("src/main/resources/order.xsd").toFile();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(xmlSchema);
        factory.setSchema(schema);

        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new DomParsingErrorHandler());
        Document document = builder.parse(xmlDocument);

        NodeList orderLinesNode = document.getElementsByTagName("order_line");


        return parseNode(orderLinesNode);
    }


    private List<OrderLine> parseNode(NodeList nodes) {
        List<OrderLine> orderLines = new ArrayList<>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Element orderLineNode = (Element) nodes.item(i);
            OrderLine orderLine = new OrderLine();
            orderLine.setItem(orderLineNode.getAttribute("item"));
            orderLine.setQuantity(Integer.valueOf(orderLineNode.getAttribute("quantity")));

            Node unitPriceNode = orderLineNode.getChildNodes().item(1);
            orderLine.setUnitPrice(Double.valueOf(unitPriceNode.getFirstChild().getNodeValue()));
            orderLines.add(orderLine);
        }

        return orderLines;
    }


    public String buildOrderXML(Order order, List<OrderLine> lines){
       String xml="";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element orderElem = document.createElement("order");
            orderElem.setAttribute("date", DocumentUtil.convertDateToString(order.getDate()));
            orderElem.setAttribute("id", order.getId().toString());
            document.appendChild(orderElem);

            Element content = document.createElement("content");
            orderElem.appendChild(content);

            lines.forEach(l->{
                Element order_line = document.createElement("order_line");
                order_line.setAttribute("item", l.getItem());
                order_line.setAttribute("quantity", l.getQuantity().toString());
                Element unit_price1 = document.createElement("unit_price");
                unit_price1.appendChild(document.createTextNode(l.getUnitPrice().toString()));
                order_line.appendChild(unit_price1);
                content.appendChild(order_line);
            });


            xml = new XMLDocument(document).toString();

        } catch ( ParserConfigurationException e) {
            e.printStackTrace();
        }

        return xml;
    }

    }


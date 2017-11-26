package org.abondar.experimental.javaeedemo.docdemo.parsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


public class OrderSaxParserWithValidation extends DefaultHandler {

    private Integer numTabs=0;

    private StringBuilder sb = new StringBuilder();

    public void parseOrderFile() throws SAXException, IOException, ParserConfigurationException {


            File xml = Paths.get("src/main/resources/bad_order.xml").toFile();
            File xmlSchema = Paths.get("src/main/resources/order.xsd").toFile();

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(xmlSchema);

            factory.setSchema(schema);
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(xml, this);
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes attrs)
            throws SAXException {

        String elementName;
        if (localName != null && !localName.isEmpty()) {
            elementName = localName;
        } else {
          elementName = qualifiedName;
        }

        sb.append(tabs()).append(elementName).append("{\n");
        numTabs++;

        if (attrs!=null){
            for (int i = 0; i < attrs.getLength(); i++) {
                sb.append(tabs()).append(attrs.getLocalName(i)).append("=").append(attrs.getValue(i)).append("\n");
            }
        }
    }

    @Override
    public void error(SAXParseException exception) throws SAXException{
        throw new SAXException(exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        throw new SAXException(exception.getMessage());
    }


    @Override
    public void endElement(String namespaceURI, String localName, String qualifiedName)
            throws SAXException {

      numTabs--;
        sb.append("}\n");
    }



    private String tabs(){
        StringBuilder tabs = new StringBuilder();

        for (int i=0;i<numTabs;i++){
            tabs.append("\t");
        }

        return tabs.toString();
    }

    public String getOutput(){
        return sb.toString();
    }
}

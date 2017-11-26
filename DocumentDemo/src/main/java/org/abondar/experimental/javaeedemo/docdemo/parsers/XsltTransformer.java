package org.abondar.experimental.javaeedemo.docdemo.parsers;


import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringWriter;
import java.nio.file.Paths;

public class XsltTransformer {

    public String transformOrder() {
        StringWriter writer = new StringWriter();

        try {
            File xmlDocument = Paths.get("src/main/resources/order.xml").toFile();
            File stylesheet = Paths.get("src/main/resources/order.xsl").toFile();

            TransformerFactory factory = TransformerFactory.newInstance("net.sf.saxon.TransformerFactoryImpl",null);
            Transformer transformer = factory.newTransformer(new StreamSource(stylesheet));
            transformer.transform(new StreamSource(xmlDocument),new StreamResult(writer));

        } catch (TransformerException ex) {
            System.out.println(ex.getMessage());
        }

        return writer.toString();
    }
}

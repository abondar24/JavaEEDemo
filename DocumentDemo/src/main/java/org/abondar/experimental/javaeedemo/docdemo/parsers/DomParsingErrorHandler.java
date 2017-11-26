package org.abondar.experimental.javaeedemo.docdemo.parsers;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DomParsingErrorHandler implements ErrorHandler {
    @Override
    public void warning(SAXParseException exception) throws SAXException {
        throw exception;
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        throw exception;
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        throw exception;
    }
}

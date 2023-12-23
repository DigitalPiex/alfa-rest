package com.example.restalfabank.service.parser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

class TaskInit {
    static void init(File file) {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        SAXParser saxParser = null;
        try {
            saxParser = saxParserFactory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

        XmlParser.XMLHandler handler = new XmlParser.XMLHandler();

        try {
            saxParser.parse(file, handler);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

    }
}

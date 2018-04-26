package mockData;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.Properties;

public class Mocker {

    public String getConfig(String config, String filePath) {
        Properties prop = new Properties();
        InputStream input = null;
        String value = null;

        try {

            input = new FileInputStream(filePath);
            prop.load(input);
            value = prop.getProperty(config);

        } catch (IOException io) {
            System.out.println("File not found-->" + io.getMessage());
        }
        return value;
    }

    public Document getMockData(String filePath) {
        Document doc = null;
        try {

            File fXmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
        } catch (Exception e) {
            System.out.println("Error encountered while reading file-->" + e.getMessage());
        }
        return doc;
    }
}


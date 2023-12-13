import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxHR6121 {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                public void startElement(String uri, String localName, String qName, Attributes attributes)
                        throws SAXException {
                    System.out.println("start: " + qName);
                    int length = attributes.getLength();
                    for (int i = 0; i < length; i++) {
                        System.out.println("{" + attributes.getQName(i) + ":" + attributes.getValue(i) + "}");
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    System.out.println("end: " + qName);
                }

                public void characters(char[] ch, int start, int length) throws SAXException {
                    System.out.println(new String(ch, start, length));
                }
            };

            saxParser.parse("src/HR6121_kurzusfelvetel.xml", handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

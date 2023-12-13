import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
public class XsdHR6121 {
    public static void main(String[] args) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            File schemaLocation = new File("src/HR6121_kurzusfelvetel.xsd");
            Schema schema = factory.newSchema(schemaLocation);

            Validator validator = schema.newValidator();

            File xmlFile = new File("src/HR6121_kurzusfelvetel.xml");
            SAXSource source = new SAXSource(new InputSource(xmlFile.toURI().toString()));

            validator.validate(source);
            System.out.println("Az XML fájl érvényes az XSD sémához képest.");

        } catch (SAXException e) {
            System.out.println("Hiba az XML validációjában: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Hiba: " + e.getMessage());
        }
    }
}

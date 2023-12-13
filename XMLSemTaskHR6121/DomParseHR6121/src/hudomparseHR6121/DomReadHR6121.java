package hudomparseHR6121;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomReadHR6121 {
	
	private static int baseLineRule = 0;
	 private final static String XMLFILE = "XMLHR6121.xml";
	 private static StringBuilder result;
	
	public static void main(String[] args) throws IOException {
       readOutAllElements();
   }

   //Eredmény kiírása
   private static void resultToConsole() {
       System.out.println(result.toString());
   }

   //Minden elem beolvasása
   private static void readOutAllElements(){
       Document doc;
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       result = new StringBuilder();
       try {
           DocumentBuilder dBuilder = factory.newDocumentBuilder();
           doc = dBuilder.parse(XMLFILE);

           Node er = doc.getFirstChild();
           NodeList erList = er.getChildNodes();
           getAllElements(erList);
       } catch (ParserConfigurationException | SAXException | IOException e) {
           e.printStackTrace();
       }

       resultToConsole();
   }

   //Elemek összefûzése
   private static void getAllElements(NodeList nl) {
       Node node;
       for (int i = 0; i < nl.getLength(); i++) {
           node = nl.item(i);
           if(node.getNodeType() == Node.ELEMENT_NODE) {

               result.append(indent(3)+node.getNodeName());

               if(node.hasAttributes()) {
                   result.append(" "+ getAttributes(node.getAttributes())+"\n");//
               } else {
                   result.append("\n");
               }
               if(node.getChildNodes().getLength() != 1) {

                   NodeList childList = node.getChildNodes();
                   baseLineRule++;
                   getAllElements(childList);
               } else {
                   baseLineRule++;
                   result.append(indent(3)+node.getTextContent()+"\n");
                   baseLineRule--;
               }
           }
       }
       baseLineRule--;
   }

   //Elemek elválasztása
   private static String indent(int multiplyBy) {
       StringBuilder whiteSpaces = new StringBuilder();
       for (int i = 0; i < (baseLineRule *multiplyBy); i++) {
           whiteSpaces.append(" ");
       }
       return whiteSpaces.toString();
   }

   //Elemek tulajdonságának a neve
   private static String getAttributes(NamedNodeMap node) {
       StringBuilder sb = new StringBuilder(" Attributes: [");
       for (int i = 0; i < node.getLength(); i++) {
           if(i != node.getLength()-1) {
               sb.append(node.item(i).getNodeName()+" : "+node.item(i).getNodeValue()+"; ");
           } else {
               sb.append(node.item(i).getNodeName()+" : "+node.item(i).getNodeValue());
           }
       }
       sb.append("]");
       return sb.toString();
   }
}

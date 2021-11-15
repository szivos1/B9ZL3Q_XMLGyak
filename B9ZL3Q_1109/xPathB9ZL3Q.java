package xpathb9zl3q1107;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class xPathB9ZL3Q {

	public static void main(String[] args) {
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse("studentB9ZL3Q.xml");
            document.getDocumentElement().normalize();
            XPath xPath= XPathFactory.newInstance().newXPath();
            //feladat1
            //String expression = "class/student";
            //feladat2
            //String expression = "class/student[@id=01]";
            //feladat3
            //String expression = "//student";
            //feladat4
            //String expression = "class/student[2]";
            //feladat5
            //String expression = "class/student[last()]";
            //feladat6
            //String expression = "class/student[last()-1]";
            //feladat7
            //String expression = "class/student[position()<3]";
            //feladat8
            //String expression = "class/*";
            //feladat9
            //String expression = "class/student[@id]";
            //feladat10
            //String expression = "//*";
            //feladat11
            //String expression = "class/student[kor>20]";
            //feladat12
            //String expression = "class/student"
            //NodeList nodelist = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
            //  for(int i = 0; i< nodelist.getLength();i++){
            //            Node node = nodelist.item(i);
            //                    System.out.println("\n Aktuális elem:" + node.getNodeName());
            //            if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")){
            //                Element element = (Element) node;
            //                System.out.println("Keresztnév: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
            //                System.out.println("Vezetéknév: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
            // }
        String expression = "class/student";
        NodeList nodelist = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
        for(int i = 0; i< nodelist.getLength();i++){
            Node node = nodelist.item(i);
                    System.out.println("\n Aktuális elem:" + node.getNodeName());
            if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")){
                Element element = (Element) node;
                System.out.println("ID:"+ element.getAttribute("id"));
                System.out.println("Keresztnév: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
                System.out.println("Vezetéknév: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
                System.out.println("Becenév: " + element.getElementsByTagName("becenev").item(0).getTextContent());
                System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());
            }

            }
        }catch (ParserConfigurationException e){
            e.printStackTrace();
        }
        catch (SAXException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (XPathExpressionException e){
            e.printStackTrace();
        }
    }
}
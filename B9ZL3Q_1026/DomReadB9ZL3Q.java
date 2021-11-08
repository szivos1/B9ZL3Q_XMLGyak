package domb9zl3q1026;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomReadB9ZL3Q {

	public static void main(String[] args) {
		try {
			File sourceFile = new File("usersB9ZL3Q.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document doc = dBuilder.parse(sourceFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element: "+ doc.getDocumentElement().getNodeName());
			ReadCurrentElement(doc);
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(SAXException sae) {
			sae.printStackTrace();
		}
	}
	
	public static void ReadCurrentElement(Document doc) {
		NodeList nList = doc.getElementsByTagName("user");
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element element = (Element) nNode;
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
					String id = element.getAttribute("id");
					String firstname = element.getElementsByTagName("firstname").item(0).getTextContent();
					String lastname = element.getElementsByTagName("lastname").item(0).getTextContent();
					String profession = element.getElementsByTagName("profession").item(0).getTextContent();
					
					System.out.println("\n");
					System.out.println("Current element: "+ nNode.getNodeName());
					System.out.println("\n\tUser id\t "+ id + "\n\tFirst name:\t"+firstname+"\n\tLast name:\t"+lastname+"\n\tProfession:\t"+profession);
				
			}
		}
	}

}

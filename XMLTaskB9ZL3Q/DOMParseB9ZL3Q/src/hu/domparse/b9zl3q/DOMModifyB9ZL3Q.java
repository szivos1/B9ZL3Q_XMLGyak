package hu.domparse.b9zl3q;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMModifyB9ZL3Q {

	public static void main(String[] args){
		// DOMBuilder-rel  DOM letrheozasa		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File("XMLB9ZL3Q.xml"));
			//normalizalas
            document.getDocumentElement().normalize();
            Scanner scanner = new Scanner(System.in);
            boolean exit=true;
            while(exit) {
				//switch case segitsegevel menu
            	System.out.println("0: Kilepes es vegrehajtas\n1: Termek ar mododsitas\n2: Vasarlo telefonszam mododsitas\n3: Raktar nev mododsitas");
            	switch(scanner.nextInt()) {
            		case 1 : termAr(document, scanner);break;
            		case 2 : vasTel(document, scanner);break;
            		case 3 : rakNev(document, scanner);break;
					case 0 : exit=false;
            		default : exit=false;
            	}
            }
            scanner.close();
			//kiirja az uj doksiba
            writeToXml(document);
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException | InputMismatchException e) {
            e.printStackTrace();
        }
	}
	
	public static void termAr(Document document, Scanner scanner) {
		//kikeresi tag alapjan
		NodeList nodeList = document.getElementsByTagName("termek");
		System.out.println("Termek ID: ");
		String input = scanner.next();
		for	(int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				//kikeresi id alapjan
				if (node.getAttributes().getNamedItem("tid").getTextContent().equals(input)) {
					//lekeri a child node-okat
					NodeList subNodeList = node.getChildNodes();
					for (int j = 0; j < subNodeList.getLength(); j++) {
						Node subNode = subNodeList.item(j);
						if (subNode.getNodeName().equals("ar")) {
							System.out.println("Uj ar: ");
							subNode.setTextContent(scanner.next());
						}
					}
				}
			}
		}
	}
	
	public static void vasTel(Document document, Scanner scanner) {
		//kikeresi tag alapjan
		NodeList nodeList = document.getElementsByTagName("vasarlo");
		System.out.println("Vasarlo ID: ");
		String input = scanner.next();
		for	(int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				//kikeresi id alapjan
				if (node.getAttributes().getNamedItem("vid").getTextContent().equals(input)) {
					//lekeri a child node-okatv
					NodeList subNodeList = node.getChildNodes();
					for (int j = 0; j < subNodeList.getLength(); j++) {
						Node subNode = subNodeList.item(j);
						if (subNode.getNodeName().equals("telefonszam")) {
							System.out.println("Uj telefonszam: ");
							subNode.setTextContent(scanner.next());
						}
					}
				}
			}
		}
	}
	
	public static void rakNev(Document document, Scanner scanner) {
		//kikeresi tag alapjan
		NodeList nodeList = document.getElementsByTagName("raktar");
		System.out.println("Raktar ID: ");
		String input = scanner.next();
		for	(int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				//kikeresi id alapjan
				if (node.getAttributes().getNamedItem("raid").getTextContent().equals(input)) {
					//lekeri a child node-okat
					NodeList subNodeList = node.getChildNodes();
					for (int j = 0; j < subNodeList.getLength(); j++) {
						Node subNode = subNodeList.item(j);
						if (subNode.getNodeName().equals("rnev")) {
							System.out.println("Uj nev: ");
							String name = scanner.next();
							name += scanner.nextLine();
							subNode.setTextContent(name);
						}
					}
				}
			}
		}
	}
	
	//kiiras xml-be
	public static void writeToXml(Document document) throws TransformerException, UnsupportedEncodingException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(new File("XMLB9ZL3Q.out.xml"));
		transf.transform(source, console);
		transf.transform(source, file);
	}
	
}
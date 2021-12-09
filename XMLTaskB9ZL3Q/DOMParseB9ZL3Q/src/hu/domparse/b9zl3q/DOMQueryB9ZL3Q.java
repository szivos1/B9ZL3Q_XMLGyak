package hu.domparse.b9zl3q;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMQueryB9ZL3Q {

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
            	System.out.println("0: Kilepes\n1: Bizonyos ar feletti termek \n2: Rendeles egyezo hatarido \n3: Adott kornal idosebb elado ");
            	switch(scanner.nextInt()) {		
            		case 1 : termAr(document, scanner);break;
            		case 2 : rendHat(document, scanner);break;
            		case 3 : ertKor(document, scanner);break;
					case 0 : exit=false;	
            		default : exit=false;
            	}
            }
            scanner.close();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
	}
	
	//termek aranak megvaltoztatasa
	public static void termAr(Document document,Scanner scanner) {
		//kikeresi tag alapjan
		NodeList nodeList = document.getElementsByTagName("termek");
		System.out.println("Adja meg minel nagyobb: ");
		Long input = scanner.nextLong();
		System.out.println("\n");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				//lekeri a child node-okat
				NodeList subNodeList = node.getChildNodes();
				for (int j = 0; j < subNodeList.getLength(); j++) {
				Node subNode = subNodeList.item(j);
				if(subNode.getNodeType() == Node.ELEMENT_NODE) {
					if (subNode.getNodeName().equals("ar") )
						if (Long.parseLong(subNode.getTextContent()) > input)
							listSub(node, "Termek");
					}
				}
			}
		}
	}
	
	public static void rendHat(Document document,Scanner scanner) {
		//kikeresi tag alapjan
		NodeList nodeList = document.getElementsByTagName("rendeles");
		System.out.println("Adja meg a hataridot: ");
		String input = scanner.next();
		System.out.println("\n");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				//lekeri a child node-okat
				NodeList subNodeList = node.getChildNodes();
				for (int j = 0; j < subNodeList.getLength(); j++) {
				Node subNode = subNodeList.item(j);
				if(subNode.getNodeType() == Node.ELEMENT_NODE) {
					if (subNode.getNodeName().equals("hatarido") )
						if (subNode.getTextContent().equals(input))
							listSub(node, "Rendeles");
					}
				}
			}
		}
	}
	
	public static void ertKor(Document document,Scanner scanner) {
		//kikeresi tag alapjan
		NodeList nodeList = document.getElementsByTagName("ertekesito");
		System.out.println("Adja meg a kort: ");
		int input = scanner.nextInt();
		System.out.println("\n");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				//lekeri a child node-okat
				NodeList subNodeList = node.getChildNodes();
				for (int j = 0; j < subNodeList.getLength(); j++) {
				Node subNode = subNodeList.item(j);
				if(subNode.getNodeType() == Node.ELEMENT_NODE) {
					if (subNode.getNodeName().equals("kor") )
						if (Integer.parseInt(subNode.getTextContent()) > input)
							listSub(node, "Ertekesito");
					}
				}
			}
		}
	}
	private static void listSub(Node node, String tag) {
		//id alapjan az elemek kilistazasa
		String out="";
		out +=tag + " ID : " + node.getAttributes().item(0).getTextContent()+"\n";
			Element element = (Element) node;
				switch(tag) {
				case "Termek":
					out += element.getElementsByTagName("tnev").item(0).getNodeName() + " : " +element.getElementsByTagName("tnev").item(0).getTextContent()+"\n";
					out += element.getElementsByTagName("ar").item(0).getNodeName() + " : " +element.getElementsByTagName("ar").item(0).getTextContent()+"\n";
				break;
				case "Rendeles":
					out += element.getElementsByTagName("datum").item(0).getNodeName() + " : " +element.getElementsByTagName("datum").item(0).getTextContent()+"\n";
					out += element.getElementsByTagName("hatarido").item(0).getNodeName() + " : " +element.getElementsByTagName("hatarido").item(0).getTextContent()+"\n";
				break;
				case "Ertekesito":
					out += element.getElementsByTagName("enev").item(0).getNodeName() + " : " +element.getElementsByTagName("enev").item(0).getTextContent()+"\n";
					out += element.getElementsByTagName("kor").item(0).getNodeName() + " : " +element.getElementsByTagName("kor").item(0).getTextContent()+"\n";
				break;
			}
		
		System.out.println(out);
	}
}

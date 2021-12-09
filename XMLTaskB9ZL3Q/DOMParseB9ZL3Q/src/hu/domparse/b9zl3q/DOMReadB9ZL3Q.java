package hu.domparse.b9zl3q;

import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.SAXException;
import org.w3c.dom.*;

public class DOMReadB9ZL3Q {

    public static void main(String[] args) {
		// DOMBuilder-rel  DOM letrheozasa
    	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance(); 
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File("XMLB9ZL3Q.xml"));
            document.getDocumentElement().normalize();
			//root elem megadasa
            Node root = document.getDocumentElement(); 
            listAll(root);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void listAll(Node root) {
		//a child elemek megadasa
    	NodeList nodeList = root.getChildNodes();
    	String vasarlo = "Vasarlok\n\n";
    	String ertekesito = "Ertekesitok\n\n";
    	String rendeles = "Rendelesek\n\n";
    	String termek = "Termekek\n\n";
    	String raktar = "Raktarak\n\n";
		String kapcsolatA = "ren_t\n\n";
		String kapcsolatB = "t_rak\n\n";
    	for (int i = 0; i < nodeList.getLength(); i++) {
    		Node node = nodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				//a child elemek child-jainak kivalassztasa
				NodeList subNodeList = nodeList.item(i).getChildNodes(); 
                for (int j = 0; j < subNodeList.getLength(); j++) {
                	Node subNode = subNodeList.item(j);
					//kigyujtes a definialt string-ekbe
        			if(subNode.getNodeType() == Node.ELEMENT_NODE) {
        				vasarlo += oneNode(subNode,"vasarlo");
        				ertekesito += oneNode(subNode,"ertekesito");
        				rendeles += oneNode(subNode,"rendeles");
        				termek += oneNode(subNode,"termek");
        				raktar += oneNode(subNode,"raktar");
						kapcsolatA += oneNode(subNode,"kapcsolatA");
						kapcsolatB += oneNode(subNode,"kapcsolatB");
        			}
                }
			}
    	}
    	//kiiratas
    	System.out.println(vasarlo);
    	System.out.println(ertekesito);
    	System.out.println(rendeles);
    	System.out.println(termek);
    	System.out.println(raktar);
    	System.out.println(kapcsolatA);
    	System.out.println(kapcsolatB);
    }
    
    public static String oneNode(Node subNode, String x) {
    	String out = "";
    	if(subNode.getNodeName().equals(x)) {
			if(subNode.getAttributes().getLength()>0)
			//ha a node megegyezik x-el és van attribute-ja, akkor kiiroik
			{
				out += x + " ID : " + subNode.getAttributes().item(0).getTextContent()+"\n";
			}
			NodeList subSubNodeList = subNode.getChildNodes();
			for (int k = 0; k < subSubNodeList.getLength(); k++) {
	    		Node subSubNode = subSubNodeList.item(k);
	    		if(subSubNode.getNodeType() == Node.ELEMENT_NODE) {
					//id alapjan elkuloniti
	    			switch(subSubNode.getNodeName()) {
	    			case "vid":
	    			case "eid":
	    			case "rid":
	    			case "tid":
					case "raid":
					//case "rentid":
					//case "trakid":
	    				out += subSubNode.getNodeName() + " : " + subSubNode.getAttributes().item(0).getTextContent() + "\n";
	    			break;
					//a child elemek komplex eleme
	    			case "cim":
					case "lakcim":
	    				NodeList subSubSubNode = subSubNode.getChildNodes();
                        for (int l = 0; l < subSubSubNode.getLength(); l++) {
                            if (subSubSubNode.item(l).getNodeType() == Node.ELEMENT_NODE) {
                            	out += subSubNode.getNodeName() + "-" + subSubSubNode.item(l).getNodeName() + " : " + subSubSubNode.item(l).getTextContent()+"\n";
                            }
                        }
	    			break;
	    			default:
					//az elem nevet es a kontextust String-be irja
	    			out += subSubNode.getNodeName() + " : " + subSubNode.getTextContent()+"\n";
	    			}
	    		}
			}
			out += "\n";
		}
    	return out;
    }

}

package src;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/*
 * Class XMLGraph is responsible for creating the list of cities with coordinates.
 */

public class XMLGraph {
	// List of cities
	List<Vertex> nodes = new ArrayList<Vertex>();
	
	{
		try {
				 
				File fXmlFile = new File("villes.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
			 
				doc.getDocumentElement().normalize();
			 
				NodeList nList = doc.getElementsByTagName("ville");
				
				for (int temp = 0; temp < nList.getLength(); temp++) {
			 
					Node nNode = nList.item(temp);
					
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			 
						Element eElement = (Element) nNode;
			 
						String s = eElement.getElementsByTagName("nom").item(0).getTextContent();
						String x = eElement.getElementsByTagName("longitude").item(0).getTextContent();
						String y = eElement.getElementsByTagName("latitude").item(0).getTextContent();
						String i = Integer.toString(temp);
					
						int x1 =  Integer.valueOf(x.trim());
						int y1 =  Integer.valueOf(y.trim());
						
						// Creating list of nodes
						Vertex v = new Vertex(i,s,x1,y1);
						nodes.add(temp,v);
						
					}
				}
				
			    } catch (Exception e) {
				e.printStackTrace();
			    }
			  }
	}
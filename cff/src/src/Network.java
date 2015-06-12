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
 * Class Network parse names and distance between cities from ville.xml file. Then creates set of nodes
 * and edges for a graph.
 */

public class Network {
	
	public Network() {}

	List<Vertex> cities = new ArrayList<Vertex>();
	List<Edge> edges = new ArrayList<Edge>();
	List<Edge> edges1 = new ArrayList<Edge>();
	// List of all connections
	List<Edge> linkList = new ArrayList<Edge>();
	XMLGraph gra = new XMLGraph();
	
	String i;
	String i1;
	int x;
	int y;
	int xs1;
	int ys1;
	
	{
		try {
				 
				File fXmlFile = new File("villes.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
			 
				doc.getDocumentElement().normalize();
			 
				NodeList nList = doc.getElementsByTagName("liaison");
			 
				int iter = 0;
				int iter1 = 0;
				for (int temp = 0; temp < nList.getLength(); temp++) {
			 
					Node nNode = nList.item(temp);
			 
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			 
						Element eElement = (Element) nNode;
						// Geting data from xml file
						String s = eElement.getElementsByTagName("vil_1").item(0).getTextContent();
						String s1 = eElement.getElementsByTagName("vil_2").item(0).getTextContent();
						String t = eElement.getElementsByTagName("temps").item(0).getTextContent();
						iter1 = temp + 1;
						String id = Integer.toString(temp);
						
						for(int z=0; z<gra.nodes.size(); z++){
							if(gra.nodes.get(z).getName().equals(s)){
								String idf = gra.nodes.get(z).getId();
								i = idf;
							}
							if(gra.nodes.get(z).getName().equals(s1)){
								String idf = gra.nodes.get(z).getId();
								i1 = idf;
							}
						}
						
						int w =  Integer.valueOf(t.trim());
						// Getting coordinates of cities
						for(int a = 0; a<gra.nodes.size(); a++){
							if(gra.nodes.get(a).getName().equals(s)){
								int x1 = gra.nodes.get(a).getX();
								x = x1;
								int y1 = gra.nodes.get(a).getY();
								y = y1;
							}
							if(gra.nodes.get(a).getName().equals(s1)){
								int x2 = gra.nodes.get(a).getX();
								xs1 = x2;
								int y2 = gra.nodes.get(a).getY();
								ys1 = y2;
							}
						}
						// Creating list of nodes
						Vertex v = new Vertex(i1,s1,xs1,ys1);
						Vertex v1 = new Vertex(i,s,x,y);
						
						cities.add(iter,v);
						cities.add(iter1,v1);
						
						// Creating list of edges
						Edge e = new Edge(id, v, v1, w);
						Edge e1 = new Edge(id, v1, v, w);
						edges.add(temp,e);
						edges1.add(temp,e1);
					
						iter = temp+2;
						
					}
				}
				// Merging list of all connections
				linkList.addAll(edges);
				linkList.addAll(edges1);
				
			    } catch (Exception e) {
				e.printStackTrace();
			    }
			  }
}

package src;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
 * Class Menu is responsible for interaction with user. There is written simple menu which allows
 * adding, removing nodes and edges of graph.
 */

public class Menu{
	
	public Menu(){}
	
	XMLGraph vertex = new XMLGraph();
	Network line =  new Network();
	Boolean exit_prog = false;
	String choose = null;
	String choose1 = null;
	String choose2 = null;

	List<Vertex> nodes;
	List<Edge> edges;
	Graph graph;
	Algorithm dijkstra;
	{
	
    nodes = vertex.nodes;
    edges = line.linkList;
    graph = new Graph(nodes, edges);
    dijkstra = new Algorithm(graph);
	{
	try{
		
		do {
			// Simple menu
			Scanner inp = new Scanner(System.in);
			System.out.println();
			System.out.println("==== MENU ====");
			System.out.println("End Program: 1");
			System.out.println("Print all cities: 2");
			System.out.println("Print all connections: 3");
			System.out.println("Find the shortest path between cities: 4");
			System.out.println("Remove city: 5");
			System.out.println("Add city: 6");
			System.out.println("Add connection: 7");
			System.out.println("Remove connection: 8");
			System.out.println("Draw a map: 9");
			
			choose = inp.next();
			
			switch(Integer.parseInt(choose))
			{
				case 1: exit_prog = true;
				System.out.println("----- Program was ended !!! -----");
				break;
				
				case 2:
					System.out.println("----- LIST OF ALL CITIES -----");
					for(int i=0; i<nodes.size(); i++){
						System.out.println(nodes.get(i) + " " + "ID: " + nodes.get(i).getId() + 
								" | " + "Coordinates: " + nodes.get(i).getX() + " " + nodes.get(i).getY());
				}
				
				break;
				case 3:
					System.out.println("----- LIST OF ALL CONNECTIONS -----");
					for(int i=line.linkList.size()/2; i<line.linkList.size()-1; i++){
						System.out.println(line.linkList.get(i) + "|" + "ID: " + line.linkList.get(i).getId() + " | " + "Distance: " + " " + line.linkList.get(i).getWeight());
				}
					
				break;
				case 4:
					for(int i=0; i<nodes.size(); i++)
					System.out.println(nodes.get(i) + " " + "ID: " + nodes.get(i).getId());
				
					System.out.println("----- Please, type in the following order the ID of: -----");
					System.out.println("----- Source city -----");
					System.out.println("----- Destination city -----");
					choose = inp.next();
					choose1 = inp.next();
					int ch = Integer.valueOf(choose);
					int ch1 = Integer.valueOf(choose1);
					int sum = 0;
					
					if(ch > nodes.size()-1 || ch1 > nodes.size()-1)
					{
						System.out.println("There is no city of such id !");
						break;
					}
					
					// Checking connectivity of graph
					if(graph.isConnected1(choose) == false || graph.isConnected2(choose1) == false)
					{	System.out.println("No connection");
						break;
					}
					
					// Execution of Dijkstra algorithm for given nodes
					dijkstra = new Algorithm(graph);
				    dijkstra.execute(nodes.get(ch));
				    LinkedList<Vertex> path = dijkstra.getPath(nodes.get(ch1));
				    
				    System.out.println("====== THE SHORTEST PATH BETWEEN" + nodes.get(ch) + "AND" + nodes.get(ch1) + "======");
				    for (Vertex vertex : path) {
				      System.out.println(vertex);
				    }
				    
				    // Finding sum of minimum distance between nodes
				    for(int a=0; a<path.size()-1; a++){
				    	sum = sum + dijkstra.getDistance(path.get(a), path.get(a+1));   
				    }
				    System.out.println("Minimum time between" + nodes.get(ch) + "and" + nodes.get(ch1)+ ": " + sum);
					
				break;
				case 5:
					for(int i=0; i<nodes.size(); i++)
						System.out.println(nodes.get(i) + " " + "ID: " + nodes.get(i).getId());
					System.out.println("----- Please, type in the ID of the city you want to remove: -----");
				
					choose = inp.next();
					int v = Integer.valueOf(choose);
					
					if(v > nodes.size())
					{
						System.out.println("There is no city of such id !");
						break;
					}
					
					nodes = graph.removeVertex(choose);
					vertex.nodes = nodes;
					edges = graph.removeEdge1(choose);
					
				break;
				
				case 6:
					// I add only name and id, without coordinates. Because, it is not possible to estimate them in reality.
					System.out.println("----- Please, type the name of the city you want to add: -----");
					
					int idNew = vertex.nodes.size();
					choose = inp.next();
					
					nodes = graph.addVertex(Integer.toString(idNew)," "+choose+" "); 
					vertex.nodes = nodes;
					System.out.println("----- Now, please add a connection. Choose option 7: -----");
					
				break;
				case 7:
					// First we have to add node to list of nodes (mode 6) before adding list of edges
					for(int i=0; i<nodes.size(); i++)
						System.out.println(nodes.get(i));
					System.out.println("----- Please, type in the following order: -----");
					System.out.println("----- Source city of connection-----");
					System.out.println("----- Destination city of connection -----");
					System.out.println("----- Distance between cities -----");
					
					choose = inp.next();
					choose1 = inp.next();
						
					choose2 = inp.next();
					int dist = Integer.valueOf(choose2);
					
					edges = graph.addEdge(" "+choose+" ", " "+choose1+" ",dist);
					
				break;
				case 8:
					// Removing connection between nodes
					for(int i=edges.size()/2; i<edges.size(); i++)
						System.out.println(edges.get(i) + " " + edges.get(i).getId());
					System.out.println("----- Please, type id of the connection: -----");
					
					choose = inp.next();
					
					edges = graph.removeEdge(choose);
					line.edges1 = edges;
					line.edges = line.edges1;
				
				break;
				case 9:
					// Drawing of graph which consists position of cities
					System.out.println("----- Drawing of cities's network -----");
					Point2D window = new Point2D();
					window.setVisible(true);
					break;		
			}
		}while(exit_prog == false);
			
	}catch(Exception e){
		System.out.println("Problem with menu." + e);
	}
	}
	}
}

package src;

import java.util.List;

/*
 * Class Graph creates a data structure from lists consisting vertices and edges of graph.
 * Here are implemented methods which allows to manipulate the graph.
 */
public class Graph {

	// Lists of vertices and edges of graph
	private List<Vertex> vertexes;
	private List<Edge> edges;
	boolean state = false;
	// Construction of the graph from lists of vertices and edges
	public Graph(List<Vertex> vertexes, List<Edge> edges) {
	    this.vertexes = vertexes;
	    this.edges = edges;
	}

	public List<Vertex> getVertexes() {
	    return vertexes;
	}

	public List<Edge> getEdges() {
	    return edges;
	}
	
	// Method checks if a given edge exists in a graph.
	public boolean checkIfexistConnection(String id, String id1)
	{
		for(int a=0; a<edges.size(); a++){
			if(edges.get(a).getSource().getId() == id && edges.get(a).getDestination().getId() == id1)
				return true;
			}
		return false;
	}
	
	// Checking connectivity
	public boolean isConnected1(String city1)
	{
		for(int a=0; a<edges.size(); a++){			
			if(edges.get(a).getSource().getId().equals(city1)){
				state = true;
				break;
			}
			else
				state = false;			
		}
		return state;
	}
	
	public boolean isConnected2(String city2)
	{
		for(int a=0; a<edges.size(); a++){			
			if(edges.get(a).getDestination().getId().equals(city2)){
				state = true;
				break;
			}
			else
				state = false;			
		}
		return state;
	}
	
	// Method checks if a given node exists in a graph.
	public boolean checkIfexist(String id, String name){
		for(int a=0; a<vertexes.size(); a++){
			if(vertexes.get(a).getName() != name && vertexes.get(a).getId() != id)
				return true;
		}
		return false;
	}
	
	public boolean checkIfexistnode(String id){
		for(int a=0; a<vertexes.size(); a++){
			if(vertexes.get(a).getId() == id){
				state = true;
			}
			else{
				state = false;
			}
		}
		
		return state;
	}
	
	public boolean checkIfexistNode(String name){
		for(int a=0; a<vertexes.size(); a++){
			if(vertexes.get(a).getName() == name){
				state = true;
				break;
			}
			else{
				state = false;
			}
		}
		
		return state;
	}
	
	// Method allows to add a vertex to list of nodes
	public List<Vertex> addVertex(String id, String name){
	    
		if(checkIfexist(id, name)){
			
			Vertex v = new Vertex(id, name);
			vertexes.add(v);
		}
		return vertexes;
	}
	
	// Methods allows to remove a vertex from list of nodes
	public List<Vertex> removeVertex(String id) {
		for(int i=0; i<vertexes.size(); i++){
			if(id.equals(vertexes.get(i).getId())){
				vertexes.remove(vertexes.get(i));
			}
		}
		return vertexes;
	}
	
	// Method allows to add an edge (connection) to a graph (between cities)
	public List<Edge> addEdge(String name1, String name2, int distance){
		Vertex x1 = new Vertex("", name1);
		Vertex x2 = new Vertex("", name2);
		
		for(int i=0; i<vertexes.size(); i++){
			if(vertexes.get(i).getName().equals(name1)){
				x1 = vertexes.get(i);
				System.out.println(x1);
			}
			if(vertexes.get(i).getName().equals(name2)){
				x2 = vertexes.get(i);
				System.out.println(x2);
			}	
		}
		
		if(x1.getId() == "" || x2.getId() == ""){
			System.out.println("One of them doesn't exist! Please, create city at first! Then you can add connection.");
		}
		
		String id = Integer.toString((edges.size()/2));
		String id1 = Integer.toString((edges.size()/2)+1);
		
		Edge e = new Edge(id, x1, x2, distance);
		
		Edge e1 = new Edge(id1, x2, x1, distance);
		
		edges.add(e);
		edges.add(e1);
		return edges;	
	}
	
	// Method allows to remove an edge (connection) from a graph (between cities)
	public List<Edge> removeEdge(String id) {
		for(int j=0; j<edges.size(); j++){
			if(edges.get(j).getId().equals(id)){
			edges.remove(edges.get(j));
			}
		}
		System.out.println(edges);
		return edges;
	}
	
	public List<Edge> removeEdge1(String id) {
		for(int j=0; j<edges.size(); j++){
			if(edges.get(j).getDestination().getId().equals(id) || edges.get(j).getSource().getId().equals(id)){
				edges.remove(edges.get(j));
			}
		}
		System.out.println(edges);
		
		return edges;
	}
}

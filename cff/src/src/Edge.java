package src;


/*
 * Class Edge is responsible for creation of object edge in a graph.
 * Each object contains vertices (source and destination), distance between them and coordinates of node
 * on a map.
 */

public class Edge {

	private String id; 
	private Vertex source;
	private Vertex destination;
	private int weight; 
	// Coordinates on a map
	private int x;
	private int y;
	
	  public Edge(String id, Vertex source, Vertex destination, int weight) {
	    this.id = id;
	    this.source = source;
	    this.destination = destination;
	    this.weight = weight;
	  }
	  
	  public Edge(String id, Vertex source, Vertex destination, int weight, int x, int y) {
		    this.id = id;
		    this.source = source;
		    this.destination = destination;
		    this.weight = weight;
		    this.setX(x);
		    this.setY(y);
	  }
	  
	  public Edge(String id, Vertex source, Vertex destination, Vertex s1, Vertex d1, int weight) {
		    this.id = id;
		    this.source = source;
		    this.destination = destination;
		    this.weight = weight;
	  }
	  
	  // Setters and getters methods for all field of object edge
	  public String getId() {
	    return id;
	  }
	  public Vertex getDestination() {
	    return destination;
	  }

	  public Vertex getSource() {
	    return source;
	  }
	  public int getWeight() {
	    return weight;
	  }
	  
	  @Override
	  public String toString() {
	    return source + " " + destination;
	  }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}

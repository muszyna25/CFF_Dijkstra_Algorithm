package src;


/*
 * Class Vertex creates node of graph. Each node contains ID, name and coordinates on a map.
 */

public class Vertex {

	private String id;
	private String name;
	private int x;
	private int y;
	  
	public Vertex(){}
	
	public Vertex(String id, String name) 
	{
		this.id = id;
	    this.name = name;
	}
	
	public Vertex(String id, String name, int x, int y) 
	{
		this.id = id;
	    this.name = name;
	    this.setX(x);
	    this.setY(y);
	}
	  
	// Method to get id node
	public String getId() {
		return id;
	}

	// Methods to get name of node
	public String getName() {
	    return name;
	}
	  
	@Override
	  public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((id == null) ? 0 : id.hashCode());
	    return result;
	  }
	
	// Methods to compare objects of this class
	  @Override
	  public boolean equals(Object obj) {
	    if (this == obj)
	      return true;
	    if (obj == null)
	      return false;
	    if (getClass() != obj.getClass())
	      return false;
	    Vertex other = (Vertex) obj;
	    if (id == null) {
	      if (other.id != null)
	        return false;
	    } else if (!id.equals(other.id))
	      return false;
	    return true;
	  }

	// Setters and getters methods for each field of Vertex
	@Override
	public String toString() {
	    return name;
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

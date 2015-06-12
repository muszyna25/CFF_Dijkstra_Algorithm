package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Class Algorithm is responsible for implementation of Dijkstra Algorithm for finding the shortest
 * path between to nodes in a given graph.
 * Algorithm partitions all nodes into two sets. 
 * Unsettled and settled. Initially all nodes are in the unsettled sets.
 * A node is moved to the settled set if a shortest path from the source to this node has been found.
 * Initially the distance of each node to the source is set to a very high value.
 * First only the source is in the set of unsettledNodes.
 * The algorithms runs until the unsettledNodes are empty. In each iteration it 
 * selects the node with the lowest distance from the source out the unsettled nodes. 
 * If reads all edges which are outgoing from the source and evaluates for each destination 
 * node in these edges which is not yet settled if the known distance from the source to this 
 * node can be reduced if the selected edge is used. If this can be done then the distance 
 * is updated and the node is added to the nodes which need evaluation.
 * Note that Dijkstra also determines the predsuccessor of each node on its way to the source. 
 */

public class Algorithm {
	
	  // List of nodes and edges in a graph
	  private final List<Vertex> nodes;
	  private final List<Edge> edges;
	  // Nodes which are settled
	  private Set<Vertex> settledNodes;
	  // Nodes which are not settled
	  private Set<Vertex> unSettledNodes;
	  // Nodes which are to remember the path
	  private Map<Vertex, Vertex> predecessors;
	  // Set of distance between nodes
	  private Map<Vertex, Integer> distance;
	  public int sum;

	  public Algorithm(Graph graph) {
	    this.nodes = new ArrayList<Vertex>(graph.getVertexes());
	    this.edges = new ArrayList<Edge>(graph.getEdges());
	  }

	  //It does not use any PriorityQueue for the UnSettledNodes 
	  //to make the algorithms as simple as possible.
	  //Getting a beginning vertex of path
	  public void execute(Vertex source) {
	    settledNodes = new HashSet<Vertex>();
	    unSettledNodes = new HashSet<Vertex>();
	    distance = new HashMap<Vertex, Integer>();
	    predecessors = new HashMap<Vertex, Vertex>();
	    distance.put(source, 0);
	    // First only the source is in the set of unsettledNodes.
	    unSettledNodes.add(source);
	    // The algorithms runs until the unsettledNodes are empty
	    while (unSettledNodes.size() > 0) {
	      // Getting node minimum distance
	      Vertex node = getMinimum(unSettledNodes);
	      settledNodes.add(node);
	      unSettledNodes.remove(node);
	      // Finding minimal distance 
	      findMinimalDistances(node);
	    }
	  }

	  // Method for finding minimal distance between vertices
	  public void findMinimalDistances(Vertex node) {
	    List<Vertex> adjacentNodes = getNeighbors(node);
	    for (Vertex target : adjacentNodes) {
	      if (getShortestDistance(target) > getShortestDistance(node)
	          + getDistance(node, target)) {
	        distance.put(target, getShortestDistance(node)
	            + getDistance(node, target));
	        predecessors.put(target, node);
	        unSettledNodes.add(target);
	      }
	    }

	  }

	  //Method is getting the distance between two nodes on the basis of weight
	  public int getDistance(Vertex node, Vertex target) {
	    for (Edge edge : edges) {
	      if (edge.getSource().equals(node)
	          && edge.getDestination().equals(target)) {
	        return edge.getWeight();
	      }
	    }
	    throw new RuntimeException("No connection");
	  }

	  // Method gets neighbours of given node which are not settled
	  public List<Vertex> getNeighbors(Vertex node) {
	    List<Vertex> neighbors = new ArrayList<Vertex>();
	    for (Edge edge : edges) {
	      if (edge.getSource().equals(node)
	          && !isSettled(edge.getDestination())) {
	        neighbors.add(edge.getDestination());
	      }
	    }
	    return neighbors;
	  }

	  // Finding minimum distance between vertices
	  public Vertex getMinimum(Set<Vertex> vertexes) {
	    Vertex minimum = null;
	    for (Vertex vertex : vertexes) {
	      if (minimum == null) {
	        minimum = vertex;
	      } else {
	        if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
	          minimum = vertex;
	        }
	      }
	    }
	    return minimum;
	  }

	  // Method checks if given node is settled
	  private boolean isSettled(Vertex vertex) {
	    return settledNodes.contains(vertex);
	  }

	  // Method gets the shortest way to vertex destination
	  public int getShortestDistance(Vertex destination) {
	    Integer d = distance.get(destination);
	    if (d == null) {
	      return Integer.MAX_VALUE;
	    } else {
	      return d;
	    }
	  }

	  
	   // Method returns the path from the source to the destination and NULL if no path exists
	   // It means that it checks connectivity of graph.
	  public LinkedList<Vertex> getPath(Vertex target) {
	    LinkedList<Vertex> path = new LinkedList<Vertex>();
	    Vertex step = target;
	    // It checks if a path exists
	    if (predecessors.get(step) == null) {
	      return null;
	    }
	    path.add(step);
	    while (predecessors.get(step) != null) {
	      step = predecessors.get(step);
	      path.add(step);
	    }
	    // It puts in correct order nodes
	    Collections.reverse(path);
	    return path;
	  }
	  
	public List<Vertex> getNodes() {
		return nodes;
	}
}

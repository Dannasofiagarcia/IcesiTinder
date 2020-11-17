package data_structures.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph<V> {

	private int numVertices;
	private int numEdges;
	private Node<V> current;

	private Map<Integer, Node<V>> nodes; //adjList
	
	private Map<Node<V>, ArrayList<Node<V>>> adjacencyList;
	
	

	public int getNumVertices() {
		return numVertices;
	}

	public void setNumVertices(int numVertices) {
		this.numVertices = numVertices;
	}

	public int getNumEdges() {
		return numEdges;
	}

	public void setNumEdges(int numEdges) {
		this.numEdges = numEdges;
	}

	public Node<V> getCurrent() {
		return current;
	}

	public void setCurrent(Node<V> current) {
		this.current = current;
	}

	public Map<Integer, Node<V>> getNodes() {
		return nodes;
	}

	public void setNodes(Map<Integer, Node<V>> nodes) {
		this.nodes = nodes;
	}

	public Graph() {
		numVertices = 0;
		numEdges = 0;
		nodes = new HashMap<Integer, Node<V>>();
	}

	public void addNode(V value) {
		nodes.put(value.hashCode(), new Node<V>(value));
	}

	public void addConnection(Integer hashCodeOrigin, Integer hashCodeDestination, int weight) {
		Node<V> origin = nodes.get(hashCodeOrigin), destination = nodes.get(hashCodeDestination);
		origin.addEdge(new Edge<V>(origin, destination, weight));
	}

	public void addConnectionToAdjacencyList(Integer hashCodeOrigin, Integer hashCodeDestination, int weight) {
		
	}
}

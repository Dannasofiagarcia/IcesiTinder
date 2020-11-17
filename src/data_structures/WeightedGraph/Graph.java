package data_structures.WeightedGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import data_structures.DijkstraAlgorithm;

public class Graph<V> {

	private Map<Integer, Node<V>> adjList;

	public Graph() {

		adjList = new HashMap<Integer, Node<V>>();
	}

	public Map<Integer, Node<V>> getNodes() {
		return adjList;
	}

	public void setNodes(Map<Integer, Node<V>> nodes) {
		this.adjList = nodes;
	}

	public void addNode(V value) {
		adjList.put(value.hashCode(), new Node<V>(value));
	}

	public void addConnection(Integer hashCodeOrigin, Integer hashCodeDestination, int weight) {
		Node<V> origin = adjList.get(hashCodeOrigin), destination = adjList.get(hashCodeDestination);
		origin.addEdge(new Edge<V>(origin, destination, weight));
		destination.addEdge(new Edge<V>(destination, origin, weight));

	}

	public List<Node<V>> bfs(int hashCodeSource) {

		List<Node<V>> list = adjList.get(hashCodeSource).bfs();
		Collections.reverse(list);

		return list;

	}

	public List<Node<V>> dfs(int source) {

		List<Node<V>> list = adjList.get(source).dfs();

		return list;
		
	}

	public LinkedList<Node<V>> dijkstra(Integer hashCodeOrigin, Integer hashCodeDestination) {

		List<Node<V>> nodes = new ArrayList<>(adjList.values());

		List<Edge> edges = new ArrayList<>();

		for (int i = 0; i < nodes.size(); i++) {
			edges.addAll(nodes.get(i).getEdges());
		}

		DijkstraAlgorithm<V> dijkstra = new DijkstraAlgorithm(edges);

		dijkstra.execute(adjList.get(hashCodeOrigin));

		LinkedList<Node<V>> path = dijkstra.getPath(adjList.get(hashCodeDestination));

		return path;

	}

}
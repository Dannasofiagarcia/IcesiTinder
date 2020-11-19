package data_structures.WeightedGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph<V> {

	private Map<Integer, Node<V>> adjList;
	private final int defaultNumNodes = 5;
	private int[][] nodesAdjMatrix;
	private int numVertices;
	private int numEdges;

	public Graph() {

		adjList = new HashMap<Integer, Node<V>>();
		numVertices = 0;
		numEdges = 0;
		nodesAdjMatrix = new int[defaultNumNodes][defaultNumNodes];
		for (int i = 0; i < nodesAdjMatrix.length; i++) {
			for (int j = 0; j < nodesAdjMatrix[i].length; j++) {
				nodesAdjMatrix[i][j] = 0;
			}
		}
	}

	public Map<Integer, Node<V>> getNodes() {
		return adjList;
	}

	public void setNodes(Map<Integer, Node<V>> nodes) {
		this.adjList = nodes;
	}

	public void addNode(V value) {
		adjList.put(value.hashCode(), new Node<V>(value));

		addNodeAdjMatrix();
		adjList.get(value.hashCode()).setIndexMatrix(numVertices - 1);
	}

	public int[][] getNodesAdjMatrix() {
		return nodesAdjMatrix;
	}

	public void setNodesAdjMatrix(int[][] nodesAdjMatrix) {
		this.nodesAdjMatrix = nodesAdjMatrix;
	}

	public void addConnection(Integer hashCodeOrigin, Integer hashCodeDestination, int weight) {
		Node<V> origin = adjList.get(hashCodeOrigin), destination = adjList.get(hashCodeDestination);
		origin.addEdge(new Edge<V>(origin, destination, weight));
		destination.addEdge(new Edge<V>(destination, origin, weight));

		nodesAdjMatrix[origin.getIndexMatrix()][destination.getIndexMatrix()] = weight;
		nodesAdjMatrix[destination.getIndexMatrix()][origin.getIndexMatrix()] = weight;

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
/* 
	public List<Node<V>> dijkstraForAdjaMatrix(Integer hashCodeOrigin, Integer hashCodeDestination) {

		int o = adjList.get(hashCodeOrigin).getIndexMatrix(), d = adjList.get(hashCodeOrigin).getIndexMatrix();

		DijkstraAlgorithmForAdjacencyMatrix dijkstra = new DijkstraAlgorithmForAdjacencyMatrix();

		int[][] newMatrix = new int[nodesAdjMatrix.length - 1][nodesAdjMatrix.length - 1];

		for (int i = 0; i < newMatrix.length - 1; i++) {
			for (int j = 0; j < newMatrix.length - 1; j++) {
				newMatrix[i][j] = nodesAdjMatrix[i][j];
			}
		}
		int[][] miGrafo = newMatrix;
		for (int x = 0; x < miGrafo.length; x++) {
			System.out.print("|");
			for (int y = 0; y < miGrafo[x].length; y++) {
				System.out.print(miGrafo[x][y]);
				if (y != miGrafo[x].length - 1)
					System.out.print("\t");
			}
			System.out.println("|");
		}
		dijkstra.dijkstra(newMatrix, o, d, numVertices);

		List<Integer> pathWihtIndices = dijkstra.getShortesPath();
		List<Node<V>> temporalNodes = new ArrayList<>();
		temporalNodes.addAll(adjList.values());

		List<Node<V>> shortestPath = new ArrayList<>();
		System.out.println("tamaño temporales" + temporalNodes.size() + "  tamaño camino " + pathWihtIndices.size());

		for (int i = 0; i < temporalNodes.size(); i++) {
			for (int j = 0; j < pathWihtIndices.size(); j++) {
				System.out.println("entrooo");

				if (temporalNodes.get(i).getIndexMatrix() == (int) pathWihtIndices.get(j)) {
					shortestPath.add(temporalNodes.get(i));
				}
			}
		}
		return shortestPath;

	} */

	public List<Node<V>> dijkstraForAdjaList(Integer hashCodeOrigin, Integer hashCodeDestination) {

		List<Node<V>> nodes = new ArrayList<>(adjList.values());

		@SuppressWarnings("rawtypes")
		List<Edge> edges = new ArrayList<>();

		for (int i = 0; i < nodes.size(); i++) {
			edges.addAll(nodes.get(i).getEdges());
		}

		@SuppressWarnings("rawtypes")
		DijkstraAlgorithmForAdjacencyList<V> dijkstra = new DijkstraAlgorithmForAdjacencyList(edges);

		dijkstra.execute(adjList.get(hashCodeOrigin));

		LinkedList<Node<V>> path = dijkstra.getPath(adjList.get(hashCodeDestination));

		List<Node<V>> l = new ArrayList<>();
		l.addAll(path);

		return path;

	}

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

	public void addNodeAdjMatrix() {
		numVertices++;

		int v = getNumVertices();
		if (v >= nodesAdjMatrix.length) {
			int[][] newNodesAdjMatrix = new int[v + 1][v + 1];
			for (int i = 0; i < nodesAdjMatrix.length; i++) {
				for (int j = 0; j < nodesAdjMatrix.length; j++) {
					newNodesAdjMatrix[i][j] = nodesAdjMatrix[i][j];

				}
			}
			nodesAdjMatrix = newNodesAdjMatrix;
		}
		for (int i = 0; i < nodesAdjMatrix[v].length; i++) {
			nodesAdjMatrix[v][i] = 0;
			nodesAdjMatrix[i][v] = 0;
		}

		for (int i = 0; i < nodesAdjMatrix.length; i++) {
			for (int j = 0; j < nodesAdjMatrix[i].length; j++) {
				if (nodesAdjMatrix[i][j] == 0) {
					nodesAdjMatrix[i][j] = 0;
				}
			}
		}

	}

	public int distance[] = new int[numVertices];
	public int cost[][] = new int[10][10];

	public void dijkstra2(int n, int s) {
		int flag[] = new int[n + 1];
		int i, minpos = 1, k, c, minimum;

		for (i = 1; i <= n; i++) {
			flag[i] = 0;
			this.distance[i] = this.nodesAdjMatrix[s][i];
		}
		c = 2;
		while (c <= n) {
			minimum = 99;
			for (k = 1; k <= n; k++) {
				if (this.distance[k] < minimum && flag[k] != 1) {
					minimum = this.distance[i];
					minpos = k;
				}
			}
			flag[minpos] = 1;
			c++;
			for (k = 1; k <= n; k++) {
				if (this.distance[minpos] + this.nodesAdjMatrix[minpos][k] < this.distance[k] && flag[k] != 1)
					this.distance[k] = this.distance[minpos] + this.nodesAdjMatrix[minpos][k];
			}
		}
	}
}
package data_structures.WeightedGraph;

import java.util.ArrayList;
import java.util.List;

public class Node<V> {

    private V value;

    private List<Edge<V>> edges;

    private boolean visited;

    private int indexMatrix;
    public int getIndexMatrix() {
		return indexMatrix;
	}

	public void setIndexMatrix(int indexMatrix) {
		this.indexMatrix = indexMatrix;
	}

	public Node(V value) {
        this.value = value;
        edges = new ArrayList<Edge<V>>();
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public List<Edge<V>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<V>> edges) {
        this.edges = edges;
    }

    public boolean addEdge(Edge<V> edge) {
        return edges.add(edge);
    }

    public List<Node<V>> dfs() {

        List<Node<V>> aN = new ArrayList<Node<V>>();
        visited = true;

        aN.add(this);

        for (int i = 0; i < edges.size(); i++) {

            if (!edges.get(i).getDestination().visited) {
                aN.addAll(edges.get(i).getDestination().dfs());
            }

        }

        return aN;

    }

    public List<Node<V>> bfs() {

        List<Node<V>> aN = new ArrayList<Node<V>>();
        visited = true;

        for (int i = 0; i < edges.size(); i++) {

            if (!edges.get(i).getDestination().visited) {
                aN.addAll(edges.get(i).getDestination().bfs());
            }

        }
        aN.add(this);

        return aN;

    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}
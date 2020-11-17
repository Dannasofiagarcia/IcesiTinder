package data_structures.graph;

import java.util.ArrayList;
import java.util.List;

public class Node<V> {

    private V value;

    private List<Edge<V>> edges;

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

}

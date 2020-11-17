package data_structures.WeightedGraph;

public class Edge<V> {
    private Node<V> origin;
    private Node<V> destination;
    private int weight;

    public Edge(Node<V> origin, Node<V> destination, int weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }

    public Node<V> getOrigin() {
        return origin;
    }

    public void setOrigin(Node<V> origin) {
        this.origin = origin;
    }

    public Node<V> getDestination() {
        return destination;
    }

    public void setDestination(Node<V> destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "";
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
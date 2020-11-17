package data_structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import data_structures.WeightedGraph.Edge;
import data_structures.WeightedGraph.Node;

public class DijkstraAlgorithm<V> {

    private final List<Edge<V>> edges;
    private Set<Node<V>> settledNodes;
    private Set<Node<V>> unSettledNodes;
    private Map<Node<V>, Node<V>> predecessors;
    private Map<Node<V>, Integer> distance;

    public DijkstraAlgorithm(List<Edge<V>> edges) {
        this.edges = edges;
    }

    public void execute(Node<V> source) {
        settledNodes = new HashSet<Node<V>>();
        unSettledNodes = new HashSet<Node<V>>();
        distance = new HashMap<Node<V>, Integer>();
        predecessors = new HashMap<Node<V>, Node<V>>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Node<V> node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Node<V> node) {
        List<Node<V>> adjacentNodes = getNeighbors(node);
        for (Node<V> target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Node<V> node, Node<V> target) {
        for (Edge<V> edge : edges) {
            if (edge.getOrigin().equals(node) && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Node<V>> getNeighbors(Node<V> node) {
        List<Node<V>> neighbors = new ArrayList<Node<V>>();
        for (Edge<V> edge : edges) {
            if (edge.getOrigin().equals(node) && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Node<V> getMinimum(Set<Node<V>> nodes) {
        Node<V> minimum = null;
        for (Node<V> node : nodes) {
            if (minimum == null) {
                minimum = node;
            } else {
                if (getShortestDistance(node) < getShortestDistance(minimum)) {
                    minimum = node;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Node<V> node) {
        return settledNodes.contains(node);
    }

    private int getShortestDistance(Node<V> destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and NULL
     * if no path exists
     */
    public LinkedList<Node<V>> getPath(Node<V> target) {
        LinkedList<Node<V>> path = new LinkedList<Node<V>>();
        Node<V> step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}

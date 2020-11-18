package ui;

import java.util.List;

import data_structures.WeightedGraph.Graph;
import data_structures.WeightedGraph.Node;
import model.User;

public class Main {

    public static void main(String[] args) {
        Graph<User> miGrafo = new Graph<User>();

        User a = new User("andrea", "sabsajhds");
        User b = new User("danna", "asdas");
        User c = new User("escobar", "122s");
        User d = new User("cordoba", "c3s");
        User e = new User("reyes", "12ss");

        miGrafo.addNode(a);
        miGrafo.addNode(b);
        miGrafo.addNode(c);
        miGrafo.addNode(d);
        miGrafo.addNode(e);

        miGrafo.addConnection(a.hashCode(), d.hashCode(), 3);

        miGrafo.addConnection(a.hashCode(), c.hashCode(), 20);

        miGrafo.addConnection(d.hashCode(), c.hashCode(), 2);

        miGrafo.addConnection(c.hashCode(), e.hashCode(), 3);

        miGrafo.addConnection(a.hashCode(), b.hashCode(), 3);

        List<Node<User>> path = miGrafo.dijkstraForAdjaList(a.hashCode(), e.hashCode());

        for (Node<User> node : path) {
            System.out.println(node.getValue().userName);
        }

    }

}
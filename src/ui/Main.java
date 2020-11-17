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

        miGrafo.addConnection(a.hashCode(), c.hashCode(), 3);

        miGrafo.addConnection(d.hashCode(), c.hashCode(), 3);

        miGrafo.addConnection(c.hashCode(), e.hashCode(), 3);

        miGrafo.addConnection(a.hashCode(), b.hashCode(), 3);

        List<Node<User>> amplitud = miGrafo.bfs(a.hashCode());

        System.out.println("POR AMPLITUD\n");
        for (int i = 0; i < amplitud.size(); i++) {
            System.out.println(amplitud.get(i).getValue().userName);
            amplitud.get(i).setVisited(false);
        }

        List<Node<User>> profundidad = miGrafo.dfs(a.hashCode());
        System.out.println("\n\nPOR PROFUNDIDAD\n");

        for (int i = 0; i < profundidad.size(); i++) {
            System.out.println(profundidad.get(i).getValue().userName);
        }

    }

}

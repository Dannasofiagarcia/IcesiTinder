package data_structures.WeightedGraph;

import model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DijkstraAlgorithmForAdjacencyListTest {

    private DijkstraAlgorithmForAdjacencyList myDijkstra;
    private Graph<User> miGrafo;
    private User a,b,c,d,e;


    public void setUp1() {
        miGrafo = new Graph<User>();

        a = new User("andrea", "sabsajhds");
        b = new User("danna", "asdas");
        c = new User("escobar", "122s");
        d = new User("cordoba", "c3s");
        e = new User("reyes", "12ss");

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


    }

    @Test
    void dijkstraTest() {
        setUp1();

        List<Node<User>> nodes = new ArrayList<Node<User>>(miGrafo.getNodes().values());
        @SuppressWarnings("rawtypes")
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            edges.addAll(nodes.get(i).getEdges());
        }

        myDijkstra = new DijkstraAlgorithmForAdjacencyList(edges);

        String expected = "andreacordobaescobarreyes";
        String actual = "";

        myDijkstra.execute(miGrafo.getNodes().get(a.hashCode()));

        LinkedList<Node<User>> temp = myDijkstra.getPath(miGrafo.getNodes().get(e.hashCode()));
        List<Node<User>> path = new ArrayList<>();
        path.addAll(temp);

        for (Node<User> node : path)
        {
            actual += node.getValue().userName;
        }
        assertEquals(expected, actual);
    }

    @Test
    void dijkstraTest2() {
        setUp1();

        List<Node<User>> nodes = new ArrayList<Node<User>>(miGrafo.getNodes().values());
        @SuppressWarnings("rawtypes")
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            edges.addAll(nodes.get(i).getEdges());
        }

        myDijkstra = new DijkstraAlgorithmForAdjacencyList(edges);

        String expected = "dannaandreacordobaescobar";
        String actual = "";

        myDijkstra.execute(miGrafo.getNodes().get(b.hashCode()));

        LinkedList<Node<User>> temp = myDijkstra.getPath(miGrafo.getNodes().get(c.hashCode()));
        List<Node<User>> path = new ArrayList<>();
        path.addAll(temp);

        for (Node<User> node : path)
        {
            actual += node.getValue().userName;
        }
        assertEquals(expected, actual);
    }

    @Test
    void dijkstraTest3() {
        setUp1();

        List<Node<User>> nodes = new ArrayList<Node<User>>(miGrafo.getNodes().values());
        @SuppressWarnings("rawtypes")
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++) {
            edges.addAll(nodes.get(i).getEdges());
        }

        myDijkstra = new DijkstraAlgorithmForAdjacencyList(edges);

        String expected = "reyesescobarcordobaandrea";
        String actual = "";

        myDijkstra.execute(miGrafo.getNodes().get(e.hashCode()));

        LinkedList<Node<User>> temp = myDijkstra.getPath(miGrafo.getNodes().get(a.hashCode()));
        List<Node<User>> path = new ArrayList<>();
        path.addAll(temp);

        for (Node<User> node : path)
        {
            actual += node.getValue().userName;
        }
        assertEquals(expected, actual);
    }

}

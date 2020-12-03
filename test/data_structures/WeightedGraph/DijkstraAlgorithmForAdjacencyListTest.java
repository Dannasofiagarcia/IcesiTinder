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
	private User a, b, c, d, e;
	public final static String[] FACULTIES = { "Ingenieria", "Ciencias Administrativas y Economicas",
            "Ciencias de la Salud", "Derecho", "Ciencias Naturales", "Ciencias de la Educación" };
	public void setUp1() {
		miGrafo = new Graph<User>();

		a = new User("andrea", "pinina", "Andrea Nuñez", FACULTIES[0], "andrea.nr2000@gmail.com", 'f', 1);
		b = new User("danna", "cloe", "Danna Garcia", FACULTIES[0], "dannita123@yahoo.com", 'f', 1);
		c = new User("escobar", "chumby", "Camilo Escobar Junior", FACULTIES[0],
				"traquetoMan123@yahoo.com", 'm', 1);
		d = new User("cordoba", "theBestPassword", "Camilo Cordoba", FACULTIES[0], "kamneklogs@gmail.com", 'm',
				1);
		e = new User("reyes", "iLoveTheCode", "Juan Manuel Reyes", FACULTIES[0], "seyerman@gmail.com", 'm', 1);

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

		for (Node<User> node : path) {
			actual += node.getValue().getUserName();
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

		for (Node<User> node : path) {
			actual += node.getValue().getUserName();
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

		for (Node<User> node : path) {
			actual += node.getValue().getUserName();
		}
		assertEquals(expected, actual);
	}

}

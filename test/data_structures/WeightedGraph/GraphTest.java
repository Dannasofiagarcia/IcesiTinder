package data_structures.WeightedGraph;

import model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphTest {

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
    void dfsTest1() {
        setUp1();
        String expected = "andreacordobaescobarreyesdanna";
        String actual = "";
        List<Node<User>> temp = miGrafo.dfs(a.hashCode());
        for (Node<User> node: temp
             ) {
            actual += node.getValue().getUserName();
        }
        assertEquals(expected, actual);
    }

    @Test
    void dfsTest2() {
        setUp1();
        String expected = "dannaandreacordobaescobarreyes";
        String actual = "";
        List<Node<User>> temp = miGrafo.dfs(b.hashCode());
        for (Node<User> node: temp
        ) {
            actual += node.getValue().getUserName();
        }
        assertEquals(expected, actual);
    }

    @Test
    void dfsTest3() {
        setUp1();
        String expected = "escobarandreacordobadannareyes";
        String actual = "";
        List<Node<User>> temp = miGrafo.dfs(c.hashCode());
        for (Node<User> node: temp
        ) {
            actual += node.getValue().getUserName();
        }
        assertEquals(expected, actual);
    }


    @Test
    void bfsTest1() {
        setUp1();
        String expected = "andreadannacordobaescobarreyes";
        String actual = "";
        List<User> temp = miGrafo.bfs(a.hashCode());
        for (User node: temp
        ) {
            actual += node.getUserName();
        }
        assertEquals(expected, actual);
    }

    @Test
    void bfsTest2() {
        setUp1();
        String expected = "dannaandreacordobaescobarreyes";
        String actual = "";
        List<User> temp = miGrafo.bfs(b.hashCode());
        for (User node: temp
        ) {
            actual += node.getUserName();
        }
        assertEquals(expected, actual);
    }

    @Test
    void bfsTest3() {
        setUp1();
        String expected = "reyesescobarandreadannacordoba";
        String actual = "";
        List<User> temp = miGrafo.bfs(e.hashCode());
        for (User node: temp
        ) {
            actual += node.getUserName();
        }
        assertEquals(expected, actual);
    }

    @Test
    void addNodeTest1() {
    	setUp1();
    	User aux = new User("aristi", "profe de andrea", "ARISTI", FACULTIES[0], "aristi@gmail.com", 'm', 1);
    	miGrafo.addNode(aux);
    	assertEquals(aux.hashCode(), miGrafo.getNodes().get(aux.hashCode()).getValue().hashCode());
    }

    @Test
    void addNodeTest2() {
     	setUp1();
    	User aux = new User("lordPiedrahita666", "director de Hogwarts", "Piedrahita", FACULTIES[0], "p@gmail.com", 'm', 1);
    	miGrafo.addNode(aux);
    	assertEquals(aux.hashCode(), miGrafo.getNodes().get(aux.hashCode()).getValue().hashCode());
    }

    @Test
    void deleteNodeTest1() {
    	setUp1();
        assertTrue(miGrafo.removeNode(a.hashCode()));
    }

    @Test
    void deleteNodeTest2() {
    	setUp1();
        assertTrue(miGrafo.removeNode(b.hashCode()));
    }

    @Test
    void searchNodeTest1() {
    	setUp1();
    	assertEquals(a, miGrafo.searchNode(a.hashCode()).getValue());
    }

    @Test
    void searchNodeTest2() {
    	setUp1();
    	assertEquals(b, miGrafo.searchNode(b.hashCode()).getValue());
    }

    @Test
    void searchNodeTest3() {
    	setUp1();
    	assertNull(miGrafo.searchNode(123));
    }

}

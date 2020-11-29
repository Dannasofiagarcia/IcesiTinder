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
    void dfsTest1() {
        setUp1();
        String expected = "andreacordobaescobarreyesdanna";
        String actual = "";
        List<Node<User>> temp = miGrafo.dfs(a.hashCode());
        for (Node<User> node: temp
             ) {
            actual += node.getValue().userName;
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
            actual += node.getValue().userName;
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
            actual += node.getValue().userName;
        }
        assertEquals(expected, actual);
    }


    @Test
    void bfsTest1() {
        setUp1();
        String expected = "andreadannacordobaescobarreyes";
        String actual = "";
        List<Node<User>> temp = miGrafo.bfs(a.hashCode());
        for (Node<User> node: temp
        ) {
            actual += node.getValue().userName;
        }
        assertEquals(expected, actual);
    }

    @Test
    void bfsTest2() {
        setUp1();
        String expected = "dannaandreacordobaescobarreyes";
        String actual = "";
        List<Node<User>> temp = miGrafo.bfs(b.hashCode());
        for (Node<User> node: temp
        ) {
            actual += node.getValue().userName;
        }
        assertEquals(expected, actual);
    }

    @Test
    void bfsTest3() {
        setUp1();
        String expected = "reyesescobarandreadannacordoba";
        String actual = "";
        List<Node<User>> temp = miGrafo.bfs(e.hashCode());
        for (Node<User> node: temp
        ) {
            actual += node.getValue().userName;
        }
        assertEquals(expected, actual);
    }

    @Test
    void addNodeTest1() {
    	setUp1();
    	User aux = new User("aristi", "profe de andrea");
    	miGrafo.addNode(aux);
    	assertEquals(aux.hashCode(), miGrafo.getNodes().get(aux.hashCode()).getValue().hashCode());
    }
    
    @Test
    void addNodeTest2() {
     	setUp1();
    	User aux = new User("lordPiedrahita666", "director de Hogwarts");
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

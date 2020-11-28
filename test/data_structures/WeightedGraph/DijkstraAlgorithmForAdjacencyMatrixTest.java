package data_structures.WeightedGraph;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DijkstraAlgorithmForAdjacencyMatrixTest {

    private DijkstraAlgorithmForAdjacencyMatrix myDijkstra;
    int[][] cost;


    public void setUp1() {
        myDijkstra = new DijkstraAlgorithmForAdjacencyMatrix();
        cost = new int[][]{
                {0, 3, 20, 3, 0},
                {3, 0, 0, 0, 0},
                {20, 0, 0, 2, 3},
                {3, 0, 2, 0, 0},
                {0, 0, 3, 0, 0},
        };


    }

    @Test
    void dijkstraTest() {
        setUp1();
        String expected = "0324";
        String actual = "";
        myDijkstra.dijkstra(cost, 0, 4, cost.length);
        List<Integer> temp = myDijkstra.getShortesPath();
        for (Integer i : temp) {
            actual += i;
        }
        assertEquals(expected, actual);
    }

    @Test
    void dijkstraTest2() {
        setUp1();
        String expected = "103";
        String actual = "";
        myDijkstra.dijkstra(cost, 1, 3, cost.length);
        List<Integer> temp = myDijkstra.getShortesPath();
        for (Integer i : temp) {
            actual += i;
        }
        assertEquals(expected, actual);
    }

    @Test
    void dijkstraTest3() {
        setUp1();
        String expected = "42301";
        String actual = "";
        myDijkstra.dijkstra(cost, 4, 1, cost.length);
        List<Integer> temp = myDijkstra.getShortesPath();
        for (Integer i: temp)
        {
            actual += i;
        }
        assertEquals(expected, actual);
    }


}

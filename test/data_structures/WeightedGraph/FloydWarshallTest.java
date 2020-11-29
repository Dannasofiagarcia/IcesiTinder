package data_structures.WeightedGraph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class FloydWarshallTest {

private static FloydWarshall myFlow = new FloydWarshall();

	private int[][] cost;
	private List<Integer> list;
	
	public void setUp1() {
		
		cost = new int[][] { 
			        { 0, 2, 0, 6, 0 }, 
			        { 2, 0, 3, 8, 5 }, 
			        { 0, 3, 0, 0, 7 }, 
			        { 6, 8, 0, 0, 9 }, 
			        { 0, 5, 7, 9, 0 }, 
			    }; 
	}
	
	public void setUp2() {
		cost = new int[][] { 
			{0,	3,	20,	3, 0},
		    {3,	0,	0,	0,	0},
		    {20, 0,	0,	2,	3},
		    {3,	0,	2,	0,	0},
		    {0, 0,	3,	0,	0},
	    };
	}
	
	@Test
	void floydWarshallTest1() {
		setUp2();
		list = new ArrayList<>();
		int[] aux = new int[] {6, 3, 5, 3, 8, 3, 6, 8, 6, 11, 5, 8, 4, 2, 3, 3, 6, 2, 4, 5, 8, 11, 3, 5, 6};
		for (int i = 0; i < aux.length; i++) {
			list.add(aux[i]);
		}
		assertEquals(list.toString(), myFlow.floydWarshall(cost).toString());
	}
	
	@Test
	void floydWarshallTest2() {
		setUp1();
		list = new ArrayList<>();
		int[] aux = new int[] {4, 2, 5, 6, 7, 2, 4, 3, 8, 5, 5, 3, 6, 11, 7, 6, 8, 11, 12, 9, 7, 5, 7, 9, 10};
		for (int i = 0; i < aux.length; i++) {
			list.add(aux[i]);
		}
		assertEquals(list.toString(), myFlow.floydWarshall(cost).toString());
	}

}

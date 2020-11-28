package data_structures.WeightedGraph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KruskalTest {

	private static Kruskal myKruskal = new Kruskal();
	
	int[][] cost;
	
	
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
			{0,	3,	20,	3,0},
		    {3,	0,	0,	0,	0},
		    {20,	0,	0,	2,	3},
		    {3,	0,	2,	0,	0},
		    {0	,0,	3,	0,	0},
	    };
	    
	    
	}
	
	@Test
	void kruskalMSTTest1() {
		setUp1();
		assertEquals(16, myKruskal.kruskalMST(cost));
	}
	
	@Test
	void kruskalMSTTest2() {
		setUp2();
		assertEquals(11, myKruskal.kruskalMST(cost));
	}

}

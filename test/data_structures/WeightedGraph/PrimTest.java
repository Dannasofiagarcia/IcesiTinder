import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data_structures.WeightedGraph.Prim;

class PrimTest {


	private static Prim myPrim = new Prim();
	
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
			{0,	3,	20,	3, 0},
		    {3,	0,	0,	0,	0},
		    {20, 0,	0,	2,	3},
		    {3,	0,	2,	0,	0},
		    {0, 0,	3,	0,	0},
	    };
	    
	    
	}
	
	@Test
	void kruskalMSTTest1() {
		setUp1();
		myPrim.primMST(cost)
		assertEquals(16, myPrim.mincost );
	}
	
	@Test
	void kruskalMSTTest2() {
		setUp2();
		myPrim.primMST(cost)
		assertEquals(11, myPrim.mincost);
	}
}

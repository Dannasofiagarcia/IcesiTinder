package data_structures.WeightedGraph;

public class Kruskal {

	static int V = 5;
	static int[] parent = new int[V];
	static int INF = Integer.MAX_VALUE;
	
	static int find(int i) {
		while (parent[i] != i)
			i = parent[i];
		return i;
	}

	static void union1(int i, int j) {
		int a = find(i);
		int b = find(j);
		parent[a] = b;
	}

	public static int kruskalMST(int adjMatrix[][]) {
		int[][] cost = new int[adjMatrix.length][adjMatrix.length];

		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost.length; j++) {
				if (adjMatrix[i][j] == 0) {
					cost[i][j] = INF;
				} else {
					cost[i][j] = adjMatrix[i][j];
				}
			}
		}

		int mincost = 0; 
		for (int i = 0; i < V; i++)
			parent[i] = i;
		int edge_count = 0;
		while (edge_count < V - 1) {
			int min = INF, a = -1, b = -1;
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					if (find(i) != find(j) && cost[i][j] < min) {
						min = cost[i][j];
						a = i;
						b = j;
					}
				}
			}

			union1(a, b);
//			System.out.printf("Edge %d:(%d, %d) cost:%d \n", edge_count++, a, b, min);
			edge_count++;
			mincost += min;
		}
		
		return mincost;
//		System.out.printf("\n Minimum cost= %d \n", mincost);

	}
}

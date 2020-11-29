package data_structures.WeightedGraph;

import java.util.ArrayList;
import java.util.List;

public class FloydWarshall {

	final static int INF = 99999;
	private int V;
	
	public List<Integer> floydWarshall(int matrix[][]) {
		int[][] graph = new int[matrix.length][matrix.length];
		V = graph.length;
		int dist[][] = new int[V][V];
		int i, j, k;
		for (int k2 = 0; k2 < graph.length; k2++) {
			for (int l = 0; l < graph.length; l++) {
				if (matrix[k2][l] == 0) {
					graph[k2][l] = INF;
				} else {
					graph[k2][l] = matrix[k2][l]; 
				}
			}
		}
		
		for (i = 0; i < V; i++)
			for (j = 0; j < V; j++)
				dist[i][j] = graph[i][j];
		
		for (k = 0; k < V; k++) {
			for (i = 0; i < V; i++) {
				for (j = 0; j < V; j++) {
					if (dist[i][k] + dist[k][j] < dist[i][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
		return printSolution(dist);
	}

	public List<Integer> printSolution(int dist[][]) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < V; ++i) {
			for (int j = 0; j < V; ++j) {
				if (dist[i][j] == INF)
					list.add(INF);
				else
					list.add(dist[i][j]);
			}
		}
		return list;
	}

}

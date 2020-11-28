package ui;

import java.util.List;

import data_structures.WeightedGraph.Graph;
import data_structures.WeightedGraph.Kruskal;
import data_structures.WeightedGraph.Node;
import data_structures.WeightedGraph.Prim;
import model.User;

public class Main {

    public static void main(String[] args) {

        Graph<User> miGrafo = new Graph<User>();

        User a = new User("andrea", "sabsajhds");
        User b = new User("danna", "asdas");
        User c = new User("escobar", "122s");
        User d = new User("cordoba", "c3s");
        User e = new User("reyes", "12ss");

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
        
        int[][] matriz = miGrafo.adjMatrix;
        
        for (int x=0; x < matriz.length; x++) {
        	  System.out.print("|");
        	  for (int y=0; y < matriz[x].length; y++) {
        	    System.out.print (matriz[x][y]);
        	    if (y!=matriz[x].length-1) System.out.print("\t");
        	  }
        	  System.out.println("|");
        	}
        
        Kruskal myKruskal = new Kruskal();
      
        System.out.println(myKruskal.kruskalMST(miGrafo.adjMatrix));

        Prim p = new Prim();
        p.primMST(miGrafo.adjMatrix);
        System.out.println(p.mincost);
        for (Node<User> nodo: miGrafo.dijkstraForAdjaMatrix(a.hashCode(), e.hashCode())
             ) {
            System.out.println(nodo.getValue().userName);
        }
    }

}
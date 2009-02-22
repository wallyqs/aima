import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.TreeSet;
import java.util.Comparator;
import javax.swing.JFrame;
import javax.swing.JPanel;

// genera una gráfica del problema TSP aleatoriamente donde las edge tienen peso
public class kruskal{


    public static void main(String[] args){

	TreeSet<Edge> edges = new TreeSet<Edge>();

	// esto es el grafo de adyacencia
	int superconnected[][] =  {{0, 0, 326, 235, 0  },
				   {0, 0, 242, 261, 96 },
				   {326, 242,   0, 133, 244},
				   {235, 261, 133, 0, 0  },
				   {0, 96, 244, 0, 0  }};
	imprimirContenidoConnected(superconnected);

	// ESTO ES EL ARBOL CON LAS EDGES, CREO. tal vez es sólo una estructura con las edges
	edges.add(new Edge(0,3,255));
	edges.add(new Edge(0,2,326));
	edges.add(new Edge(1,4,96));
	edges.add(new Edge(1,3,261));
	edges.add(new Edge(1,2,242));
	edges.add(new Edge(2,4,244));
	edges.add(new Edge(2,3,133));
	
	for (int element : edges.peso)
	    System.out.printf(element + " ");
    }

    public static void imprimirContenidoConnected( int superconnected[][]){
	for (int i = 0; i < superconnected.length; i++) {
	    for (int j = 0; j < superconnected[i].length; j++) {
		System.out.printf(superconnected[i][j] + " , ");
	    }
	    System.out.printf("\n");
	}
    }


    // subclases-----------------------------------------------
    // crear una clase Edge que tenga propiedades
    public static class Edge{
	
	public int peso;
	public int vertexA;
	public int vertexB;
	
	public Edge(int weight, int vertA, int vertB){
	    this.peso = weight;
	    this.vertA = vertexA;
	    this.vertB = vertexB;
	}

	public int comparar(Edge edge){
	    return (this.peso < edge.peso) ? -1 : 1;
	}
    } // end Edge

    // para comparar con el TreeSet
    // public class elComparador implements Comparator<int>{
    // 	public int compare(int a, int b){
    // 	    int aStr, bStr;
    // 	    aStr = a;
    // 	    bStr = b;
    // 	    return bStr.compareTo(aStr);
    // 	}
    // } // end elComparador

}     // end kruskal

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.TreeSet;
import java.util.Comparator;
import javax.swing.JFrame;
import javax.swing.JPanel;

// genera una gráfica del problema TSP aleatoriamente donde las edge tienen peso
public class kruskal{

    public Node nodos[];
    public static void main(String[] args){

	// esto es el grafo de adyacencia
	int superconnected[][] =  {{0, 0, 326, 235, 0  },
				   {0, 0, 242, 261, 96 },
				   {326, 242,   0, 133, 244},
				   {235, 261, 133, 0, 0  },
				   {0, 96, 244, 0, 0  }};
	imprimirContenidoConnected(superconnected);
	
	nodos = new Node[7];
	nodos[0] = new Node(0,3,255);
	nodos[1] = new Node(0,2,326);
	nodos[2] = new Node(1,4,96);
	nodos[3] = new Node(1,3,261);
	nodos[4] = new Node(1,2,242);
	nodos[5] = new Node(2,4,244);
	nodos[6] = new Node(2,3,133);
	
	// for (int element : nodos.peso)
	//     System.out.printf(element + " ");
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
    // crear una clase Node que tenga propiedades
    public class Node{
	
	public int vecinos[];

	// como saber cuales son sus vecinos??
	// buscar dentro del arreglo uno por uno, por ejemplo
	// superconnected[yo][j]

	public Node(int yo){

	    // primero sacar el numeroVecinos
	    this.vecinos = new int[numeroVecinos];
	}

	public int comparar(Node edge){
	    return (this.peso < edge.peso) ? -1 : 1;
	}
    } // end Node

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

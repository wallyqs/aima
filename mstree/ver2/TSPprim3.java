import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.JFrame;

public class TSPprim3 extends JFrame{

    // método para resolver el MST hard
    // método para resolver el MST automático
    public Punto puntillos[];
    public int superconnected[][];
    public final int numeroDeNodos = 5; // 5
    public final int cuantasVeces = 14; // conecta los nodos 14
    public final int radioCirculo = 5;


    // implementar el algoritmo del MST usando los datos de PUNTO


    // --------------------------THE PAINT!!! ------------------------
    public void paint(Graphics g){
	// Generador de Problemas.

	puntillos = new Punto[numeroDeNodos];
	superconnected = new int[numeroDeNodos][numeroDeNodos];

	int colorless = 0;
	for (int i = 0; i < puntillos.length; i++) {
	    puntillos[i] = new Punto();

	    if (colorless == 0) {	  // quitar
		g.setColor(Color.yellow); // quitar
	    } else if (colorless >= 4 ){  // quitar
		g.setColor(Color.pink);	  // quitar
	    }				  // quitar
 	    colorless++;		  // quitar

	    puntillos[i].IpaintMyself(g);
	    
	    g.setColor(Color.black);	  // quitar
	}
	conectarNodosAleatoriamente(puntillos, g);
	// conectarConEnLineaRectaPuntos(puntillos, g);
	imprimirContenidoConnected( // superconnected
				   );
	imprimirVecinosDeCadaNodo(puntillos);


    } // fin de paint principal
    // --------------------------PAINT!!!THE ------------------------


    // -------------------------CONECTES-----------------------------
    public void imprimirVecinosDeCadaNodo(Punto puntillos[]){
	// de los puntillos
	for (int i = 0; i < puntillos.length; i++) {
	    System.out.printf("Soy el Node %d \n", i);
	    puntillos[i].imprimirMisVecinos();
	}
    }

    public void conectarNodosEstructura(// int superconnected[][],
					int de, int a, int peso){
	// PESAR EDGE
	superconnected[de][a] = 1 * peso;
	superconnected[a][de] = 1 * peso;
    }

    public void imprimirContenidoConnected(// int superconnected[][]
					   ){
	for (int i = 0; i < superconnected.length; i++) {
	    for (int j = 0; j < superconnected[i].length; j++) {
		System.out.printf(superconnected[i][j] + " , ");
	    }
	    System.out.printf("\n");
	}
    }

    // pesar la arista/edge
    public int pesarEdge(int x1, int y1, int x2, int y2){
	int pesoEnlace;
	pesoEnlace = (int)Math.sqrt(Math.pow ((x2 - x1) , 2) + 
				    Math.pow ((y2 - y1) , 2));
	return pesoEnlace;
    }

    public void conectarNodosAleatoriamente(Punto puntillos[],Graphics g){
	// int cuantasVeces = 8;
	int dame = dameAleatorioConRango(puntillos);
	int dame2 = dameAleatorioConRango(puntillos);
	int weight;
	for (int i = 0; i < cuantasVeces; i++) {
	    g.drawLine(puntillos[dame].coorx + radioCirculo, 
		       puntillos[dame].coory + radioCirculo,
		       puntillos[dame2].coorx + radioCirculo, //
		       puntillos[dame2].coory + radioCirculo
		       );
	    // pesar la arista/edge
	    weight = pesarEdge(puntillos[dame].coorx, 
			       puntillos[dame].coory,
			       puntillos[dame2].coorx,
			       puntillos[dame2].coory
			       );
	    conectarNodosEstructura(dame, dame2, weight);

	    // PASARLO COMO UNA STRING Y DESPUÉS HACER EL CAST
	    // agregandole los vecinos
	    // si el peso entre los nodos es mayor a 0, es un vecino
	    // siempre va a ser...
	    // algunos salen repetidos
	    // CADA VECINO TIENE UN PESO

	    // SI EL PESO ES 0 NI SIQUIERA LO DEBERIA DE ESTAR PONIENDO
	    if (weight != 0 ) {
		if (puntillos[dame].vecinos.contains(dame2 + "")) {
		    System.out.printf("item repetido\n");
		    // puntillos[dame].vecinos.remove(dame2 + "");
		    // puntillos[dame].vecinos.add(dame2 + "");
		}else {
		    puntillos[dame].vecinos.add(dame2 + "");
		    puntillos[dame].ponerPesoEnlaces(weight);
		}

		if (puntillos[dame2].vecinos.contains(dame + "")) {
		    System.out.printf("item repetido\n");
		    // puntillos[dame2].vecinos.remove(dame + "");
		    // puntillos[dame2].vecinos.add(dame + "");
		}else {
		    puntillos[dame2].vecinos.add(dame + "");
		    puntillos[dame2].ponerPesoEnlaces(weight);
		}
		// POR EL MOMENTO ESTO NO
	    
		// tambien debe de quitarse a si mismo como vecino
		// if (puntillos[dame].vecinos.contains(dame + "")) {
		//     // System.out.printf("item repetido\n");
		//     puntillos[dame].vecinos.remove(dame + "");
		//     puntillos[dame].pesoEnlaces.remove(dame + "");
		// }
	    
		// if (puntillos[dame2].vecinos.contains(dame2 + "")) {
		//     // System.out.printf("item repetido\n");
		//     puntillos[dame2].vecinos.remove(dame2 + "");
		//     puntillos[dame2].pesoEnlaces.remove(dame2 + "");
		// }
	    }

	    // PESAR EDGE--le paso puntillos para lo del size adentro
	    // RENEW DE LOS VALORES
	    dame = dameAleatorioConRango(puntillos);
	    dame2 = dameAleatorioConRango(puntillos);
	}
    }

    // FALLBACK por si no se conectó algún nodo la primera vez
    public void conectarConEnLineaRectaPuntos(Punto puntillos[],Graphics g){
	
	int weight;
	for (int i = 0; i < puntillos.length -1; i++) {
	    g.drawLine(puntillos[i].coorx + radioCirculo, //
		       puntillos[i].coory + radioCirculo,
		       puntillos[i+1].coorx + radioCirculo, //
		       puntillos[i+1].coory + radioCirculo
		       );
	    weight = pesarEdge(puntillos[i].coorx, 
			       puntillos[i].coory,
			       puntillos[i+1].coorx,
			       puntillos[i+1].coory
			       );

	    conectarNodosEstructura(i, i+1, weight);
	}
    }
    // -------------------------CNOCEETS-----------------------------



    // --------------------- SUBCLASES ---------------------------------

    // clase Punto para crear arreglos de ellos
    public class Punto{
	public int coorx;
	public int coory;
	
	// sacar el numero de vecinos es una F*CKING STRING
	// los vecinos y sus pesos están mapeados
	public LinkedList<String> vecinos;
	public LinkedList<String> pesoEnlaces;

	public Punto(){
	    this.coorx = dameNAleatorio();
	    this.coory = dameNAleatorio();
	    vecinos = new LinkedList<String>();
	    pesoEnlaces = new LinkedList<String>();
	}
	public void IpaintMyself(Graphics g){
	    int anchoDelCirculo = 10;
	    g.fillOval(coorx,coory,anchoDelCirculo,anchoDelCirculo);
	}
	public void imprimirMisVecinos(){
	    System.out.printf("Numero vecinos = %s \n", vecinos.size());
	    System.out.printf("Numero enlaces = %s \n", pesoEnlaces.size());
	    for (int i = 0; i < vecinos.size(); i++) {
		System.out.printf("Mi vecini #%d es %s y weight = %s \n",i, vecinos.get(i), pesoEnlaces.get(i));
	    }
	    int menorDeTodos = obtenerMenor();
	}
	// deben de estar 1 por uno
	public void ponerPesoEnlaces(int weight){
	    this.pesoEnlaces.add(weight + "");
	}

	public int obtenerMenor(){
	    int menor = Integer.parseInt(this.pesoEnlaces.get(0));;
	    int next = 0;
	    int posMenor = 0;

	    for (int i = 0; i < pesoEnlaces.size() - 1; i++) {
		next = Integer.parseInt(this.pesoEnlaces.get(i+1));
		if (menor > next) {
		    menor = next;
		    posMenor = i + 1;
		}
	    }
	    System.out.printf("El menor esta en la pos %d y es %d \n", posMenor, menor);
	    return posMenor;
	}

    }
    // --------------------SBULCSASE----------------------------------



    // ------------------------GENERADORES ALEATORIOS -----------------------
    // ------------------------GENERADORES ALEATORIOS -----------------------
    // numero aleatorio con el rango del arreglo PARA CONECTARLOS
    public int dameAleatorioConRango(Punto puntillos[]){
	int rawRandomNumber;
	int min = 0;
	int max = puntillos.length - 1;

	rawRandomNumber = (int) (Math.random() * (max - min + 1) ) + min;
	// System.out.println("Random number : " + rawRandomNumber);
	return rawRandomNumber;
    }

    public int dameNAleatorio0a9(){
	int rawRandomNumber;
	int min = 0;
	int max = 20;
	rawRandomNumber = (int) (Math.random() * (max - min + 1) ) + min;
	return rawRandomNumber;
    }

    // dame número aleatorio no muy grande PARA DIBUJARLOS
    public int dameNAleatorio(){
	int a = 0;
	a = (int)((Math.random() * 400) + 140 + dameNAleatorio0a9());
	System.out.printf(a+"\n");
	return a;
    }

    // método para dibujar las figuras del robot
    public void dibujarFiguras(Figura figurinas[], Graphics g, Escena escenilla){
    	for (int k = 0; k < escenilla.numeroObjetos; k++) {
    	    // hacerlo con un FOR para dibujar todas las lineas MENOS LA ÚLTIMA
    	    for (int i = 0; i < figurinas[k].numCoordenadas - 1; i++) {
    	    	g.drawLine(figurinas[k].coordsX[i], //
    			   figurinas[k].coordsY[i],
    			   figurinas[k].coordsX[i+1], //
    			   figurinas[k].coordsY[i+1]
    			   );
    	    }
    	    // dibujar la última línea
    	    int maxcoors = figurinas[k].numCoordenadas;
    	    g.drawLine(figurinas[k].coordsX[maxcoors - 1], //
    		       figurinas[k].coordsY[maxcoors - 1],
    		       figurinas[k].coordsX[0], //
    		       figurinas[k].coordsY[0]
    		       );
    	}
    }
    // ------------------------ALEATORIOS GENERADORES -----------------------
    // ------------------------ALEATORIOS GENERADORES -----------------------

    // minimo para que salga una ventana
    public TSPprim3(){
	setSize(800,600);
	setVisible(true);
    }
    public static void main(String[] args){
	TSPprim3 GG = new TSPprim3();
	// GG.repaint();
    }
}

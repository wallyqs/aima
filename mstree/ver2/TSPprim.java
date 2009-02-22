import java.awt.Color;
import java.awt.Graphics;
// import java.awt.Graphics2D;
import java.util.LinkedList;
import javax.swing.JFrame;
// import javax.swing.JPanel;

// genera una gráfica del problema TSP aleatoriamente donde las edge tienen peso
public class TSPprim extends JFrame{

    // método para resolver el MST hard
    // método para resolver el MST automático
    public Punto puntillos[];
    public int superconnected[][];
    public final int numeroDeNodos = 5;
    public final int cuantasVeces = 14; // conecta los nodos
    public final int radioCirculo = 5;



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
	int cuantasVeces = 8;
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
	    if (puntillos[dame].vecinos.contains(dame2 + "")) {
		System.out.printf("item repetido");
	    }else {
		puntillos[dame].vecinos.add(dame2 + "");
	    }

	    if (puntillos[dame2].vecinos.add(dame + "")) {
		System.out.printf("item repetido");
	    }else {
		puntillos[dame2].vecinos.add(dame + "");
	    }

	    // PESAR EDGE--le paso puntillos para lo del size adentro
	    dame = dameAleatorioConRango(puntillos);
	    dame2 = dameAleatorioConRango(puntillos);
	}
    }

    // fallback por si no se conectó algún nodo la primera vez
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
	public LinkedList<String> vecinos;

	public Punto(){
	    this.coorx = dameNAleatorio();
	    this.coory = dameNAleatorio();
	    vecinos = new LinkedList<String>();
	}
	public void IpaintMyself(Graphics g){
	    int anchoDelCirculo = 10;
	    g.fillOval(coorx,coory,anchoDelCirculo,anchoDelCirculo);
	}
	public void imprimirMisVecinos(){
	    for (int i = 0; i < vecinos.size(); i++) {
		System.out.printf("Mi vecini #%d es %s\n",i, vecinos.get(i));
	    }
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
	System.out.println("Random number : " + rawRandomNumber);
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
    public TSPprim(){
	setSize(800,600);
	setVisible(true);
    }
    public static void main(String[] args){
	TSPprim GG = new TSPprim();
	// GG.repaint();
    }
}

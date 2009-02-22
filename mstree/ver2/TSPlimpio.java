// quiero dibujar pasarle las coordenadas y que me dibuje las lineas

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

// genera una gráfica del problema TSP aleatoriamente

public class TSPlimpio extends JFrame{

    // FALTA LA ESTRUCTURA DE DATOS!!!
    // método para resolver el MST hard
    // método para resolver el MST automático
    public Punto puntillos[];
    public int superconnected[][];

    public void paint(Graphics g){
	// número de puntos que queremos :: Randomizar
	int numeroDeNodos = 8;
	puntillos = new Punto[numeroDeNodos];
	superconnected = new int[numeroDeNodos][numeroDeNodos];

	// for (int i = 0; i < puntillos.length; i++) {
	//     puntillos[i] = new Punto();
	//     puntillos[i].IpaintMyself(g);
	// }
	// conectarNodosAleatoriamente(puntillos, g);
	// conectarConEnLineaRectaPuntos(puntillos, g);
	
	imprimirContenidoConnected(superconnected);

	
    } // fin de paint principal

    public void connectarNodosEstructura(Punto puntillos){
	
    }

    public void imprimirContenidoConnected(int superconnected[][]){
	for (int i = 0; i < superconnected.length; i++) {
	    for (int j = 0; j < superconnected[i].length; j++) {
		System.out.printf(superconnected[i][j] + " , ");
	    }
	    System.out.printf("\n");
	}
    }


    public void conectarNodosAleatoriamente(Punto puntillos[],Graphics g){
	int radioCirculo = 5;
	int cuantasVeces = 8;
	int dame = dameAleatorioConRango(puntillos);
	int dame2 = dameAleatorioConRango(puntillos);

	for (int i = 0; i < cuantasVeces; i++) {
	    g.drawLine(puntillos[dame].coorx + radioCirculo, 
		       puntillos[dame].coory + radioCirculo,
		       puntillos[dame2].coorx + radioCirculo, //
		       puntillos[dame2].coory + radioCirculo
		       );
	    dame = dameAleatorioConRango(puntillos);
	    dame2 = dameAleatorioConRango(puntillos);
	}
    }

    // fallback por si no se conectó algún nodo la primera vez
    public void conectarConEnLineaRectaPuntos(Punto puntillos[],Graphics g){
	int radioCirculo = 5;
	for (int i = 0; i < puntillos.length - 1; i++) {
	    g.drawLine(puntillos[i].coorx + radioCirculo, //
		       puntillos[i].coory + radioCirculo,
		       puntillos[i+1].coorx + radioCirculo, //
		       puntillos[i+1].coory + radioCirculo
		       );
	}
    }

    // clase Punto para crear arreglos de ellos------------------
    public class Punto{
	public int coorx;
	public int coory;

	public Punto(){
	    this.coorx = dameNAleatorio();
	    this.coory = dameNAleatorio();
	}
	public void IpaintMyself(Graphics g){
	    int anchoDelCirculo = 10;
	    g.fillOval(coorx,coory,anchoDelCirculo,anchoDelCirculo);
	}
    } // end Punto -----------------------------------------------

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

    // minimo para que salga una ventana
    public TSPlimpio(){
	setSize(800,600);
	setVisible(true);
    }
    public static void main(String[] args){
	TSPlimpio GG = new TSPlimpio();
	GG.repaint();
    }
}

// quiero dibujar pasarle las coordenadas y que me dibuje las lineas

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

// genera una gráfica del problema TSP aleatoriamente

public class Grapher3 extends JFrame{

    // tamaño de la ventana
    public Grapher3(){
	setSize(800,600);
	setVisible(true);
	// setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    // método para dibujar un gráfo de TSP -- hard 
    // método para dibujar un gráfo TSP automático

    // FALTA LA ESTRUCTURA DE DATOS!!!

	// método para resolver el MST hard
	// método para resolver el MST automático

    // declarar arreglo de puntos
    public Punto puntillos[];

    public void paint(Graphics g){
	// Escena escenilla = new Escena("text5good.txt");
	// Figura figurinas[] = escenilla.regresarArregloDeObjetos();
	// dibujarFiguras(figurinas, g, escenilla); 

	// número de puntos que queremos :: Randomizar
	int tamanoDelArreglo = 10;
	puntillos = new Punto[tamanoDelArreglo];

	// dame diez numeros aleatorios
	for (int i = 0; i < puntillos.length; i++) {
	    puntillos[i] = new Punto();
	    puntillos[i].IpaintMyself(g);
	    // g.fillOval(dameNAleatorio(),dameNAleatorio(),5,5);
	}
	// conectarAleatoriamenteLineasDeArregloDePuntos(puntillos, g);
	conectarConEnLineaRectaPuntos(puntillos, g);
	conectarParesPuntos(puntillos, g);
	conectarDosAleatoriosConElUltimo(puntillos, g);
	// como conectar los caminos???
    } // fin de paint principal

    public void conectarDosAleatoriosConElUltimo(Punto puntillos[],Graphics g){
	int radioCirculo = 5;
	int dame = dameNAleatorio0a9();
	g.drawLine(puntillos[dame].coorx + radioCirculo, 
		   puntillos[dame].coory + radioCirculo,
		   puntillos[puntillos.length - 1].coorx + radioCirculo, //
		   puntillos[puntillos.length - 1].coory + radioCirculo
		   );
	dame = dameNAleatorio0a9();
	g.drawLine(puntillos[dame].coorx + radioCirculo, 
		   puntillos[dame].coory + radioCirculo,
		   puntillos[puntillos.length - 1].coorx + radioCirculo, //
		   puntillos[puntillos.length - 1].coory + radioCirculo
		   );
    }

    // public void conectarAleatoriamenteLineasDeArregloDePuntos(Punto puntillos[],Graphics g){	int radioCirculo = 5; }

    public void conectarParesPuntos(Punto puntillos[],Graphics g){
	int radioCirculo = 5;
	for (int i = 0; i < puntillos.length - 2; i++) {
	    g.drawLine(puntillos[i].coorx + radioCirculo, //
		       puntillos[i].coory + radioCirculo,
		       puntillos[i+2].coorx + radioCirculo, //
		       puntillos[i+2].coory + radioCirculo
		       );
	    i++;		// incrementar dos veces
	}
    }

    // hacer una linea recta con los puntos en el orden que salieron
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


    // dame número aleatorio no muy grande
    // 300 que tan dispersos
    public int dameNAleatorio(){
	int a = 0;
	a = (int)((Math.random() * 400) + 140 + dameNAleatorio0a9());
	System.out.printf(a+"\n");
	return a;
    }
    // dameNodoAleatorioDelArreglo
    public int dameNAleatorio0a9(){
	int a = 0;
	a = (int)((Math.random() * 10));
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

    // minimo para que salga una ventana---esto es invisible
    public static void main(String[] args){
	Grapher3 GG = new Grapher3();
	GG.repaint();
    }
}

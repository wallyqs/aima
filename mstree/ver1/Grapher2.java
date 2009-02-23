// quiero dibujar pasarle las coordenadas y que me dibuje las lineas

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Grapher2 extends JPanel{


    public void paint(Graphics g){

	Escena m = new Escena("text5good.txt");
	Figura tm[] = m.regresarArregloDeObjetos();
	int k = 0;
	System.out.printf(m.numeroObjetos + "");
	System.out.printf("ijijijijijijij");
	// for que se ejecuta por cada uno de los objetos
	// for (int k = 0; k < m.numeroObjetos; k++) {
	    // hacerlo con un FOR para dibujar todas las lineas MENOS LA ÚLTIMA
	    for (int i = 0; i < tm[k].numCoordenadas - 1; i++) {
	    	g.drawLine(tm[k].coordsX[i], //
			   tem[k].coordsY[i],
			   tem[k].coordsX[i+1], //
			   tem[k].coordsY[i+1]
			   );
	    }
	    // dibujar la última línea
	    int maxcoors = teAmo[k].numCoordenadas;
	    g.drawLine(tm[k].coordsX[maxcoors - 1], //
		       tm[k].coordsY[maxcoors - 1],
		       tm[k].coordsX[0], //
		       tm[k].coordsY[0]
		       );
	// }











    } // fin de paint


















    // minimo para que salga una ventana---esto es invisible
    public static void main(String[] args){
	JFrame frame = new JFrame("Demo");
	frame.getContentPane().add(new Grapher());
	frame.setSize(500,500);
	frame.setVisible(true);
    }
}

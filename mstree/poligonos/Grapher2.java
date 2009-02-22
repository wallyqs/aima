// quiero dibujar pasarle las coordenadas y que me dibuje las lineas

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Grapher2 extends JPanel{
    public void paint(Graphics g){
	Escena scene = new Escena("text7");
	Figura go[] = scene.regresarArregloDeObjetos();
	// int k = 0;
	System.out.printf(scene.numeroObjetos + "");
	System.out.printf("ijijijijijijij");
	// for que se ejecuta por cada uno de los objetos
	// for (int k = 0; k < scene.numeroObjetos; k++) {
	    // hacerlo con un FOR para dibujar todas las lineas MENOS LA ÚLTIMA
	// diujar cada una de las figuras
	for (int k = 0; k < scene.numeroObjetos; k++) {
	    for (int i = 0; i < go[k].numCoordenadas - 1; i++) {
	    	g.drawLine(go[k].coordsX[i], //
			   go[k].coordsY[i],
			   go[k].coordsX[i+1], //
			   go[k].coordsY[i+1]
			   );
	    }
	    // dibujar la última línea
	    int maxcoors = go[k].numCoordenadas;
	    g.drawLine(go[k].coordsX[maxcoors - 1], //
		       go[k].coordsY[maxcoors - 1],
		       go[k].coordsX[0], //
		       go[k].coordsY[0]
		       );
	}

	// }
    } // fin de paint

    // minimo para que salga una ventana---esto es invisible
    public static void main(String[] args){
	JFrame frame = new JFrame("Demo");
	frame.getContentPane().add(new Grapher2());
	frame.setSize(500,500);
	frame.setVisible(true);
    }
}

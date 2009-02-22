// quiero dibujar pasarle las coordenadas y que me dibuje las lineas

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Grapher extends JPanel{


    public void paint(Graphics g){

	Escena mechita = new Escena("text5.txt");
	Figura teAmo[] = mechita.regresarArregloDeObjetos();

	// hacerlo con un FOR para dibujar todas las lineas MENOS LA ÚLTIMA
	for (int i = 0; i < teAmo[0].numCoordenadas - 1; i++) {
	    	g.drawLine(teAmo[0].coordsX[i], // から
			   teAmo[0].coordsY[i],
			   teAmo[0].coordsX[i+1], // まで
			   teAmo[0].coordsY[i+1]
			   );
	}
	// dibujar la última línea
	int maxcoors = teAmo[0].numCoordenadas;
	g.drawLine(teAmo[0].coordsX[maxcoors - 1], // から
		   teAmo[0].coordsY[maxcoors - 1],
		   teAmo[0].coordsX[0], // まで
		   teAmo[0].coordsY[0]
		   );


















    }


















    // minimo para que salga una ventana---esto es invisible
    public static void main(String[] args){
	JFrame frame = new JFrame("Demo");
	frame.getContentPane().add(new Grapher());
	frame.setSize(400,400);
	frame.setVisible(true);
    }
}

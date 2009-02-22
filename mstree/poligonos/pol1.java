// quiero dibujar pasarle las coordenadas y que me dibuje las lineas

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class pol1 extends JPanel{

    public Poligono poligonos[];

    // como le hago para crear la estructura de los nodos???
    // primer subclase------------------------------------------------------
   public class Poligono{
       public LinkedList<String> edges;
       
       public Poligono(){
	   edges = new LinkedList<String>;

	   
       }
   }
    // primer subclase------------------------------------------------------

    public void imprimirContenidoLista(LinkedList<String> content){
	for (int i = 0; i < content.size(); i++) {
	    System.out.printf(content.get(i) + " , ");
	}
	System.out.printf("\n");
    }

    public void paint(Graphics g){
	Escena scene = new Escena("text7");
	Figura go[] = scene.regresarArregloDeObjetos();
	System.out.printf(scene.numeroObjetos + "");
	System.out.printf("ijijijijijijij");
	for (int k = 0; k < scene.numeroObjetos; k++) {
	    for (int i = 0; i < go[k].numCoordenadas - 1; i++) {
	    	g.drawLine(go[k].coordsX[i],
			   go[k].coordsY[i],
			   go[k].coordsX[i+1],
			   go[k].coordsY[i+1]
			   );
	    }
	    
	    // cada que se cree una linea, voy a crear un objeto y sus aristas
	    


	    int maxcoors = go[k].numCoordenadas;
	    g.drawLine(go[k].coordsX[maxcoors - 1], //
		       go[k].coordsY[maxcoors - 1],
		       go[k].coordsX[0], //
		       go[k].coordsY[0]
		       );
	}

    } // fin de paint

    public static void main(String[] args){
	JFrame frame = new JFrame("Demo");
	frame.getContentPane().add(new pol1());
	frame.setSize(500,500);
	frame.setVisible(true);
    }
}

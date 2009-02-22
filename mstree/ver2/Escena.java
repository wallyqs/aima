import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

// Clase Escena el cual contiene un método con el cual lee el archivo que se le pasa en el 
// constructor y regresa un arreglo de Figuras. 
// USAGE:
// Escena holaaaa = new Escena("text2.txt");
// Figura conjunto[] = holaaaa.regresarArregloDeObjetos();
// 
// ORDEN EN QUE DEBE DE IR EL ARCHIVO:
// (numero de figuras)
// (numero de coordenadas de figura1)
// (coordenada en X1 de figura1)
// (coordenada en Y1 de figura1)
// (coordenada en X2 de figura1)
// (coordenada en Y2 de figura1)
// (...++coordenadas)
// (numero de coordenadas de figura2)
// (coordenada en X1 de figura2)
// (coordenada en Y1 de figura2)
// (coordenada en X2 de figura2)
// (coordenada en Y2 de figura2)
// (...++coordenadas)


// Separarlo ahora para que regrese el array object
public class Escena{

    private Scanner input;
    public int numeroObjetos;	// para inicializar arreglo de Figura
    private String path;
    public Figura arrayObjetos[];
    
    public Escena(String camino){ this.path = camino; };

    public Figura[] regresarArregloDeObjetos(){
	try {input = new Scanner(new File (path));}
	catch (FileNotFoundException e) {System.err.println("No se encuentra el archivo"); System.exit(1);}

	// numero de objetos y crear el arreglo de objetos
	numeroObjetos = Integer.parseInt(input.nextLine().trim());
	System.out.printf("Numero de objetos: %d \n", numeroObjetos);
	arrayObjetos = new Figura[numeroObjetos]; 
	
	// ciclo que se repite por cada objeto que hay. 
	for (int k = 0; k < numeroObjetos; k++) {

	    // inicializar el objeto
	    arrayObjetos[k] = new Figura();
	    arrayObjetos[k].setNCoordenadas(Integer.parseInt(input.nextLine().trim()));

	    // inicializar los arreglos de coordenadas en X y Y con su numCoordenadas privado
	    arrayObjetos[k].inicializarCoordsArray();

	    // FOR interno para definir las coordenadas
	    for (int i = 0; i < arrayObjetos[k].numCoordenadas; i++) {
		arrayObjetos[k].coordsX[i] = Integer.parseInt(input.nextLine().trim());
		arrayObjetos[k].coordsY[i] = Integer.parseInt(input.nextLine().trim());
	    }
	    // DEBUG--> display de la informacion de c/u objetos
	    arrayObjetos[k].myInfo(); 
	}
	return arrayObjetos;
    }
    // // EJEMPLO:
    // public static void main(String args[]){
    // 	Escena holaaaa = new Escena("text.txt");
    // 	Figura conjunto[] = holaaaa.regresarArregloDeObjetos(); // No funciona!!!
    // } 
} // Escena end
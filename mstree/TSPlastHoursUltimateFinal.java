import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.JFrame;

// falta que te diga el peso del arbol
// e implementar el algoritmo para que viaje el agente
// empezando por root y yendose siempre por el mas barato

public class TSPlastHoursUltimateFinal extends JFrame{

    // el algoritmo del MST usando los datos de PUNTO
    // método para resolver el MST hard
    // método para resolver el MST automático
    public Punto puntillos[];
    public FringeType theFringe; 
    public FringeType theMST; 
    public int superconnected[][];
    public final int numeroDeNodos = 20; // 5
    public final int cuantasVeces = 40; // conecta los nodos 14
    public final int radioCirculo = 5; 

    // true method

    public void finalMSTmethod(){
	System.out.println("This is my last chance... to find the MST tree");
	LinkedList<String> unseen = new LinkedList<String>();
	LinkedList<String> fringe = new LinkedList<String>();
	LinkedList<String> mst = new LinkedList<String>();
	theFringe = new FringeType();
	theMST = new FringeType();
	int minor = 0;
	for (int i = 0; i < puntillos.length; i++) {
	    System.out.printf("Metiendo a UNSEEN al nodo # %d \n", i); 
	    unseen.add(i+"");
	}
	imprimirContenidoLista(unseen);

	// INICIALIZAR EL ALGORITMO------000
	// TO DO: hacer que jale el algoritmo recordando los paths del mst

	// quitar y poner el root del fringe
	fringe.add(unseen.get(0)); 
	unseen.remove(unseen.get(0));
	System.out.printf("Contenido Unseen :"); imprimirContenidoLista(unseen); 
	System.out.printf("Contenido fringe :"); imprimirContenidoLista(fringe);

	// AL MOMENTO DE BUSCAR A LOS VECINOS TENGO QUE INSERTAR SUS PATHS
	for (int i = 0; i < puntillos[Integer.parseInt(fringe.get(0))].vecinos.size(); i++) {
	    fringe.add(puntillos[Integer.parseInt(fringe.get(0))].vecinos.get(i));
	    // AÑADIR LOS COSTOS DE LOS PATHS
	    theFringe.addPath("0", 
			      puntillos[Integer.parseInt(fringe.get(0))].vecinos.get(i), 
			      puntillos[Integer.parseInt(fringe.get(0))].pesoEnlaces.get(i) 
			      );

	    unseen.remove(puntillos[Integer.parseInt(fringe.get(0))].vecinos.get(i));
	}
	// theFringe.showPaths();
	mst.add("0");
	fringe.remove("0");
	minor = theFringe.menorDeLosPaths();

	// HASTA AQUI ESTA BIEN--------------------
	// meter el 0 al mst path menor
	theMST.addPath(theFringe.de.get(minor), 
		       theFringe.para.get(minor), 
		       theFringe.pathcost.get(minor)
		       );
	theFringe.removePath(theFringe.de.get(minor), 
			     theFringe.para.get(minor), 
			     theFringe.pathcost.get(minor)
			     );

	System.out.printf("Contenido Unseen :"); imprimirContenidoLista(unseen); 
	System.out.printf("Contenido fringe :"); imprimirContenidoLista(fringe);
	System.out.printf("Contenido mst :");    imprimirContenidoLista(mst);
	System.out.printf("The Fringe :\n");      theFringe.showPaths();
	System.out.printf("The MST :\n");         theMST.showPaths();
	System.out.printf("okokokokokok\n");
	// NO ES MINOR PORQUE MINOR ES LA POSICION DENTRO DE PATHCOST
	// DEBERIA DE SER EL NODO QUE REPRESENTA A MINOR EN ESA  POSICION
	// ahora meter los vecinos del que tiene el path menor al fringe

	// SE DEBEN DE METER LOS CAMINOS DEL QUE ACABO DE METER AL MST!!!
	for (int i = 0; i < puntillos[Integer.parseInt(theMST.para.get(0))].vecinos.size() ; i++) {
	    fringe.add(puntillos[Integer.parseInt(theMST.para.get(0))].vecinos.get(i));
	    // solo debe de poner el path si es que el PARA no existe
	    if (mst.contains(puntillos[Integer.parseInt(theMST.para.get(0))].vecinos.get(i))) { } else {
		    theFringe.addPath(theMST.para.get(0), 
			      // ESTA MAL ESTA INSERCION!!!!!!?!?!??!!??!?!?!?!?!?!?!?!??!?!?!?!?!?!?!?
			      // puntillos[Integer.parseInt(theMST.para.get(0))].vecinos.get(i), 
			      puntillos[Integer.parseInt(theMST.para.get(0))].vecinos.get(i), 
			      puntillos[Integer.parseInt(theMST.para.get(0))].pesoEnlaces.get(i) 
	    		      );
	    // fringe.remove(puntillos[Integer.parseInt(theMST.para.get(minor+"")].vecinos.get(i));
	    unseen.remove(puntillos[Integer.parseInt(theMST.para.get(0))].vecinos.get(i));
	    quitarRepetidosDelMSTenelFringe(fringe, mst); 
	    quitarRepetidosDelMSTenelFringePATHVERSION(theFringe, theMST); 
	 
	    }
	}
	mst.add(theMST.para.get(0));
	fringe.remove(theMST.para.get(0));

	// mst.add(puntillos[Integer.parseInt(mst.get(0))].obtenerMenor() + "");
	// fringe.remove(puntillos[Integer.parseInt(mst.get(0))].obtenerMenor() + "");

	System.out.printf("Contenido Unseen :"); imprimirContenidoLista(unseen); 
	System.out.printf("Contenido fringe :"); imprimirContenidoLista(fringe);
	System.out.printf("Contenido mst :");    imprimirContenidoLista(mst);
	System.out.printf("The Fringe: \n");      theFringe.showPaths();
	System.out.printf("The MST: \n");         theMST.showPaths();

	// ITERACIÓN 2222222222222222222222222222222222222222222222222222
	// Contenido Unseen :
	// Contenido fringe :3 , 4 , 3 , 1 , 
	// Contenido mst :0 , 2 , 
	// The Fringe: 
	// 0 Path de 0 a 3 con costo 164 
	// 1 Path de 2 a 4 con costo 306 
	// 2 Path de 2 a 3 con costo 134 
	// 3 Path de 2 a 1 con costo 93 
	// The MST: 
	// 0 Path de 0 a 2 con costo 72 

	// DEBE DE AGREGAR EL PATH MENOR DE NUEVO al mst // DEBE DE AGREGAR EL PATH MENOR DE NUEVO22222222222222222222222



	// 222222222 Super procedure 22222222222  Super Nintendo fue la mejor epoca

	// falta agregar los paths al theFringe
	int www = 2;
	while (mst.size() != numeroDeNodos) {
	    minor = theFringe.menorDeLosPaths();
	    mst.add(theFringe.para.get(minor)); // se mueve el indice si lo haces despues ERROR!!!
	    theMST.addPath(theFringe.de.get(minor), 
			   theFringe.para.get(minor), 
			   theFringe.pathcost.get(minor)
			   );
	    fringe.remove(theFringe.para.get(minor)); // debe de estar aca esta cosa
	    theFringe.removePath(theFringe.de.get(minor), 
				 theFringe.para.get(minor), 
				 theFringe.pathcost.get(minor)
				 );
	    // agregar los paths a los vecinos del nodo mas nuevo del mst
	    for (int i = 0; i < puntillos[Integer.parseInt(theMST.para.getLast())].vecinos.size() ; i++) {
		fringe.add(puntillos[Integer.parseInt(theMST.para.getLast())].vecinos.get(i));
		// solo debe de poner el path si es que el PARA no existe
		if (mst.contains(puntillos[Integer.parseInt(theMST.para.getLast())].vecinos.get(i))) { } else {
		    theFringe.addPath(theMST.para.getLast(), 
				      // ESTA MAL ESTA INSERCION!!!!!!?!?!??!!??!?!?!?!?!?!?!?!??!?!?!?!?!?!?!?
				      // puntillos[Integer.parseInt(theMST.para.getLast())].vecinos.get(i), 
				      puntillos[Integer.parseInt(theMST.para.getLast())].vecinos.get(i), 
				      puntillos[Integer.parseInt(theMST.para.getLast())].pesoEnlaces.get(i) 
				      );
		    // fringe.remove(puntillos[Integer.parseInt(theMST.para.getLast(minor+"")].vecinos.get(i));
		    unseen.remove(puntillos[Integer.parseInt(theMST.para.getLast())].vecinos.get(i));
		    quitarRepetidosDelMSTenelFringe(fringe, mst); 
		    quitarRepetidosDelMSTenelFringePATHVERSION(theFringe, theMST); 
		    
		}
	    }
	    // mst.add(theMST.para.getLast());

	    // lalalalalalalalalalalalalalalal
	    quitarRepetidosDelMSTenelFringe(fringe, mst); 
	    quitarRepetidosDelMSTenelFringePATHVERSION(theFringe, theMST);
	    quitarDestinosQueYaEstanEnMSTdeltheFringe(theFringe, mst);
	    System.out.printf("%d %d %d %d %d %d %d\n",  www,www,www,www,www,www,www);
	    System.out.printf("Contenido Unseen :"); imprimirContenidoLista(unseen); 
	    System.out.printf("Contenido fringe :"); imprimirContenidoLista(fringe);
	    System.out.printf("Contenido mst :");    imprimirContenidoLista(mst);
	    System.out.printf("The Fringe :\n");      theFringe.showPaths();
	    System.out.printf("The MST :\n");         theMST.showPaths();
	    www++;
	}
    }

    // method true 




    // NO HACER LE MUCHO CASO AL METODO DE ABAJO 
    public void generarMST(Punto puntillos[]){
	LinkedList<String> unseen = new LinkedList<String>();
	LinkedList<String> fringe = new LinkedList<String>();
	LinkedList<String> mst = new LinkedList<String>();
	
	// meter todos el key de cada nodo al unseen
	for (int i = 0; i < puntillos.length; i++) {System.out.printf("Metiendo a UNSEEN al nodo # %d \n", i); unseen.add(i+"");}
	imprimirContenidoLista(unseen);

	// -------------AUTOMATIZAR KORE -----------------------------------
	// TO DO: get(0) quitarlo, todo esto es la primera vez nada más

	// obtener el menor de algun punto
	// puntillos[0].vecinos.get([puntillos[0].obtenerMenor()]+""); 
	// puntillos[0].vecinos.get(puntillos[0].obtenerMenor() +""); 
	// fringe.add(puntillos[Integer.parseInt(fringe.get(0))].vecinos.get(0));

	// INICIO |||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	
	// unseen(0) --> fringe
	// unseen(0) !-- unseen off
	fringe.add(unseen.get(0)); unseen.remove(unseen.get(0));
	System.out.printf("Contenido Unseen :"); imprimirContenidoLista(unseen); System.out.printf("Contenido fringe :"); imprimirContenidoLista(fringe);

	System.out.printf("fringe nodo #%s:\n", "0");

	// puntillos(fringe(0)).imprimirMisVecinos
	puntillos[Integer.parseInt(fringe.get(0))].imprimirMisVecinos();

	// fringe(0).vecinos --> fringe
	// fringe(0).vecinos !-- unseen off
	// (no es posible que hayan repetidos) PRIMERA VEZ
	for (int i = 0; i < puntillos[Integer.parseInt(fringe.get(0))].vecinos.size() ; i++) {
	    fringe.add(puntillos[Integer.parseInt(fringe.get(0))].vecinos.get(i));
	    unseen.remove(puntillos[Integer.parseInt(fringe.get(0))].vecinos.get(i));
	}

	// (esto es de a fuerza meter el fringe(0) y quitarlo del fringe)
	// fringe(0) --> mst
	// fringe(0) !-- fringe off
	mst.add(fringe.get(0)); fringe.remove(fringe.get(0)); 

	// IMPRIMIR TODAS LAS COSAS
	System.out.printf("Contenido Unseen :"); imprimirContenidoLista(unseen); System.out.printf("Contenido fringe :"); imprimirContenidoLista(fringe); System.out.printf("Contenido MST :"); imprimirContenidoLista(mst);

	// tomar el menor de los que estan en el fringe desde 0 --> obtener el menor
	// fringe.menor --> mst   o bien,
	// mst(0).menor --> mst
	// obtener menor deberia de decir la posición en el arreglo de puntillos donde esta el menor
	mst.add(puntillos[Integer.parseInt(mst.get(0))].obtenerMenor() + "");
	fringe.remove(puntillos[Integer.parseInt(mst.get(0))].obtenerMenor() + "");
	System.out.printf("Contenido Unseen :"); imprimirContenidoLista(unseen); System.out.printf("Contenido fringe :"); imprimirContenidoLista(fringe); System.out.printf("Contenido MST :"); imprimirContenidoLista(mst);
	// °°°°°°°°°°°°°°°°°°°°° Primer Ciclo? °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	// mst(1).vecinos --> fringe
	// mst(1).vecinos !-- unseen
	// MAS BIEN SE PONEN LOS VECINOS DEL FRINGE DEL QUE ESTA A PUNTO DE METERSE
	// (es posible que hayan repetidos) ------------------------------------------- SEGUNDA VEZ
	for (int i = 0; i < puntillos[Integer.parseInt(puntillos[Integer.parseInt(mst.get(0))].obtenerMenor() + "")].vecinos.size() ; i++) {
	    fringe.add(puntillos[Integer.parseInt(puntillos[Integer.parseInt(mst.get(0))].obtenerMenor() + "")].vecinos.get(i));
	    unseen.remove(puntillos[Integer.parseInt(puntillos[Integer.parseInt(mst.get(0))].obtenerMenor() + "")].vecinos.get(i));
	}
	quitarRepetidosDelMSTenelFringe(fringe, mst);
	// ya esta sucediendo que hay nodos nuevos vecinos en el fringe que se repiten.
	// lo que se tiene que hacer ahora es comparar cual es el menor de los dos caminos que aparecieron

	System.out.printf("Contenido Unseen :"); imprimirContenidoLista(unseen); System.out.printf("Contenido fringe :"); imprimirContenidoLista(fringe); System.out.printf("Contenido MST :"); imprimirContenidoLista(mst);
	
	// fringe.remove(puntillos[Integer.parseInt(mst.get(0))].obtenerMenor() + "");

	// puntillos[Integer.parseInt(fringe.get(1))].imprimirMisVecinos();

	// -------------------DANGEROUS-----------
	// obtener el menor de el que esta en el fringe	// cuidado con este, segun yo te debe de pasar el valor ejem. "0" en String, porque va a estar cambiando (si me entiendes?)	// esto te dice cual es el menor uno de los puntos, lo necesitamos para quitarlo del fringe y ponerlo en el mst	// Wow, esta super complicada esta linea de código, estará correcta?	// puntillos[Integer.parseInt(mst.get(0))].vecinos.get(puntillos[Integer.parseInt(mst.get(0))].obtenerMenor() +""); 	// debe de haber otra forma de hacer esto empezando por mst.get... creo que no yappari	// CHECAR ESTO: OBTENER EL MENOR CAMINO QUE SE PUEDA TOMAR PARTIENDO DE UNO DE LOS NODOS DEL MST	// CHECAR ESTO: OBTENER EL MENOR CAMINO QUE SE PUEDA TOMAR PARTIENDO DE UNO DE LOS NODOS DEL MST	// CHECAR ESTO: OBTENER EL MENOR CAMINO QUE SE PUEDA TOMAR PARTIENDO DE UNO DE LOS NODOS DEL MST
	// -------------------DANGEROUS-----------

	// la operacion se realiza tantas veces como hay NODOS!!!

	// este ciclo se repite hasta que ya esten todos los nodos dentro
	// es decir mst.size() == numerode nodos que es cuando forma el arbol
	// while (mst.size() != numeroDeNodos) {
	// int k = 0, index por el cual voy 
	    

	// }

	System.out.printf("Aqui deberia de imprimir el MST \n");
	
    }

    public void quitarRepetidosDelMSTenelFringe(LinkedList<String> fringe, LinkedList<String> mst){
	// UNAS 10 VECES PARA QU SE DEPURE BIEN
	for (int j = 0; j < 10; j++) {
	    for (int i = 0; i < mst.size(); i++) {
		if (fringe.contains(mst.get(i))) {
		    fringe.remove(mst.get(i));
		}
	    }
	}

    }

    public void quitarRepetidosDelMSTenelFringePATHVERSION(FringeType fringer, FringeType mstr){
	for (int i = 0; i < mstr.pathcost.size(); i++) {
	    if (fringer.pathcost.contains(mstr.pathcost.get(i))) {
		fringer.de.remove(mstr.de.get(i));
		fringer.para.remove(mstr.para.get(i));
		fringer.pathcost.remove(mstr.pathcost.get(i));
	    }
	}
    }

    public void quitarDestinosQueYaEstanEnMSTdeltheFringe(FringeType fringer, LinkedList<String> mstr){
	for (int i = 0; i < fringer.pathcost.size(); i++) {
	    // if (fringer.pathcost.contains(mstr.pathcost.get(i))) {
	    if (mstr.contains(fringer.para.get(i))) {
		fringer.de.remove(i);
		fringer.para.remove(i);
		fringer.pathcost.remove(i);
	    }
	}
    }

    
    public void imprimirContenidoLista(LinkedList<String> content){
	for (int i = 0; i < content.size(); i++) {
	    System.out.printf(content.get(i) + " , ");
	}
	System.out.printf("\n");
    }

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
	// GENERAR EL MST----- (MÉTODO QUE NO FUNCIONA MUY BIEN)
	// generarMST(puntillos);
	finalMSTmethod();

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
	    // System.out.printf("Numero enlaces = %s \n", pesoEnlaces.size());
	    for (int i = 0; i < vecinos.size(); i++) {
		System.out.printf("Mi vecini #%d es %s y weight = %s \n",i, vecinos.get(i), pesoEnlaces.get(i));
	    }
	    // NO HACE NADA ESTA LINEA
	    // int menorDeTodos = obtenerMenor(); 
	}
	// deben de estar 1 por uno
	public void ponerPesoEnlaces(int weight){
	    this.pesoEnlaces.add(weight + "");
	}

	// regresa la posicion del vecino menor
	// dentro de su lista de vecinos
	// seria puntillos[0].vecinos.get([puntillos[0].obtenerMenor()]+""); 
	// seria puntillos[0].obtenerMenor()); 

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
	    // bug: según yo debe de ser menor y no posMenor

	    // return posMenor;
	    return Integer.parseInt(vecinos.get(posMenor));

	    // ME DEBIA DE REGRESAR LA UBICACIÓN DEL MENOR DENTRO DEL ARREGLO DE PUNTILLOS
	    // LO QUE VOY A HACER ES BUSCAR EL NÚMERO QUE SE SUPONE ES EL MENOR DENTRO DE LOS ENLACES
	    // return menor;
	}

	// public int posicionDelMenorEnPuntillos(){
	//     int enlaceMenor = obtenerMenor();

	//     // return posicionEnPuntillos;
	// }
    }
    // --------------------SBULCSASE----------------------------------

    // ---------------------SUBCLASES VER0.2.--------------------------------


    // LOS PATHS SE VAN AGREGANDO Y QUITANDO
    public class FringeType{

	// estas clases tienen un arreglo adentro que les dice
	// de: (puntillos no key) lista
	// para: (puntillos no key)   lista
	// costo del path:         lista
	// como llenarla de estos datos

	public LinkedList<String> de;
	public LinkedList<String> para;
	public LinkedList<String> pathcost;
	
	public FringeType(){
	    de = new LinkedList<String>();
	    para = new LinkedList<String>();
	    pathcost = new LinkedList<String>();
	}

	public void addPath(String from, String to, String costing){
	    this.de.add(from);
	    this.para.add(to);
	    this.pathcost.add(costing);
	}
	public void removePath(String from, String to, String costing){
	    this.de.remove(from);
	    this.para.remove(to);
	    this.pathcost.remove(costing);
	}

	// show paths y costo
	public void showPaths(){
	    for (int i = 0; i < de.size(); i++) {
		System.out.printf("%d Path de %s a %s con costo %s \n", i, this.de.get(i), this.para.get(i), this.pathcost.get(i));
	    }
	}

	// indicar cual es el menor de los paths
	public int menorDeLosPaths(){
	    // SI NO ESTA VACIO
	    int menor;
	    int next = 0;
	    int posMenor = 0;

	    if (this.pathcost.size() != 0){
		menor = Integer.parseInt(this.pathcost.get(0));;
		// for (int i = 0; i < this.de.size() - 1; i++) {
		for (int i = 0; i < this.de.size() - 1; i++) {
		    // siempre poner this. sino toma la 1era ref.
		    next = Integer.parseInt(this.pathcost.get(i+1));
		    if (menor > next) {
			menor = next;
			posMenor = i + 1;
		    }
		}
		System.out.printf("El menor de los paths es de %s a %s con costo %s \n y esta en la posicion %d \n", de.get(posMenor), para.get(posMenor), pathcost.get(posMenor), posMenor);
		return posMenor;
	    } else {
		System.out.printf("No hay nada de paths");
		return 2500;
	    }
	    

	}

    }
    // ---------------------SBULCSAE SVER0.2.--------------------------------






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
    public TSPlastHoursUltimateFinal(){
	setSize(800,600);
	setVisible(true);
    }
    public static void main(String[] args){
	TSPlastHoursUltimateFinal GG = new TSPlastHoursUltimateFinal();
	// GG.repaint();
    }
}

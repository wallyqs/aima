// figura
class Figura{

    public int numCoordenadas;
    public int coordsX[];
    public int coordsY[];

    public Figura(){
	this.numCoordenadas = 0;
    }
    
    public Figura(int a){
	this.numCoordenadas = a;
    }

    public void setNCoordenadas(int a){
	this.numCoordenadas = a;
    }
    public int getNCoordenadas(){
	return this.numCoordenadas;
    }
    public void inicializarCoordsArray(){
	coordsX = new int[this.numCoordenadas];
	coordsY = new int[this.numCoordenadas];
    }

    // método para imprimir el contenido del objeto
    public void myInfo(){

	System.out.printf("Número de Coordenadas: %d\n", numCoordenadas);
	
	// imprimir coordenadas

	for( int j = 0; j < coordsY.length; j++){
	    System.out.printf("Coordenada en X: %d\n", coordsX[j] );
	    System.out.printf("Coordenadas en Y: %d\n", coordsY[j] );
	}
    }
} // fin de la subclase Figura
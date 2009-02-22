// NOTES:
// - NO DEBE DE AGREGAR VALORES QUE YA ESTABAN
var canvasWidth = 500;
var canvasHeight = 500;
// AHORA *SOLO* FALTA IMPLEMENTAR EL ALGORITMO CON BACKTRACKING

// construir el mapa de AUSTRALIA
window.onload = init;
function init(){
  document.getElementById("showoutput").addEventListener("click", init, false);
  var nodos = [
    new Nodo(50,150, "WA"),	//0
    new Nodo(140,100, "NT"),	//1
    new Nodo(140,200, "SA"),	//2
    new Nodo(220,140, "Q"),	//3
    new Nodo(260,200, "NSW"),	//4
    new Nodo(210,270, "V")	//5
    ];
  paintAll(nodos);
  conectarNodos(nodos[0], nodos[1]);  conectarNodos(nodos[0], nodos[2]);  conectarNodos(nodos[1], nodos[2]);
  conectarNodos(nodos[1], nodos[3]);  conectarNodos(nodos[2], nodos[3]);  conectarNodos(nodos[2], nodos[4]);
  conectarNodos(nodos[2], nodos[5]);  conectarNodos(nodos[3], nodos[4]);  conectarNodos(nodos[5], nodos[4]);
  nodos[0].imprimirVecinos();  nodos[1].imprimirVecinos();  nodos[2].imprimirVecinos();
  nodos[3].imprimirVecinos();  nodos[4].imprimirVecinos();  nodos[5].imprimirVecinos();
  puts("DONE INITIALIZING...");

  // --------------------------------
  // como se hace el algoritmo???
  // pongo un valor una variable: le quito ese valor a las demas.
  // Como escojo a la siguiente variable???
  //
  // al escoger un valor para una variable, escojo el primer valor del dominio
  // que se pueda escoger. Si el primero no se puede porque afecta el futuro
  // se le puede pasar un parametro para que escoja el siguiente
  var variablesAsignadas = [];
  var nodi = nodos[0];		//este es el root, a partir de acá todo empieza
  var count = 0;
  while (count < 10) {
    puts(nodi.nombre + ":::::::::");
    nodi.assignedValue = escogerValor(nodi); limpiarDominio(nodi);
    comoAfectaVecinos(nodi);  paintAll(nodos);
    variablesAsignadas.push(nodi.nombre);
    putsArray(variablesAsignadas);
    // aqui tengo que escoger un vecino que no este en la lista de variablesAsignadas
    // escogerVecinoNoAsignado(nodi,variablesAsignadas);
    // nodi = nodi.vecinos[0];
    nodi = escogerVecinoNoAsignado(nodi,variablesAsignadas);
    count++;
  }

  // seleccionar al primer vecino que tenga

  // voy a hacerlo que viaje de nodo en nodo. Que trate un camino.
  // y si no se pudo ese camino que se vaya por el otro.
  // entonces, cada vez asigno una variable tengo que guardar el estado???
  // no no, creo que estoy mal.	Tengo que darle heuristicas o algo asi...
  // por el momento voy a hacerlo que se mueva de nodo en nodo...


  // escogerVariable(nodos);


}




// code ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
function escogerValor(nodamen, recorrer){
  if (arguments.length == 1) {
  // puts("Numero de argumentos: " + arguments.length);
  return nodamen.domain[0];
  } else {
    return nodamen.domain[0 + recorrer];
  }
}

function limpiarDominio(nodamen){
  for (var i = -1; i <= nodamen.domain.length; i++) {
    nodamen.domain.pop();
    // putsArray(nodamen.domain);
  }
  nodamen.domain.push(nodamen.assignedValue);
  putsArray(nodamen.domain);
}

  // se maneja a nivel de nombres!!!
  function escogerVecinoNoAsignado(nodamen,vars){
    var index = 0;
    var vecinosQueNoEstan = [];
    // if (vars.contains(nodamen.vecinos[0])) {
    puts(nodamen.nombre);
    nodamen.imprimirVecinos();
    puts(vars.indexOf("NT"));
    puts(vars.indexOf("SA"));
    puts(vars.indexOf("WA"));
    // indexOf es -1 si es que no existe en el arreglo
    // tengo que hacer esto por cada uno de los vecinos de nodamen:: nodamen.vecinos.length

    for (var i = 0; i < nodamen.vecinos.length; i++) {
      if (vars.indexOf(nodamen.vecinos[i].nombre) == -1) {
	puts("No esta el vecino en la lista");
	vecinosQueNoEstan.push(i);
      } else {
	puts("Si esta en la lista y esta en la posicion" + vars.indexOf(nodamen.vecinos[i]));
      }
    }
    putsArray(vecinosQueNoEstan);
    return nodamen.vecinos[vecinosQueNoEstan[0]];
  }



  function comoAfectaVecinos(nodamen){
    puts("Comenzando a buscar desde..." + nodamen.nombre);
    // primero revisar si tiene el valor de nodamen[0].assignedValue
    // algunos de sus vecinos
    // tengo que buscar en cada uno de sus vecinos
    for (var i = 0; i < nodamen.vecinos.length; i++) {
      puts(nodamen.vecinos[i].nombre);
      puts (i);
      // creo que la mayoria de este codigo no es necesario
      for (var k = 0; k < nodamen.vecinos[i].domain.length; k++) {
	if (nodamen.assignedValue == nodamen.vecinos[i].domain[k]) {
	  // puts("lo encontre... y lo borro");
	  nodamen.vecinos[i].borrar(nodamen.assignedValue);
	}
      }
      puts("--------");
    }
  }


function paintAll(everything){
  for (var i = 0;  i < everything.length; i++) {
    everything[i].paintMyself();
  }
}


function conectarNodos(nodo1, nodo2){
  var canvas = document.getElementById("canvas").getContext("2d");
  canvas.beginPath();
  canvas.lineWidth = 2;
  canvas.moveTo(nodo1.coorx, nodo1.coory);
  canvas.lineTo(nodo2.coorx, nodo2.coory);
  canvas.stroke();
  // canvas.strokeText("probandoesto", 10,10);
  // TO DO:
  // agregar a la lista de vecinos en los dos nodos
  // no se debe de agregar el nombre!!!
  nodo1.addVecino(nodo2);
  nodo2.addVecino(nodo1);
}

// no sirve esto en ningún browser, どうしたらいいんだろう。。。
function texter(){
  var canvas = document.getElementById("canvas").getContext("2d");
  canvas.fillStyle = "#f0f";
  // canvas.font = "sans-serif";
  canvas.font         = 'bold 30px sans-serif';
  canvas.fillText("Hello world!", 0, 0);
  canvas.font         = 'bold 30px sans-serif';
  canvas.strokeText('Hello world!', 0, 0);
}

function drawCircle(x, y,canvas, color){
  // canvas.fillStyle = random_color("hex");
  if (color == ""){
  canvas.fillStyle = "#222";
  } else if (color == "r"){
    canvas.fillStyle = "#f00";
  } else if (color == "g"){
    canvas.fillStyle = "#0f0";
  } else if (color == "b"){
    canvas.fillStyle = "#00f";
  }
  // canvas.fillStyle = "#f00";
  canvas.beginPath();
  canvas.arc(x,y,20,0,2*Math.PI, true);
  canvas.fill();
}

// ------------------------------ class Nodo -------------------------------
// primero el constructor...
// como le declaro sus variables privadas...
// quiero pasarle en su constructor como se llame
// y que se escriba en el circulo
function Nodo( coorx, coory, nombre){
  this.coorx = coorx;
  this.coory = coory;
  this.domain = ["r", "g", "b"];
  this.vecinos = [];
  this.nombre = nombre;
  this.assignedValue = "";
}

Nodo.prototype.paintMyself = function (){
  var canvas = document.getElementById("canvas").getContext("2d");
  drawCircle(this.coorx, this.coory, canvas, this.assignedValue);
};

// BORRAR Y ADD SON PARA LOS VALORES DEL DOMINIO
Nodo.prototype.borrar = function (elemento){
  var i = this.domain.indexOf(elemento);
  // puts ("Esta en el index: " + i);
  puts ("borrando: " + elemento);
  this.domain.splice(i+1);
  putsArray(this.domain);
};

Nodo.prototype.add = function (elemento){
  puts("agregando: " + elemento);
  this.domain.push(elemento);
  putsArray(this.domain);
};

Nodo.prototype.addVecino = function (elemento){
  this.vecinos.push(elemento);
};

Nodo.prototype.imprimirVecinos = function (){
  puts("Vecinos de " + this.nombre);
  putsVecinosNombres(this.vecinos);
};
Nodo.prototype.imprimirVecinos = function (){
  puts("Vecinos de " + this.nombre);
  putsVecinosNombres(this.vecinos);
};
// Nodo.prototype.escogerValor = function (){
  // puts("Vecinos de " + this.nombre);
  // if (arguments.length == 0){
  //   puts(this.domain[0]);
  //   return this.domain[0];
  // } else {
  //   puts(this.domain[0]);
  //   return this.domain[0 + recorrer];
  // }
  // return this.domain[0];
// };

// FRIENDLY CODE ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
function random_color(format)
{
 var rint = Math.round(0xffffff * Math.random());
 switch(format)
 {
  case 'hex':
   return ('#0' + rint.toString(16)).replace(/^#0([0-9a-f]{6})$/i, '#$1');
  break;

  case 'rgb':
   return 'rgb(' + (rint >> 16) + ',' + (rint >> 8 & 255) + ',' + (rint & 255) + ')';
  break;

  default:
   return rint;
  break;
 }
}

// crear numeros aleatorios para las coordenadas... no la necesito
function littleRandom()
{
  minVal = 30;
  maxVal = 450;
  var randVal = minVal+(Math.random()*(maxVal-minVal));
  // return typeof floatVal=='undefined'?Math.round(randVal):randVal.toFixed(floatVal);
  return Math.round(randVal);
}

function putsArray(arreglo){
  for (i = 0; i < arreglo.length; i++) {
    var a = document.getElementById("output").innerHTML;
    document.getElementById("output").innerHTML = a + arreglo[i] + " , ";
  }
  a = document.getElementById("output").innerHTML;
  document.getElementById("output").innerHTML = a + "<br /> ";
}

function putsVecinosNombres(arreglo){
  for (i = 0; i < arreglo.length; i++) {
    var a = document.getElementById("output").innerHTML;
    document.getElementById("output").innerHTML = a + arreglo[i].nombre + " , ";
  }
  a = document.getElementById("output").innerHTML;
  document.getElementById("output").innerHTML = a + "<br /> ";
}

function puts(content){
    var a = document.getElementById("output").innerHTML;
    document.getElementById("output").innerHTML = a + content + "<br />";
  }

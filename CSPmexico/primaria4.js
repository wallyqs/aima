// construyendo el mapa de メキシコ
// tengo algunos problemas al buscar las cosas,
// surgen valores undefined y cosas raras suceden...
// voy a aumentarle el dominio a ver qué pasa...
// problema, siempre escoge el primero de sus valores disponibles... deberia de escoger...
// implementar lo de LEAST CONSTRAINING VALUE
// ME FALTAN BAJA, QUERETARO, TABASCO Y TLAXCALA
window.onload = init;
function init(){
  document.getElementById("showoutput").addEventListener("click", init, false);
  var nodos = [
    new Nodo(300,360, "Aguascalientes"),
    new Nodo(70,110, "Baja"),
    new Nodo(120,200, "BajaSur"),
    new Nodo(640,390, "Campeche"),
    new Nodo(370,100, "Coahuila"), //370,210... 370,160... es el que esta mas arriba. (5)
    new Nodo(320,420, "Colima"),
    new Nodo(600,470, "Chiapas"),
    new Nodo(270,170, "Chihuahua"),
    new Nodo(430,390, "DF"),	//430,410
    new Nodo(300,285, "Durango"),
    new Nodo(390,350, "Guanajuato"),
    new Nodo(420,460, "Guerrero"), //420,450
    new Nodo(470,410, "Hidalgo"),  //460,400
    new Nodo(300,400, "Jalisco"), //300,400
    new Nodo(400,405, "Mexico"),  //420,405
    new Nodo(380,400, "Michoacan"),
    new Nodo(430,440, "Morelos"), //430,420
    new Nodo(200,330, "Nayarit"), //lo voy a mover a la izquierda, 300,330.... NAYARIT ESTA MAL!!!
    new Nodo(400,250, "NuevoLeon"),
    new Nodo(500,470, "Oaxaca"),
    new Nodo(490,440, "Puebla"), //460,420
    new Nodo(420,370, "Queretaro"),
    new Nodo(700,380, "QRoo"),
    new Nodo(390,310, "SLP"),	//390,340
    new Nodo(240,260, "Sinaloa"),
    new Nodo(150,150, "Sonora"),
    new Nodo(590,430, "Tabasco"),
    new Nodo(430,300, "Tamaulipas"),
    new Nodo(490,390, "Tlaxcala"), //450,400
    new Nodo(540,410, "Veracruz"), //500,410
    new Nodo(670,350, "Yucatán"),
    new Nodo(350,320, "Zacatecas")
  ];
  paintAll(nodos);

  // AGUASCALIENTES
  conectarNodos(nodos[0], nodos[13]);
  conectarNodos(nodos[0], nodos[31]);
  // BAJA
  conectarNodos(nodos[1], nodos[2]);
  conectarNodos(nodos[1], nodos[25]);
  // BAJASUR
  // CAMPECHE
  conectarNodos(nodos[3], nodos[22]);
  conectarNodos(nodos[3], nodos[26]);
  conectarNodos(nodos[3], nodos[30]);
  // COAHUILA
  conectarNodos(nodos[4], nodos[7]); //porque no esta entre los 7s !?!?!! Porque ya lo borre.
  conectarNodos(nodos[4], nodos[9]);
  conectarNodos(nodos[4], nodos[18]);
  conectarNodos(nodos[4], nodos[23]);
  conectarNodos(nodos[4], nodos[27]);
  conectarNodos(nodos[4], nodos[31]);
  // COLIMA
  conectarNodos(nodos[5], nodos[13]);
  conectarNodos(nodos[5], nodos[15]);
  // CHIAPAS
  conectarNodos(nodos[6], nodos[19]);
  conectarNodos(nodos[6], nodos[26]);
  conectarNodos(nodos[6], nodos[29]);
  // CHIHUAHUA
  conectarNodos(nodos[7], nodos[9]);
  conectarNodos(nodos[7], nodos[24]);
  conectarNodos(nodos[7], nodos[25]);
  // DF
  conectarNodos(nodos[8], nodos[14]);
  conectarNodos(nodos[8], nodos[16]);
  // Durango
  conectarNodos(nodos[9], nodos[17]); //ehhhhhhhh!!!
  conectarNodos(nodos[9], nodos[24]);
  conectarNodos(nodos[9], nodos[31]);
  // Guanajuato
  conectarNodos(nodos[10], nodos[13]);
  conectarNodos(nodos[10], nodos[15]);
  conectarNodos(nodos[10], nodos[21]);
  conectarNodos(nodos[10], nodos[23]);
  conectarNodos(nodos[10], nodos[31]);
  // Guerrero
  conectarNodos(nodos[11], nodos[14]);
  conectarNodos(nodos[11], nodos[15]);
  conectarNodos(nodos[11], nodos[16]);
  conectarNodos(nodos[11], nodos[19]);
  conectarNodos(nodos[11], nodos[20]);
  // hidalgo
  conectarNodos(nodos[12], nodos[14]);
  conectarNodos(nodos[12], nodos[20]);
  conectarNodos(nodos[12], nodos[21]);
  conectarNodos(nodos[12], nodos[23]);
  conectarNodos(nodos[12], nodos[28]);
  conectarNodos(nodos[12], nodos[29]);
  // jalisco
  conectarNodos(nodos[13], nodos[15]);
  conectarNodos(nodos[13], nodos[17]);
  conectarNodos(nodos[13], nodos[31]);
  // mexico
  conectarNodos(nodos[14], nodos[15]);
  conectarNodos(nodos[14], nodos[16]);
  conectarNodos(nodos[14], nodos[20]);
  conectarNodos(nodos[14], nodos[21]);
  conectarNodos(nodos[14], nodos[28]);
  // michoacán
  conectarNodos(nodos[15], nodos[21]);
  // morelos
  conectarNodos(nodos[16], nodos[20]);
  // nayarit
  conectarNodos(nodos[17], nodos[24]);
  // nuevoleon
  conectarNodos(nodos[18], nodos[23]);
  conectarNodos(nodos[18], nodos[27]);
  // oaxaca
  conectarNodos(nodos[19], nodos[20]);
  conectarNodos(nodos[19], nodos[29]);
  // puebla
  conectarNodos(nodos[20], nodos[28]);
  conectarNodos(nodos[20], nodos[29]);
  // querétaro
  conectarNodos(nodos[21], nodos[23]);
  // qroo
  conectarNodos(nodos[22], nodos[30]);
  // slp
  conectarNodos(nodos[23], nodos[27]);
  conectarNodos(nodos[23], nodos[29]);
  conectarNodos(nodos[23], nodos[31]);
  // sinaloa
  conectarNodos(nodos[24], nodos[25]);
  // sonora
  // tabasco
  conectarNodos(nodos[26], nodos[29]);
  // tamaulipas
  conectarNodos(nodos[27], nodos[29]);
  // tlaxcala
  // veracruz
  // yucatán
  // zacatecas
  // FIN DE LOS CONECTES ENTRE MAPAS:::::::::::::::::::::::::::::
  // nodos[0].imprimirVecinos();
  puts("DONE INITIALIZING...");
  // ------------------------------------------------------------------------------------------------------------

  // yendo desde COAHUILA::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
  var variablesAsignadas = [];
  var nodi = nodos[5];		//este es el root:::COAHUILA
  var count = 0;
  // while (variablesAsignadas.length < 31) {
  // desde aguascalientes (0) llega hasta el ciclo 16
  // desde coahuila (5) llega hasta el ciclo 11
  while (count < 11) {
    puts(nodi.nombre + ":::::::::");
    nodi.assignedValue = escogerValor(nodi); limpiarDominio(nodi);
    comoAfectaVecinos(nodi);  paintAll(nodos);
    variablesAsignadas.push(nodi.nombre);
    putsArray(variablesAsignadas);
    nodi = escogerVecinoNoAsignado(nodi,variablesAsignadas);
    puts("CICLO .-.-..-.-" + count);
    count++;
  }

  puts("porque no me puedo ir por campeche? creo que por que se cicla y deja de correrlo...");
  puts("OK!");
  // DESDE CAMPECHE :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ciclo 2
  variablesAsignadas = [];
  nodi = nodos[3];		//este es el root:::CAMPECHE
  count = 0;
    while (count < 3) {
    puts(nodi.nombre + ":::::::::");
    nodi.assignedValue = escogerValor(nodi); limpiarDominio(nodi);
    comoAfectaVecinos(nodi);  paintAll(nodos);
    variablesAsignadas.push(nodi.nombre);
    putsArray(variablesAsignadas);
    nodi = escogerVecinoNoAsignado(nodi,variablesAsignadas);
    puts("CICLO .-.-..-.-" + count);
    count++;
  }

  puts("acabo CAMPECHE");
  // DESDE PUEBLA :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ciclo 4
  variablesAsignadas = [];
  nodi = nodos[20];		//este es el root:::PUEBLA
  count = 0;
    while (count < 4) {
    puts(nodi.nombre + ":::::::::");
    nodi.assignedValue = escogerValor(nodi); limpiarDominio(nodi);
    comoAfectaVecinos(nodi);  paintAll(nodos);
    variablesAsignadas.push(nodi.nombre);
    putsArray(variablesAsignadas);
    nodi = escogerVecinoNoAsignado(nodi,variablesAsignadas);
    puts("CICLO .-.-..-.-" + count);
    count++;
  }
  puts("acabo PUEBLA");

    // DESDE VERACRUZ :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ciclo 9
  variablesAsignadas = [];
  nodi = nodos[29];		//este es el root:::PUEBLA
  count = 0;
    while (count < 9) {
    puts(nodi.nombre + ":::::::::");
    nodi.assignedValue = escogerValor(nodi); limpiarDominio(nodi);
    comoAfectaVecinos(nodi);  paintAll(nodos);
    variablesAsignadas.push(nodi.nombre);
    putsArray(variablesAsignadas);
    nodi = escogerVecinoNoAsignado(nodi,variablesAsignadas);
    puts("CICLO .-.-..-.-" + count);
    count++;
  }
  puts("acabo VERACRUZ");

  // DESDE SLP  :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ciclo 10
  variablesAsignadas = [];
  nodi = nodos[23];		//este es el root:::PUEBLA
  count = 0;
    while (count < 10) {
    puts(nodi.nombre + ":::::::::");
    nodi.assignedValue = escogerValor(nodi); limpiarDominio(nodi);
    comoAfectaVecinos(nodi);  paintAll(nodos);
    variablesAsignadas.push(nodi.nombre);
    putsArray(variablesAsignadas);
    nodi = escogerVecinoNoAsignado(nodi,variablesAsignadas);
    puts("CICLO .-.-..-.-" + count);
    count++;
  }
  puts("acabo SLP");

  // DESDE Tamaulipas Wendolyn ランド  :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ciclo 10
  variablesAsignadas = [];
  nodi = nodos[27];		//este es el root:::PUEBLA
  count = 0;
    while (count < 10) {
    puts(nodi.nombre + ":::::::::");
    nodi.assignedValue = escogerValor(nodi); limpiarDominio(nodi);
    comoAfectaVecinos(nodi);  paintAll(nodos);
    variablesAsignadas.push(nodi.nombre);
    putsArray(variablesAsignadas);
    nodi = escogerVecinoNoAsignado(nodi,variablesAsignadas);
    puts("CICLO .-.-..-.-" + count);
    count++;
  }
  puts("acabo TAMAULIPAS");

  // DESDE Nuevo Leon   :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ciclo 10
  variablesAsignadas = [];
  nodi = nodos[18];		//este es el root:::PUEBLA
  count = 0;
    while (count < 10) {
    puts(nodi.nombre + ":::::::::");
    nodi.assignedValue = escogerValor(nodi); limpiarDominio(nodi);
    comoAfectaVecinos(nodi);  paintAll(nodos);
    variablesAsignadas.push(nodi.nombre);
    putsArray(variablesAsignadas);
    nodi = escogerVecinoNoAsignado(nodi,variablesAsignadas);
    puts("CICLO .-.-..-.-" + count);
    count++;
  }
  puts("acabo NUEVOLEON");

  // estados que faltan: Baja, 2 de enmedio y Tabasco...
  // AQUI ESTABA!!!!!!!!// AQUI ESTABA!!!!!!!!// AQUI ESTABA!!!!!!!!// AQUI ESTABA!!!!!!!!
  // ME FALTAN BAJA, QUERETARO, TABASCO Y TLAXCALA
  // BAJASUR
  nodi = nodos[2];
  nodi.assignedValue = escogerValor(nodi); limpiarDominio(nodi);
  comoAfectaVecinos(nodi);  paintAll(nodos);
  // QUERETARO
  nodi = nodos[21];
  nodi.assignedValue = escogerValor(nodi); limpiarDominio(nodi);
  comoAfectaVecinos(nodi);  paintAll(nodos);
  // TABASCO
  nodi = nodos[26];
  nodi.assignedValue = escogerValor(nodi); limpiarDominio(nodi);
  comoAfectaVecinos(nodi);  paintAll(nodos);
  // TLAXCALA
  nodi = nodos[28];
  nodi.assignedValue = escogerValor(nodi); limpiarDominio(nodi);
  comoAfectaVecinos(nodi);  paintAll(nodos);

  // seleccionar al primer vecino que tenga
  // voy a hacerlo que viaje de nodo en nodo. Que trate un camino.
  // y si no se pudo ese camino que se vaya por el otro.
  // entonces, cada vez asigno una variable tengo que guardar el estado???
  // no no, creo que estoy mal.	Tengo que darle heuristicas o algo asi...
  // por el momento voy a hacerlo que se mueva de nodo en nodo...
  // escogerVariable(nodos);


}//initの終わり


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
  puts("check0: "); putsArray(nodamen.domain);
  puts("check00: "); puts(nodamen.assignedValue);
  // for (var i = -2; i <= nodamen.domain.length; i++) {
    // nodamen.domain.pop();
  nodamen.domain = [];
    // putsArray(nodamen.domain);
  // }
  puts("check1: "); putsArray(nodamen.domain); //no deberia de haber nada!!!
  puts("check2: "); puts(nodamen.assignedValue);
  nodamen.domain.push(nodamen.assignedValue);
  puts("check3: "); putsArray(nodamen.domain);
}

// se maneja a nivel de nombres!!!
function escogerVecinoNoAsignado(nodamen,vars){
  var index = 0;
  var vecinosQueNoEstan = [];
  // if (vars.contains(nodamen.vecinos[0])) {
  puts(nodamen.nombre);
  nodamen.imprimirVecinos();
  // ESTO ESTOY CASI SEGURO QUE ES UN BUG...
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
    canvas.fillStyle = "#444";
  } else if (color == "r"){
    canvas.fillStyle = "#f00";
  } else if (color == "g"){
    canvas.fillStyle = "#0f0";
  } else if (color == "b"){
    canvas.fillStyle = "#00f";
  } else if (color == "a"){
    canvas.fillStyle = "#ffa3a3";
  } else if (color == "w"){
    canvas.fillStyle = "#fff";
  }

  // canvas.fillStyle = "#f00";
  canvas.beginPath();
  canvas.arc(x,y,5,0,2*Math.PI, true);
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
  this.domain = ["r", "g", "b", "a", "w"];
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

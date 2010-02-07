x_old = 0;
x_new = 6;
epsilon = 0.01;
precision = 0.00001;

function derivada(x){
  // return (4*(x^3)) - (9*(x^2));
  // return 4*(Math.pow(x,3)) - 9(Math.pow(x,2));
  return 4*x*x*x - 9*x*x;
}

function puts(s){
  var content =  document.getElementById("output");
  content.innerHTML += s + "<br />";
}

window.onload = function(){
  puts("Buscando el minimo con usando el gradiente <br />");
  while(Math.abs(x_new - x_old) > precision){
    x_old = x_new;
    x_new = x_new - epsilon * derivada(x_new);
    // var s = [x_old, x_new].join("");
    // puts(x_old + x_new + "");
    // puts(s);
    puts("Old:"+ x_old + "");
    puts("New:"+ x_new + "");
  }
};

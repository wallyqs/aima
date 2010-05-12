#!/usr/bin/env ruby -wKU
require 'benchmark'

class Point
  attr_accessor :coorx, :coory
end

def distance(p2, p1)
  Math.sqrt((p2.coorx - p1.coorx )**2 + (p2.coory - p1.coory)**2)
end

# setup for calculating the distance between the cities
cities_number = 8
# cities_number = 12
e = Math::E
pi = Math::PI

cities = Array.new(cities_number).map!{ Point.new}
cities_distances = Array.new(cities_number,0).map!{Array.new(cities_number,0)}
step = 2*pi/cities.length       # e.g. step = pi/4
rounding_condition = 1e-15
neg_rounding_condition = 1e-15

# Sacar la posicion de cada una de las ciudades
# ciudad 0 tiene las coordenadas 1,0
# ciudad 1 tiene las coordenadas sin pi/4, cos pi/4
# ciudad 2 tiene las coordenadas sin 2pi/4, cos 2pi/4
for i in (0..(cities.length - 1))
  this_step = i*step
  x = Math.cos(this_step)
  y = Math.sin(this_step)

  cities[i].coorx = x
  cities[i].coory = y

  cities[i].coorx = x.prec_i.to_f if ( x >= 0 and x < rounding_condition  )
  cities[i].coorx = x.prec_i.to_f if ( x < 0  and -x < neg_rounding_condition )

  cities[i].coory = y.prec_i.to_f if ( y >= 0 and y < rounding_condition  )
  cities[i].coory = y.prec_i.to_f if ( y < 0  and -y < neg_rounding_condition )

end


# sacar la distancia entre cada uno de los puntos
# y ponerlo dentro de su respectiva casilla
# por cada una de las ciudades, vamos por la ciudad 0
puts Benchmark.measure {
  for i in (0..(cities.length - 1))
    puts "\ndesde la ciudad #{i} -----------------------------------"
    for j in (0..(cities.length - 1))
      print "\nhacia la ciudad #{j}"
      dis = distance(cities[i], cities[j])
      cities_distances[i][j] = dis
      print " la distancia es de #{dis}"
    end
  end
}

# ahora ponerlo dentro de un archivo
# simane_data = File.open("simane_data_from_datamaker.txt", "w+")
# simane_data.puts(cities_distances)
# simane_data.close

system("echo '' > simane_data_from_datamaker.dump")
File.open('simane_data_from_datamaker.dump', 'w+') do |f|  
  Marshal.dump(cities_distances, f)  
end 

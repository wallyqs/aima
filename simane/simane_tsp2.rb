#!/usr/bin/env ruby -wKU
class Point
  attr_accessor :coory, :coorx
end

# setup for calculating the distance between the cities
# cambiar number of cities de 8 a 100
# no importa que se tengan valores que tengan exponentes muy pequenhos
cities_number = 100
r = 6
e = Math::E
pi = Math::PI
cities = Array.new(cities_number, Point.new)
cities_distances = Array.new(cities_number,0).map!{Array.new(cities_number,0)}
perimeter = 2*pi*r
angle = 0                       # starting angle
step = 2*pi/cities.length       # step = pi/4
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

  # cities[i].coory = y.round

  # cities[i].coorx = x.round if cities[i].coorx < rounding_condition
  # cities[i].coory = y.round if cities[i].coory < rounding_condition
  # puts cities[i].inspect
end

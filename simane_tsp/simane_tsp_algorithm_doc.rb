#!/usr/bin/env ruby -wKU

# funcion de probabilidad
def P(energy_new, energy_current, temperature)
  Math::E**((energy_new - energy_current)/temperature)
end

# distancia total de ir por ese trayecto
# vc es current solution y es un path
# vn es new solution
# cities_distances[a[0]][a[1]]  distancia de la ciudad 0 a 1
# cities_distances[0][1]  distancia de la ciudad 0 a 1
def eval_solution(cities_dist, current_solution)
  if cities_dist[0].length == current_solution.length
    total_distance = current_solution.inject(0) do |suma, numero|
      if  current_solution.last == numero
        # puts "estoy en el final"
        suma += cities_dist[current_solution.first][current_solution.last]
        # puts "Resultado final: #{suma}"
        suma
      else
        # puts "#{suma} le sumo #{cities_dist[numero][current_solution[numero + 1]]}"
        suma += cities_dist[numero][current_solution[numero + 1]] unless current_solution[numero + 1].nil?
        # puts "y me da #{suma}"
        suma
      end
    end
    return total_distance
  else
    "cities_distances y current_solution son de diferentes tamanhos: #{cities_dist.length}, #{current_solution.length}"
  end
end

# 2swap method, e.g.
# input:  [0, 1, 2, 3, 4, 5, 6, 7]
# output: [0, 2, 1, 3, 4, 5, 6, 7]
# i,j no pueden ser el inicial
def find_neighboorhood(current_solution)
  # se repite hasta que encuentre un par de valores buenos
  puts current_solution.inspect
  begin
    i = rand(current_solution.length)
    j = rand(current_solution.length)
  end while (i == current_solution.first or j == current_solution.first or i == j)
  item1 = current_solution[i]
  swapped_item1 = item1
  item2 = current_solution[j]
  current_solution[i] = item2
  current_solution[j] = swapped_item1

  puts current_solution.inspect
  return current_solution
  # puts "item1: #{item1}"
  # puts "item2: #{item2}"

end

# current_solution = (0..(cities_number - 1)).map{|i| a[i] = i }
# INIT ----------------------------------------------------
e = Math::E
pi = Math::PI
cities_distances = Array.new
File.open("simane_data_from_datamaker.dump", 'r') do |file|
  cities_distances = Marshal.load(file)
end
cities_number = cities_distances[0].length
temperature = 1000
iterations = 0                  # k
max_iterations = 100            # halting conditions...
r = 0.00001 

current_solution = (0..(cities_number - 1)).map!{|i| i } # => [0,1,2,3,4,5,6,7]
#current_solution.shuffle!                                # realiza una permutacion sobre el arreglo
p current_solution




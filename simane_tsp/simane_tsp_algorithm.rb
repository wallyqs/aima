#!/usr/bin/env ruby -wKU

# funcion de probabilidad
def P(eval_vn, eval_vc, temperature)
  Math::E**((eval_vn - eval_vc)/temperature)
end

# distancia total de ir por ese trayecto
# vc es current solution y es un path
# vn es new solution
# cities_distances[a[0]][a[1]]  distancia de la ciudad 0 a 1
# cities_distances[0][1]  distancia de la ciudad 0 a 1
def eval_solution(current_solution)
  cities_dist = Array.new
  File.open("simane_data_from_datamaker.dump", 'r') do |file|
    cities_dist = Marshal.load(file)
  end

  if cities_dist[0].length == current_solution.length
    total_distance = current_solution.inject(0) do |suma, numero|
      if  current_solution.last == numero
        suma += cities_dist[current_solution.first][current_solution.last]
        suma
      else
        unless current_solution[numero + 1].nil?
          suma += cities_dist[numero][current_solution[numero + 1]]
        end
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
def find_neighboorhood(current_solution_vc)
  # se repite hasta que encuentre un par de valores buenos
  # puts "vc: " + current_solution_vc.inspect

  # clonar el arreglo
  current_solution = current_solution_vc.clone

  begin
    i = rand(current_solution.length)
    j = rand(current_solution.length)
  end while (i == current_solution.first or j == current_solution.first or i == j)
  item1 = current_solution[i]
  swapped_item1 = item1
  item2 = current_solution[j]
  current_solution[i] = item2
  current_solution[j] = swapped_item1

  # puts "vn: " + current_solution.inspect
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


# ESTO SE VA A INICIALIZAR CON ARGUMENTOS
# max_iterations = 100            # halting conditions...

# realizar una permutacion sobre el arreglo
# => [0,1,2,3,4,5,6,7]

# ALGORITMO DE SIMANE ----------------------------------------------------
# Tmax = Temperature maxima
# k = numero de iteraciones, cooling schedule
# r = factor de disminucion, cooling ratio
# Tmin = Temperatura minima

cities_number = cities_distances[0].length
Tmax = 500
Tmin = 1
k = 20
r = 0.5

# Step 1
temperature = Tmax
# select vc at random
current_solution = (0..(cities_number - 1)).map {|i| i }
current_solution.shuffle!
vc = current_solution
best_current_solution = vc.clone
solutions_list = Array.new

# while (temperature <= 0)
# Step 3
while (temperature > Tmin)
  (0..(k)).each do
    vn = find_neighboorhood(vc)
    puts "vc: #{vc.inspect} y vn: #{vn.inspect}"
    puts "vc: #{eval_solution(vc)} y vn: #{eval_solution(vn)} "
    if eval_solution(vn) < eval_solution(vc)

      p "MINIMIZAR? ---------------------------------"
      p "new     #{vn.inspect} #{eval_solution(vn)}"
      p "current #{vc.inspect} #{eval_solution(vc)}"
      p "-------------------------------------------"
      vc = vn.clone

      if eval_solution(vc) < eval_solution(best_current_solution)
        p "yep, minimizar!"
        best_current_solution = vc.clone
        p "best solution so far: #{best_current_solution.inspect}"
        p "with #{eval_solution(best_current_solution)}"
        solutions_list.push(best_current_solution)
      end

    else
      probability = P(eval_solution(vn), eval_solution(vc), temperature)
      vc = vn.clone if rand < probability # probability select
      p "escoger al azar"
    end
  end
  # cool it!
  # temperature = r * temperature
  temperature -= r
  p "temperatura: #{temperature}"
end

puts "las soluciones"
solutions_list.each do |sol|
  puts "#{sol.inspect}: #{eval_solution(sol)}"
end

# p "new #{vn.inspect} #{eval_solution(vn)}"
# p "current #{vc.inspect} #{eval_solution(vc)}"
# vc = vn.clone               # minimizar
# p "MINIMIZAR ---------"
# p "new #{vn.inspect} #{eval_solution(vn)}"
# p "current #{vc.inspect} #{eval_solution(vc)}"

# if temperature <= Tmin
# else
# end

# probability = P(eval_solution(vn), eval_solution(vc), temperature)
# p probability
# QUIERO PROBAR MUCHAS PROBABILIDADES
# (0..1000).each { |t|
#   temperature = 1000 - t
#   p temperature
#   vc = current_solution
#   vn = find_neighboorhood(vc)     # modifica a la solucion anterior!!!
#   p vn
#   p vc
#   puts "new: #{eval_solution(vn)}  y current: #{eval_solution(vc)}"
#   p Math::E**((eval_solution(vn) - eval_solution(vc))/t)
# }

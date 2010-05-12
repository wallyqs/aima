#!/usr/bin/env ruby -wKU

require 'benchmark'
require 'SVG/Graph/Line'

# CALCULATES FITNESS
def eval_solution(current_solution)
  cities_dist = Array.new

  File.open("simane_data_from_datamaker.dump", 'r') do |file|
    cities_dist = Marshal.load(file)
  end

  if cities_dist[0].length == current_solution.length
    total_distance = current_solution.inject(0) do |suma, numero|
      # print "desde ", numero, "----------\n"
      if  current_solution.last == numero
        # print "ohacia ", numero, "\n"
        suma += cities_dist[current_solution.first][current_solution.last]
        # print "la suma es ", suma, "\n"
        suma
      else

        # print "acaso es nil senhor?\n" if current_solution[numero + 1].nil?
        unless current_solution[numero + 1].nil?
          # print "hacia ", numero + 1, "\n"
          next_index = current_solution.index(numero) + 1
          suma += cities_dist[numero][current_solution[next_index]]
          # suma += cities_dist[numero][current_solution[numero + 1]]          
          # suma += cities_dist[numero][numero + 1]
          # print "la suma es ", suma, "\n"
        else 
          # significa que es la ultima ciudad del arreglo e.g 7
          # print "hacia ", 0, "\n"
          # suma += cities_dist[numero][current_solution[0]]
          suma += cities_dist[numero][0]
          # print "la suma es ", suma, "\n"
        end

        suma
      end
    end
    return total_distance
  else
    "cities_distances y current_solution son de diferentes tamanhos: #{cities_dist.length}, #{current_solution.length}"
  end

end

# RETURNS TWO CHILDS
def crossover(parent1, parent2)
  #  ABCD EFGHI and IGAH FDBEC.
  # e.g. Crossover point is after the fourth character
  #  ABCD IGHFE and IGAH BCDEF.

  n = parent1.length
  locus = rand(n)
  child1 = Array.new
  child2 = Array.new

  locus.times {|i| child1 << parent1[i]}
  locus.times {|i| child2 << parent2[i]}

  parent2.each do |item|
    child1 << item unless child1.include?(item)
  end

  parent1.each do |item|
    child2 << item unless child2.include?(item)
  end
  [child1, child2]

end

# RETURNS A MUTATED CHROMOSOME
def mutate(chromosome)
  chromosome.nil? ? (p 'what?') : n = chromosome.length
  pos1 = rand(n)
  pos2 = rand(n)
  temp = chromosome[pos1]
  chromosome[pos1] = chromosome[pos2]
  chromosome[pos2] = temp
  chromosome
end

def total_fitness(generation)
  total_generation_fitness = generation.inject(0) do |total, g|
    total += eval_solution(g)
    total
  end
end

def sort_generation(generation)
  # generation.sort!
  # aqui debo de acomodar las probabilidades y las respuestas
  sorted_generation = generation.clone

  # i,j son del tipo generation: Bubble Sort!
  # deben de estar de menor a mayor. Entre menor sea mejor es la sol
  (0..(sorted_generation.length - 1)).each do |i|
    (0..(sorted_generation.length - 1)).each do |j|
      if eval_solution(generation[i]) > eval_solution(generation[j])
        temp = generation[j]
        generation[j] = generation[i]
        generation[i] = temp
      end
    end
  end

end


def randomly_delete(generation)
  r = rand(generation.count - 1)
  generation.delete_at(r)
end

# INIT ------------------------------------------------------
cities_distances = Array.new
File.open("simane_data_from_datamaker.dump", 'r') do |file|
  cities_distances = Marshal.load(file)
end
locuses = cities_distances[0].length
p_c = 0.7                         # crossover probability
p_m = 0.01                        # mutation probability o 0.001
best_generation = []

# 1) first generation, randomly generated, generation size
n = 4                           # even number of chromosomes
# n = 12
max_generations = ARGV[1].to_i if ARGV[0] == "-n"
max_generations = 100 if ARGV[0].nil?

generation = Array.new
generation_fitnesses = Array.new
n.times { generation << [0,1,2,3,4,5,6,7].shuffle }
# n.times { generation << [0,1,2,3,4,5,6,7,8,9,10,11].shuffle }
best_generation = generation.clone

puts Benchmark.measure { 
  max_generations.times do |cual_iteracion|

    offsprings_generation = Array.new
    offsprings_number = 0
    begin
      # Los arreglo de menor a mayor?, coincide que si son en orden
      # son mas optimos
      # generation.sort!
      # generation.reverse!
      sort_generation(generation)
      p generation

      # Print de como estan acomodadas las probabilidades
      puts "FITNESS DE LA GENERACION. DEBE DE ESTAR ORDENADA!!!" if ARGV[2] == "-debug"
      puts generation.map{ |item| eval_solution(item)}.inspect if ARGV[2] == "-debug"

      # 2) Calculate fitness of each chromosome.
      fitnesses = generation.map{ |s| eval_solution(s) }

      # Calculate the total fitness of the generation
      total_generation_fitness = total_fitness(generation)

      # Create a uniform distribution with these values
      uniform_fitnesses = fitnesses.map{ |s| s/total_generation_fitness}

      # Create an accumulated distribution for the selection process
      accumulated_fitnesses = Array.new
      uniform_fitnesses.inject(0) do |total, s|
        total += s
        accumulated_fitnesses << total
        total
      end
      puts "uniforme: #{uniform_fitnesses.inspect}"  if ARGV[2] == "-debug"
      puts "acumulada: #{accumulated_fitnesses.inspect}" if ARGV[2] == "-debug"

      # 3) Select a pair. The more fitness means it is more
      # probable to be selected. Select the first one whose
      # accumulated_fitnesses is greater than rand(1)
      # -----------------------
      # p 'generacion'
      # p generation
      # p total_generation_fitness
      # -----------------------

      pair = Array.new
      # indexes_to_be_deleted = Array.new
      2.times do
        r = rand
        puts "random: #{r}" if ARGV[2] == "-debug"
        puts "son mayores: #{accumulated_fitnesses.select {|s| s > r}.inspect}" if ARGV[2] == "-debug"

        index = n - accumulated_fitnesses.select {|s| s > r}.count
        pair << generation[index]
        # borrar los que se escogieron?, de todos modos al final los voy a
        # meter de nuevo...
        # indexes_to_be_deleted << index
      end

      # indexes_to_be_deleted.each do |i|
      #   generation.delete_at(i)
      # end

      # Todo lo que se calculo anteriormente ya no importa, solo era
      # para el select. *****************************************************

      # CREATE OFFSPRING!

      # 4) Crossover the pair (or not)
      p pair if ARGV[2] == "-debug"
      if rand < p_c
        cpair = crossover(pair.first, pair.last)
        # puts 'SI hubo crossover'
      else
        # p 'NO hubo crossover'
        cpair = pair.clone
      end

      # 5) Mutate (or not)
      cpair.each do |mchromosome|
        locuses.times do
          if rand < p_m
            mchromosome = mutate(mchromosome)
          end
        end
      end

      # 6) add these offspring to the current population
      cpair.each do |chromosome|
        offsprings_generation << chromosome
      end

      offsprings_number += 2
      puts "offsprings_number: #{offsprings_number}"if ARGV[2] == "-debug"
    end while (offsprings_number < n)

    # 7) Reemplazamos la generacion por la de puros offsprings
    generation = offsprings_generation.clone

    # 8) verificar que el numero de chromosomas que se tiene es el adecuado
    # sino randomly delete
    while (generation.count != n)
      # puts '----------------------------------------'
      # p    "generation cut #{generation.inspect}"
      # puts "#{generation.count}, #{n}"
      randomly_delete(generation)
      # puts '----------------------------------------'
    end

    if total_fitness(generation) < total_fitness(best_generation)
      best_generation = generation.clone
    else
      generation = best_generation
    end

    # para graficar, REPEAT A LOT OF TIMES, escoger cuantas generaciones
    generation_fitnesses << total_fitness(generation).truncate
    puts "TOTAL_FITNESS: #{generation_fitnesses.last}" if ARGV[2] == "-debug"

    #puts "------------------------------------------------------------------------------------------"
  end
}


generation_fitnesses.inject(0) do |i, g|
  print "#{i}: #{g},  "
  i += 1
end

p "GENERACION_FINAL: #{generation.inspect}"
# p "best_generation: #{best_generation.inspect}"
p "Members of the best_generation"
best_generation.each do |gen|
  print gen.inspect, eval_solution(gen), "\n"
end
p "best_generation_eval: #{total_fitness(best_generation)}"

# # QUIERO HACER UNA GRAFICA DE ESTO -------------------------
fields = Array.new
(0..(max_generations)).each {|i| fields << "#{i}" }

# data_sales_02 = [12, 45, 21]
# data_sales_03 = [15, 30, 40]
# (0...50).map{|i| xaxis << i}

graph = SVG::Graph::Line.new({
                               :height => 600,
                               :width => 1200,
                               :fields => fields
                             })

graph.add_data({
                 :data => generation_fitnesses,
                 :title => 'Fitness!',
               })

# GRAFICANDO!!!
# graph.burn()
system("echo '' >> grafica.svg")
File.open('grafica.svg', 'w+') do |f|
  f.write(graph.burn)
end

# falta lo mismo de siempre, guardar la mejor respuesta...

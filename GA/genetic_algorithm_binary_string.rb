#!/usr/bin/env ruby -wKU
require 'benchmark'
require 'SVG/Graph/Line'


# calculates fitness
def eval_solution(current_solution)
  total_ones = current_solution.inject(0) do |total, bit|    
    total += 1 if bit > 0
    total
  end
end

# returns two childs
def crossover(parent1, parent2)
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

# returns a mutated chromosome
def mutate(chromosome)
  n = chromosome.length
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

# se debera de ordenar de menor a mayor?
def sort_generation(generation)
  sorted_generation = generation.clone
  # i,j son del tipo generation: BUBBLE SORT! XD
  # deben de estar de menor a mayor. Entre menor sea mejor es la sol
  (0..(sorted_generation.length - 1)).each do |i|
    (0..(sorted_generation.length - 1)).each do |j|
      if eval_solution(generation[i]) < eval_solution(generation[j])
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

# => [0 1 0 1 | 1 0 1 1]
def random_solution
  solution = []
  8.times do 
    solution << rand(2)
  end
  solution
end


# INIT ------------------------------------------------------

locuses = 8
p_c = 0.7                         # crossover probability
p_m = 0.001                        # mutation probability o 0.001
best_generation = []

# 1) first generation, randomly generated, generation size
#    even number of chromosomes
n = 8                           
max_generations = ARGV[1].to_i if ARGV[0] == "-n"
max_generations = 2 if ARGV[0].nil?

generation = Array.new
generation_fitnesses = Array.new
n.times { generation << random_solution() }

best_generation = generation.clone

max_generations.times do
  
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

    # CREATE A UNIFORM DISTRIBUTION WITH THESE VALUES, POR ACA VOY MAL
    uniform_fitnesses = fitnesses.map{ |s| s/total_generation_fitness}

    # Create an accumulated distribution for the selection process
    accumulated_fitnesses = Array.new
    uniform_fitnesses.inject(0) do |total, s|
      total += s
      accumulated_fitnesses << total
      total
    end
    p total_fitness(generation)
    puts "uniforme: #{uniform_fitnesses.inspect}"  if ARGV[2] == "-debug"
    puts "acumulada: #{accumulated_fitnesses.inspect}" if ARGV[2] == "-debug"
    
    # 3) Select a pair. The more fitness means it is more
    # probable to be selected. Select the first one whose
    # accumulated_fitnesses is greater than rand(1)

    pair = Array.new

    2.times do
      r = rand
      puts "random: #{r}" if ARGV[2] == "-debug"
      puts "son mayores: #{accumulated_fitnesses.select {|s| s > r}.inspect}" if ARGV[2] == "-debug"
      
      index = n - accumulated_fitnesses.select {|s| s > r}.count
      pair << generation[index]
    end

    # Todo lo que se calculo anteriormente ya no importa, solo era
    # para el select. *****************************************************
    
    # CREATE OFFSPRING!
    p pair
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
  #    else => randomly delete
  while (generation.count != n) 
    randomly_delete(generation)
  end

  if total_fitness(generation) < total_fitness(best_generation)
    best_generation = generation.clone
  else
    generation = best_generation
  end

  # PARA GRAFICAR... REPEAT A LOT OF TIMES, escoger cuantas generaciones
  generation_fitnesses << total_fitness(generation).truncate
  puts "TOTAL_FITNESS: #{generation_fitnesses.last}" if ARGV[2] == "-debug"

end

generation_fitnesses.inject(0) do |i, g|
  print "#{i}: #{g},  "
  i += 1
end

p "GENERACION_FINAL: #{generation.inspect}"
p "best_generation: #{best_generation.inspect}"
p "best_generation_eval: #{total_fitness(best_generation)}"


# # QUIERO HACER UNA GRAFICA DE ESTO -------------------------
fields = Array.new
(0..(max_generations)).each {|i| fields << "#{i}" }

graph = SVG::Graph::Line.new({:height => 600, :width => 1200,
                              :fields => fields})

graph.add_data({ :data => generation_fitnesses,
                 :title => 'Fitness!' })

# GRAFICANDO!!!
system("echo '' >> grafica.svg")
File.open('grafica.svg', 'w+') do |f|
  f.write(graph.burn)
end

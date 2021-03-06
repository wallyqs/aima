#!/usr/bin/ruby
require "gsl"
solver = Root::FSolver.new(Root::FSolver::BISECTION)
f = GSL::Function.new { |x, params|
  a = params[0]
  b = params[1]
  c = params[2]
  (a*x + b)* + c
}
f.set_params(1,0,-5)
expected = Math.sqrt(5.0)
printf("%5s [%9s, %9s] %9s %10s %9s \n", "iter",
       "lower", "upper", "root", "err", "err(est)")
solver.set(f, 0.0, 5.0)
iter = 0
status = nil
IO.popen("graph -T X -C -g 3 -X Iterations -Y 'Root'\
         --toggle-rotate-y-label\
         -L 'x^2 - 5 = 0, #{solver.name}' -I e -S 4", "w") do |io|
  while status != GSL::SUCCESS
    iter += 1
    status = solver.root
    xl = solver.x_lower
    xu = solver.x_upper
    status = solver.test_interval(0, 0.001)
    if status == GSL::SUCCESS
      printf("Converged:\n")
    end
    printf("%5d [%.7f, %.7f] %.7f %+.7f %.7f \n",
           iter, xl, xu, r, r - expected, xu -xl)
    io.printf("%d %e %e\n", iter, r, (xu - xl)/2)
  end
end

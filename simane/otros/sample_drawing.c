#include <stdio.h>
#include <plot.h>
#define MAXORDER 12

void 
draw_c_curve (plPlotter *plotter, double dx, double dy, int order)
{
  if (order >= MAXORDER)
    pl_fcontrel_r (plotter,dx,dy);
  else 
    {
      draw_c_curve (plotter,
		    0.5 * (dx - dy),
		    0.5 * (dx + dy),
		    order + 1);
      draw_c_curve (plotter,
		    0.5 * (dx + dy),
		    0.5 * (dx - dy),
		    order + 1);
    }
}

int main()
{
  plPlotter *plotter;
  plPlotterParams *plotter_params;

  plotter_params = pl_newplparams();
  pl_setplparam (plotter_params, "PAGESIZE", "letter");

  
  return 0;
}



#include <stdio.h>
#include <plot.h>
#define MAXORDER 12

void draw_c_curve (double dx, double dy, int order)
{
  if (order >= MAXORDER)
    pl_fcontrel (dx, dy);     /* continue path along (dx, dy) */
  else
    {
      draw_c_curve (0.5 * (dx - dy), 0.5 * (dx + dy), order + 1);
      draw_c_curve (0.5 * (dx + dy), 0.5 * (dy - dx), order + 1);
    }
}

int main ()
{
  int handle;

  /* set a Plotter parameter */
  pl_parampl ("PAGESIZE", "letter");

  /* create a Postscript Plotter that writes to standard output */
  if ((handle = pl_newpl ("ps", stdin, stdout, stderr)) < 0)
    {
      fprintf (stderr, "Couldn't create Plotter\n");
      return 1;
    }
  pl_selectpl (handle);       /* select the Plotter for use */

  if (pl_openpl () < 0)       /* open Plotter */
    {
      fprintf (stderr, "Couldn't open Plotter\n");
      return 1;
    }
  pl_fspace (0.0, 0.0, 1000.0, 1000.0); /* specify user coor system */
  pl_flinewidth (0.25);       /* line thickness in user coordinates */
  pl_pencolorname ("red");    /* path will be drawn in red */
  pl_erase ();                /* erase Plotter's graphics display */
  pl_fmove (600.0, 300.0);    /* position the graphics cursor */
  draw_c_curve (0.0, 400.0, 0);
  if (pl_closepl () < 0)      /* close Plotter */
    {
      fprintf (stderr, "Couldn't close Plotter\n");
      return 1;
    }

  pl_selectpl (0);            /* select default Plotter */
  if (pl_deletepl (handle) < 0) /* delete Plotter we used */
    {
      fprintf (stderr, "Couldn't delete Plotter\n");
      return 1;
    }
  return 0;
}

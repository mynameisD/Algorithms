import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats{
 
    private double [] threshold;
    
    public PercolationStats(int N, int T){     // perform T independent experiments on an N-by-N grid
	if(N<=0 || T<=0) throw new IllegalArgumentException("Index is out of bound");
        threshold=new double[T];
        double cum=0; //cumulative(sum) of x
        for(int i=0;i<T;i++){
            Percolation p = new Percolation(N);
            int n=0;
            while(!p.percolates()){
               int x = StdRandom.uniform(N)+1;
               int y = StdRandom.uniform(N)+1;
               if(!p.isOpen(x,y)){
                   n+=1;
                   p.open(x,y);
               }
            }
            threshold[i]=((double)n)/N/N;
        }
    }
    public double mean(){                      // sample mean of percolation threshold
        return StdStats.mean(threshold);
    }
    public double stddev(){                    // sample standard deviation of percolation threshold
        return StdStats.stddev(threshold);
    }
    public double confidenceLo(){              // low  endpoint of 95% confidence interval
        return mean()-1.96*StdStats.stddev(threshold)/Math.sqrt(threshold.length);
    }
    public double confidenceHi(){              // high endpoint of 95% confidence interval
        return mean()+1.96*StdStats.stddev(threshold)/Math.sqrt(threshold.length);
    }
    public static void main(String[] args){    // test client (described below)
        int n=0,t=0;
        Stopwatch walltime = new Stopwatch();
        if (args.length > 0) {
            try {
                n = Integer.parseInt(args[0]);
                t = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Arguments must be two integers.");
                System.exit(1);
            }
        }
        PercolationStats ps = new PercolationStats(n,t);
        StdOut.println("percolation threshold of "+n+" by "+n+" grid");
        StdOut.println("percolation run time: "+walltime.elapsedTime()+"s");
        StdOut.println("Mean: "+ps.mean());
        StdOut.println("Standard Deviation: "+ps.stddev());
        StdOut.println("Confidence Interval: ["+ps.confidenceLo()+","+ps.confidenceHi()+"]");
    }
}
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class Percolation {
    //defination:
    //Full site: connected path to top row
    //Percolation: connected path from top to bottom (bottom row has full sites)

    private WeightedQuickUnionUF uf;    //Weighted quick-union by rank with path compression by halving.
    private boolean [] board;
    private int Row,Col;//number of Row and number of Col
    
    public Percolation(int N){               // create N-by-N grid, with all sites blocked
	if(N<=0) throw new IllegalArgumentException("Invalid grid size");
        uf = new WeightedQuickUnionUF(N*N+2);//0 for top dummy node
        Row=N;Col=N;
        board = new boolean[N*N+2];
	board[0]=true;board[N*N+1]=true;
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
		board[getIndex(i,j)]=false;
            }
        }
    }
    public void open(int i, int j){          // open site (row i, column j) if it is not open already
        if(i<1 || j<1 || i>Row || j>Col) throw new IndexOutOfBoundsException(i+" "+j+" is out of bound");
	if(!board[getIndex(i,j)]){
	    board[getIndex(i,j)]=true; //set to open
	    int index=getIndex(i,j);
	    for(int[] neigh: new int [][]{{i - 1, j}, {i, j + 1}, {i + 1, j}, {i, j - 1}}){
		int x=neigh[0];
		int y=neigh[1];
		if(x<=Row+1&&x>=0&&y<=Col&&y>0){
		    int n=getIndex(x,y);
		    if(board[n]){
			uf.union(index,n);
		    }                
		}
	    }
	}
    }
    public boolean isOpen(int i, int j){     // is site (row i, column j) open?
        if(i<1 || j<1 || i>Row || j>Col) throw new IndexOutOfBoundsException("Index out of bound");
        return board[getIndex(i,j)];
    }
    public boolean isFull(int i, int j){     // is site (row i, column j) full?
        if(i<1 || j<1 || i>Row || j>Col) throw new IndexOutOfBoundsException("Index out of bound");
        return uf.connected(0,getIndex(i,j));
    }
    public boolean percolates(){            // does the system percolate?
        return uf.connected(0,Row*Col+1);
    }
   private int getIndex(int x,int y){
       if(x==0) return 0; //the first row is the dummy top node
       if(x==Row+1) return Row*Col+1;
       return (x-1)*Col+y;
   }
   public static void main(String[] args){// test client (optional)
       int N = StdIn.readInt();
       Percolation bd= new Percolation(N);
       while(!StdIn.isEmpty()){
           int p = StdIn.readInt();
           int q = StdIn.readInt();
           if(!bd.isOpen(p,q)) bd.open(p,q);
       }
       if(bd.percolates()) StdOut.println("percolated");
       if(bd.isFull(3,1))  StdOut.println("full");
   }
}
import java.util.*; 
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private int move = 0; //total number of moves 
    private boolean solvable = true;  
    private Queue<Board> minpath = null;  //result slover
    
    private class SearchNode {
        private Board board;  
        private int steps;  
        private Board prev;  // the previous 
        private int rank;  //previouse moves + manhattan distance
        
        public SearchNode(Board b, int m, Board pre) {
            this.board = b;  
            this.steps = m; 
            this.prev = pre;
            this.rank = m + b.manhattan();  
        }
    }
    
    private class NodeComparator implements Comparator<SearchNode> {
        public int compare(SearchNode a, SearchNode b) {
            if(a.rank < b.rank) return -1; 
            if(a.rank > b.rank) return 1;
            return 0; 
        }
    }
    
    public Solver(Board initial) { // find a solution to the initial board (using the A* algorithm)
        if(initial == null) throw new NullPointerException("null initial board");  
        
        MinPQ<SearchNode> q1 = new MinPQ<SearchNode>(new NodeComparator());
        MinPQ<SearchNode> q2 = new MinPQ<SearchNode>(new NodeComparator()); 
        q1.insert(new SearchNode(initial, 0, null));  
        q2.insert(new SearchNode(initial.twin(), 0, null));  
        
        Queue<Board> path1 = new Queue<Board>();
        
        SearchNode cur1,cur2; 
        
        do {
            cur1 = q1.delMin();  
            cur2 = q2.delMin();  
            path1.enqueue(cur1.board);    
            
            for(Board next:cur1.board.neighbors()) {
                if (!next.equals(cur1.prev)) q1.insert(new SearchNode(next,cur1.steps+1,cur1.board));
            }
            for(Board next:cur2.board.neighbors()) {
                if (!next.equals(cur2.prev)) q2.insert(new SearchNode(next,cur2.steps+1,cur2.board)); 
            }
            
        } while (!cur1.board.isGoal() && !cur2.board.isGoal());
        
        if(cur1.board.isGoal()) {
            this.move = cur1.steps;   
            this.minpath = path1;  
        }
        else {this.solvable = false;  }       
    }
    
    public boolean isSolvable() { return this.solvable;  }
    public int moves() { return this.move;  }
    public Iterable<Board> solution() { return this.minpath;  }
        
    public static void main(String[] args) {

    // create initial board from file
    In in = new In(args[0]);
    int N = in.readInt();
    int[][] blocks = new int[N][N];
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }
}
}
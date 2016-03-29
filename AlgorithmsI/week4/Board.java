import java.util.*;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;  
import edu.princeton.cs.algs4.StdOut;
    
public class Board {
    private int[][] node;  
    private int N;  //dimension of board
    
    private int emptyx,emptyy; // the empty location
    
    public Board(int[][] blocks) {// construct a board from an N-by-N array of blocks
        if(blocks == null) throw new NullPointerException("null contructuion array");
        this.N = blocks.length;  
        node = new int[N][N];  
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                node[i][j] = blocks[i][j];
                if(node[i][j] == 0) {
                    emptyx = i;
                    emptyy = j;
                }
            }
        }
    }
    
    public int dimension() { // board dimension N
        return N;
    }
    
    public int hamming() { // number of blocks out of place
        int hamdis = 0;
        for (int i = 0; i < N; i++) {
            for( int j = 0; j < N; j++) {
                if (node[i][j] != 0 && ((node[i][j]-1)/N != i || (node[i][j]-1)%N != j)) 
                    hamdis++;
            }
        }
        return hamdis; 
    }
    
    public int manhattan() { // sum of Manhattan distances between blocks and goal
        int manhdis = 0;  
        for (int i = 0; i < N; i++) {
            for( int j = 0; j < N; j++) {
                if (node[i][j] != 0)
                    manhdis += (Math.abs((node[i][j]-1)/N - i) + Math.abs((node[i][j]-1)%N - j));
            }
        }
        return manhdis;  
    }
    
    public boolean isGoal() {
        return hamming() == 0;    
    }
    
    public Board twin() {
        int a = StdRandom.uniform(this.N*this.N);  
        while(a == emptyx*N + emptyy) {
            a = StdRandom.uniform(this.N*this.N);  
        }
        int b = StdRandom.uniform(this.N*this.N);  
        while(a == b || b == (emptyx*N + emptyy)){
            b = StdRandom.uniform(this.N*this.N); 
        }
        Board twinB = new Board(node);  
        twinB.swap(a/N, a%N, b/N, b%N);  
        return twinB;  
    }
    
    public boolean equals(Object y) { // does this board equal y?
        if (y == this) return true;  
        if (y == null) return false;  
        if (y.getClass() != this.getClass()) return false;  
        
        Board that = (Board) y; 
        
        if (this.N != that.N) return false;
        
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++) {
                if(this.node[i][j] != that.node[i][j]) return false;   
            }
        }
        
        return true;
    }
    
    private void swap(int ax,int ay,int bx,int by) {
        int temp = node[ax][ay];  
        node[ax][ay] = node[bx][by];    
        node[bx][by] = temp;  
        if(node[ax][ay] == 0) {
            emptyx = ax;
            emptyy = ay;
        }
        else if(node[bx][by] == 0) {
            emptyx = bx;  
            emptyy = by;  
        }
    }
    
    public Iterable<Board> neighbors() { // all neighboring boards
        Stack<Board> neighs = new Stack<Board>();
            
        if(this.emptyx > 0) { //push right
            Board newboard = new Board(node);  
            newboard.swap(emptyx,emptyy,emptyx-1,emptyy);  
            neighs.push(newboard);  
        }
        
        if(this.emptyx + 1 < this.N) { //push left
            Board newboard = new Board(node);  
            newboard.swap(emptyx,emptyy,emptyx+1,emptyy);  
            neighs.push(newboard);  
        }
        
        if(this.emptyy + 1 < this.N) { //push up
            Board newboard = new Board(node);  
            newboard.swap(emptyx,emptyy,emptyx,emptyy+1);  
            neighs.push(newboard);  
        }
        
        if(this.emptyy > 0) { //push down
            Board newboard = new Board(node);  
            newboard.swap(emptyx,emptyy,emptyx,emptyy-1);  
            neighs.push(newboard);  
        }
        
        return neighs;  
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();  
        s.append(N + "\n");  
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", node[i][j]));      
            }
            s.append("\n");  
        }
        return s.toString();
    }
    
    public static void main(String[] args) {

    // create initial board from file
    In in = new In(args[0]);
    int N = in.readInt();
    int[][] blocks = new int[N][N];
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    StdOut.println("Hamming distance: "+initial.hamming());  
    StdOut.println("Manhattan distance: "+initial.manhattan());      
    }
}




import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.*; 

public class Subset {
    public static void main(String[] args) {
        
        int N = Integer.parseInt(args[0]);  
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()){
            q.enqueue(StdIn.readString());  
        }
        while (q.size() > N){
            q.dequeue();  
        }
        for (int i = 0; i < N; i++) {
            StdOut.println(q.dequeue());
        }
    }
}
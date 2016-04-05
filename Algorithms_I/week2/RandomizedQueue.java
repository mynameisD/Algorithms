import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.*; 

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item [] items;
    private int N;  
    
    public RandomizedQueue() {                 // construct an empty randomized queue
        items =(Item[]) new Object[2];   
        N=0;  
    }  
    
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) copy[i] = items[i];  
        items = copy;
    }  
   
    public boolean isEmpty() {                 // is the queue empty?
        return N==0;  
    }
    
    public int size() {                       // return the number of items on the queue
        return N;     
    }
    
    public void enqueue(Item item) {           // add the item
        if(item == null) throw new NullPointerException("null item can not be added");  
        if (N == items.length) resize(2 * items.length);
        items[N++] = item;   
    }
    
    public Item dequeue() {                    // remove and return a random item
        if (isEmpty()) throw new NoSuchElementException();
        int random = StdRandom.uniform(N);
        Item item = items[random];
        items[random] = items[N-1]; 
        items[--N] = null; 
        if (N>0 && N==items.length/4) resize(items.length/2);
        return item;  
    }
    
    public Item sample() {                     // return (but do not remove) a random item
        if (isEmpty()) throw new NoSuchElementException();  
        int random = StdRandom.uniform(N);
        return items[random];
    }
    
    public Iterator<Item> iterator() {        // return an independent iterator over items in random order
        return new QueueIterator();
    }
    
    private class QueueIterator implements Iterator<Item> {
       
        private int length; 
        private Item[] newArray;    
        
        public QueueIterator() {
            length = N;  
            newArray = (Item[]) new Object[length];  
            for(int i=0; i < length; i++) {
                newArray[i] = items[i];  
            }  
        }  
        
        public boolean hasNext() { return length >0;  } 
        
        public void remove() {throw new UnsupportedOperationException();  }
            
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No next elements from empty list");      
            int random = StdRandom.uniform(length);  
            Item item = newArray[random];  
            newArray[random] = newArray[length-1];
            newArray[--length] = null;
            return item;
        }
    }
    
    public static void main(String[] args) {   // unit testing
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();  
        StdOut.println("1.isEmpty()");
        StdOut.println("2.size()");  
        StdOut.println("3.enqueue()");   
        StdOut.println("4.dequeue()");  
        StdOut.println("5.sample()");  
        StdOut.println("6.iterator()");  

        while(!StdIn.isEmpty()) {
            int action =  StdIn.readInt();
            switch (action) {
                case 1: 
                    if(q.isEmpty()) StdOut.println("Deque is empty");
                    else StdOut.println("Deque is NOT empty");
                    break;
                case 2:
                    StdOut.println(Integer.toString(q.size()));  
                    break;
                case 3:
                    int addn = StdIn.readInt();
                    q.enqueue(addn);  
                    break;
                case 4:
                    StdOut.println(Integer.toString(q.dequeue()));  
                    break;
                case 5:
                    StdOut.println(Integer.toString(q.sample()));  
                    break;
                case 6:
                    for(Iterator<Integer> i=q.iterator();i.hasNext(); ) {  
                    int item = i.next();
                    StdOut.println(Integer.toString(item));
                }
                    break;
            }
        }
    }    
        
}
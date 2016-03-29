import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.*;  

public class Deque<Item> implements Iterable<Item> {
    private Node first,last;    
    private int N;  
    
    private class Node {
        Item item;  
        Node next = null;  
        Node prev = null;  
    }
    
    public Deque() {
        N = 0;  
        first = new Node();  
        last  = new Node();  
        first.next = last;  
        last.prev = first;  
    }
    
    public boolean isEmpty() {
        return N==0;  
    }
    
    public int size() {
        return N;  
    }
    
    public void addFirst(Item item) {
        if(item == null) throw new NullPointerException("Attempts to add a null item");  
        Node newFirst = new Node();  
        first.item = item;  
        first.prev = newFirst;  
        newFirst.next = first;  
        first = newFirst;  
        N++;  
    }
    
    public void addLast(Item item) {
        if(item == null) throw new NullPointerException("Attempts to add a null item");          
        Node newLast = new Node();  
        last.item = item;  
        last.next = newLast;  
        newLast.prev=last;  
        last=newLast;  
        N++;  
    }
    
    public Item removeFirst() {
        if(isEmpty()) throw new NoSuchElementException("dequeue from an empty list");  
        Item item = first.next.item;  
        first.next = first.next.next;  
        first.next.prev = first;  
        N--;  
        return item;  
    }
    
    public Item removeLast() {
        if(isEmpty()) throw new NoSuchElementException("dequeue from an empty list");  
        Item item = last.prev.item;
        last.prev = last.prev.prev;  
        last.prev.next = last;  
        N--;  
        return item;  
    }  
    
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node current = first.next;  
        public boolean hasNext() {return current != last;  }  
        public void remove() {throw new UnsupportedOperationException("remove not supported");  }  
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException("no more items");  
            Item item = current.item;
            current = current.next;  
            return item;
        }
    }  
    
    public static void main(String[] args) {
        Deque<Integer> q = new Deque<Integer>();  
        StdOut.println("1.isEmpty()");
        StdOut.println("2.size()");  
        StdOut.println("3.addFirst()");   
        StdOut.println("4.addLast()");  
        StdOut.println("5.removeFirst()");  
        StdOut.println("6.removeLast()");  
        StdOut.println("7.iteration()");  
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
                    q.addFirst(addn);  
                    break;
                case 4:
                    int n = StdIn.readInt();
                    q.addLast(n);  
                    break;  
                case 5:
                    StdOut.println(Integer.toString(q.removeFirst()));  
                    break;
                case 6:
                    StdOut.println(Integer.toString(q.removeLast()));  
                    break;
                case 7:
                    for(Iterator<Integer> i=q.iterator();i.hasNext(); ) {  
                    int item = i.next();
                    StdOut.println(Integer.toString(item));
                }
                    break;
            }
        }
    }
    
}
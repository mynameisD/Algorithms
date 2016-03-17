import java.util.*;  
import edu.princeton.cs.algs4.*;  

public class BruteCollinearPoints { 
    
    private int N_segments;
    private Node first = null;
    
    private class Node {
       LineSegment line;  
       Node next = null;
    } 
    
    private class PointComparator implements Comparator<Point> {
        @Override
        public int compare(Point a,Point b) {
            return a.compareTo(b);  
        }
    }
    
    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        int N = points.length;  
        for(int i = 0; i < N; i++) {
            for(int j = i+1; j < N; j++) {
                for(int k = j+1; k < N; k++) {
                    for(int l = k+1; l < N; l++) {
                        if(points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) 
                               && points[i].slopeTo(points[k]) == points[i].slopeTo(points[l])) {
                            Point[] temp = {points[i], points[j], points[k], points[l]}; 
                            Arrays.sort(temp, new PointComparator());  
                            Node prev = first;  
                            first = new Node();
                            first.line = new LineSegment(temp[0],temp[3]);  
                            first.next = prev;  
                            N_segments++;
                        }
                    }
                }
            }
        }  
    }
    
    public int numberOfSegments() {       // the number of line segments
        return N_segments;  
    } 
    
    public LineSegment[] segments() {               // the line segments
        LineSegment[] array = new LineSegment[N_segments];
        int index = 0;  
        Node cur = first;  
        while(cur != null) {
            array[index++] = cur.line;  
            cur = cur.next;
        } 
        return array;  
    }
    
    public static void main(String[] args) {
        StdOut.println("How many points would like to input?");  
        int N = StdIn.readInt();
        
        Point[] p = new Point[N];  
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setXscale(-10, 10);
        StdDraw.setYscale(-10, 10);
        
        for(int i = 0; i < N; i++) {
            int x = StdIn.readInt();  
            int y = StdIn.readInt();  
            p[i] = new Point(x,y);  
            p[i].draw();  
        }
        
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.setPenRadius(0.02);
        BruteCollinearPoints collinearpoints = new BruteCollinearPoints(p);  
        LineSegment[] line = collinearpoints.segments();  
        for(int i = 0; i < collinearpoints.N_segments; i++) {
            line[i].draw();  
        }
    }
}
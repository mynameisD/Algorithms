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
        if(points == null) throw new NullPointerException("illegal input arguments");  
        int N = points.length;  
        for(int i = 0; i < N; i++) {
            if(points[i] == null) throw new NullPointerException("null point detected");  
            for(int j = i+1; j < N; j++) {
                if(points[i].slopeTo(points[j]) == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException("Repeated Points");  
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
        
        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }

}
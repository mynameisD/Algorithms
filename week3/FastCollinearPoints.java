import java.util.*;  
import edu.princeton.cs.algs4.*;  

public class FastCollinearPoints {
    
    private int N_segments;  //the number of distinct linesegments
    private ArrayList<LineSegment> lines;  //the list to store linesegments
        
    private class PointComparator implements Comparator<Point> {
        @Override
        public int compare(Point a,Point b) {
            return a.compareTo(b);  
        }
    }
    
    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        if(points == null) throw new NullPointerException("illegal input arguments"); 
        int N = points.length;
        for(int i = 0; i < N; i++) {
            if(points[i] == null) throw new NullPointerException("null point detected");  
            if(i > 1 && points[i].compareTo(points[i-1]) == 0) throw new IllegalArgumentException("Repeated Points");
        }
          
        Point[] copy = Arrays.copyOf(points, N);  
        lines = new ArrayList<LineSegment>();  
        
        Arrays.sort(copy, new PointComparator());  //sort the orign array by x,y cordinate;
        
        for(int i = 0; i < N; i++) {
            Point[] temp = Arrays.copyOf(copy, N); 
            Arrays.sort(temp, copy[i].slopeOrder());  
            //object sort is stable(merge sort), the result temp array is firstly sort by x,y then by slope to i.
            //by default the temp[0] is always the point itself, so if temp[1] is negative infinity, it's repeat point 
            for(int j = 0; j < N; ) {
                int cur = j+1;  
                
                //test if next point has same slope to point[i]
                while(cur< N && copy[i].slopeTo(temp[cur]) == copy[i].slopeTo(temp[cur-1])) {
                    cur++;  
                }
                if(cur-j >= 3 && temp[j].compareTo(copy[i]) > 0) {
                    LineSegment newline = new LineSegment(copy[i],temp[cur-1]);  
                    lines.add(newline);  
                    N_segments++;  
                }
                
                j = cur;  
            } 
        } 
    }
    
    public int numberOfSegments() {       // the number of line segments
        return N_segments;  
    } 
    
    public LineSegment[] segments() {               // the line segments
        LineSegment[] res = new LineSegment[N_segments];  
        for(int i = 0; i < N_segments; i++) {
            res[i] = lines.get(i);  
        }
        return res;  
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }
}
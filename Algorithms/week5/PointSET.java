import edu.princeton.cs.algs4.*; 

public class PointSET {
    private SET<Point2D> points;  
    
    public PointSET() {
        points = new SET<Point2D>();      
    }

    public boolean isEmpty() { return points.isEmpty();  }
    
    // number of points in the set
    public int size() { return points.size();  }
    
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) { 
        if (p == null) throw new NullPointerException("argument to PointSET.insert() is null");
        points.add(p);  
    }
    
    // does the set contain point p?    
    public boolean contains(Point2D p) { 
        if (p == null) throw new NullPointerException("argument to PointSET.contains() is null");
        return points.contains(p);  
    } 
 
    // draw all points to standard draw
    public void draw() { 
        for (Point2D p:points) { p.draw();  }   
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) { 
        if (rect == null) throw new NullPointerException("argument to PointSET.range() is null");
        Queue<Point2D> q = new Queue<Point2D>();  
        for (Point2D p:points) {
            if (rect.contains(p)) q.enqueue(p);  
        }
        return q;  
    } 
    
    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) { 
        if (p == null) throw new NullPointerException("argument to PointSET.nearest() is null");
        if (this.isEmpty()) return null;  
        
        Point2D res = points.min();
        double minDis = p.distanceTo(points.min());  
        for (Point2D i:points) {
            if (p.distanceTo(i) < minDis) {
                minDis = p.distanceTo(i);  
                res = i;  
            }
        }
        return res;
    }
    public static void main(String[] args) {    }
    
}
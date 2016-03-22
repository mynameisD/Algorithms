import edu.princeton.cs.algs4.*; 

public class KdTree {
    private Node root;   
    
    private class Node {  
        private Point2D point;  //point object as both key and val 
        private Node left,right;  
        private int N;  
        
        private Node (Point2D p, int N) { 
            this.point = p;  
            this.N = N;  
        }
    }
    
    public KdTree() { }
    
    public boolean isEmpty() { return this.N == 0;  }
    
    public int size() { return N;  }
    
    public void insert(Point2D p) { 
        if (p == null) throw new NullPointerException("null point insetion");  
        root = put(root, p, 1);  // root level = 1  
    }
    
    private Node put(Node cur, Point2D p, int level) {        
        if (cur == null) { return new Node(p, 1);   }
        
        if (level%2 != 0) { //vertical level, compare x
            if (p.x < cur.point.x) cur.left = put(cur.left, p, level+1);  
            else if (p.x > cur.point.x) cur.right = put(cur.right, p, level+1);  
            throw new UnsupportedOperationException ("vertically collinear points not supported");  
        }
        
        else if(level%2 == 0) { //alternate level, compare y
            if(p.y < cur.point.y) cur.left = put(cur.left, p, level+1);  
            else if (p.y > cur.point.y) cur.right = put(cur.right, p, level+1);  
            throw new UnsupportedOperationException ("horizontal collinear points not supported"); 
        }
        
        cur.N = 1 + size(x.left) + size(x.right); 
    }
    
    
    public boolean contains(Point2D p) { 
        if (p == null) throw new NullPointerException("argument to contains() is null");
        return get(p) != null;  
    }  
   
    public Point2D get(Point2D p) { return get(root, p, 1);  }
    
    private Point2D get(Node cur, Point2D p, int level) {
        if (cur == null) return null;  
        
        if (p.equals(cur.point)) return p;  
        
        if (level%2 != 0) { //vertical level, compare x
            if (p.x < cur.point.x) cur.left = put(cur.left, p, level+1);  
            else if (p.x > cur.point.x) cur.right = put(cur.right, p, level+1);  
            throw new UnsupportedOperationException ("vertically collinear points not supported");  
        }
        
        else if(level%2 == 0) { //alternate level, compare y
            if(p.y < cur.point.y) cur.left = put(cur.left, p, level+1);  
            else if (p.y > cur.point.y) cur.right = put(cur.right, p, level+1);  
            throw new UnsupportedOperationException ("horizontal collinear points not supported"); 
        }
        
    }
    
    
    public int size() { return size(root);  }
    private int size(Node x) { 
        if (x == null) return 0;  
        else return x.N;
    }
    
}
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
    
    public boolean isEmpty() { return size() == 0;  }
        
    public int size() { return size(root);  }
    private int size(Node x) { 
        if (x == null) return 0;  
        else return x.N;
    }
    
    public void insert(Point2D p) { 
        if (p == null) throw new NullPointerException("null point insetion");  
        root = put(root, p, 1);  // root level = 1  
    }
    
    private Node put(Node cur, Point2D p, int level) {        
        if (cur == null) { return new Node(p, 1);   }

        if (cur.point.equals(p)) return cur;
            
        if (level%2 != 0) { //vertical level, compare x
            if (p.x() <= cur.point.x()) cur.left = put(cur.left, p, level+1);  
            else cur.right = put(cur.right, p, level+1);  
        }
        
        else { //alternate level, compare y
            if(p.y() <= cur.point.y()) cur.left = put(cur.left, p, level+1);  
            else cur.right = put(cur.right, p, level+1);  
        }
        
        cur.N = 1 + size(cur.left) + size(cur.right); 
        return cur;  
    }
    
    
    public boolean contains(Point2D p) { 
        if (p == null) throw new NullPointerException("argument to contains() is null");
        return get(p) != null;  
    }  
   
    private Point2D get(Point2D p) { return get(root, p, 1);  }
    
    private Point2D get(Node cur, Point2D p, int level) {
        if (cur == null) return null;  
        
        if (p.equals(cur.point)) return p;  
        
        else if (level%2 != 0) { //vertical level, compare x
            if (p.x() <= cur.point.x()) return get(cur.left, p, level+1);  
            else return get(cur.right, p, level+1);    
        }
        
        else { //alternate level, compare y
            if(p.y() <= cur.point.y()) return get(cur.left, p, level+1);  
            else return get(cur.right, p, level+1);  
        }
    }
    
    public void draw() {
        RectHV bound = new RectHV(0.0, 0.0, 1.0, 1.0); 
        draw(root, 1, bound);  
    }
    
    private void draw(Node cur, int level, RectHV rect) {
        if (cur != null) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.02); 
            cur.point.draw();             
            
            if(level % 2 != 0) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius(.005); 
                Point2D start = new Point2D(cur.point.x(),rect.ymin());
                Point2D end   = new Point2D(cur.point.x(),rect.ymax());
                start.drawTo(end);  
                draw(cur.left, level+1, new RectHV(rect.xmin(),rect.ymin(),cur.point.x(),rect.ymax()));  
                draw(cur.right, level+1,new RectHV(cur.point.x(),rect.ymin(),rect.xmax(),rect.ymax()));  
            }
            
            else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.setPenRadius(.005);  
                Point2D start = new Point2D(rect.xmin(),cur.point.y());  
                Point2D end   = new Point2D(rect.xmax(),cur.point.y());  
                start.drawTo(end);  
                draw(cur.left, level+1, new RectHV(rect.xmin(),rect.ymin(),rect.xmax(),cur.point.y()));  
                draw(cur.right, level+1,new RectHV(rect.xmin(),cur.point.y(),rect.xmax(),rect.ymax()));  
            }
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new NullPointerException("argument to range() is null");
        Queue<Point2D> q = new Queue<Point2D>();          
        range(q,rect, root, 1);  
        return q;
    }
    
    private void range(Queue<Point2D> q,RectHV rect, Node cur, int level) {
        if (cur != null) {  
            if (rect.contains(cur.point)) q.enqueue(cur.point);  
            
            if (level % 2 != 0) {
                if (rect.xmin() <= cur.point.x()) range(q, rect, cur.left, level+1);  
                if (rect.xmax() > cur.point.x()) range(q, rect, cur.right, level+1);  
            }
        
            else if (level % 2 == 0) {
                if (rect.ymin() <= cur.point.y()) range(q, rect, cur.left, level+1);  
                if (rect.ymax() > cur.point.y()) range(q, rect, cur.right, level+1);  
            }
        }
    }
    
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException("argument to nearest() is null");
        if (size() == 0) return null; //empty tree
        
        return nearest(p, root, 1, new RectHV(0.0, 0.0, 1.0, 1.0), root.point);  
    }
    
    private Point2D nearest(Point2D p, Node cur, int level, RectHV rect, Point2D candidate) { 
        //candidate remain unchanged if current search didn't find smaller point
        if(cur.point.distanceTo(p) < candidate.distanceTo(p)) candidate = cur.point;  
        
        if (level % 2 !=0 ){
            RectHV Lhalf = new RectHV (rect.xmin(), rect.ymin(), cur.point.x(), rect.ymax());  
            RectHV Rhalf = new RectHV (cur.point.x(), rect.ymin(), rect.xmax(), rect.ymax());  
            
            if (Lhalf.distanceTo(p) < Rhalf.distanceTo(p)) { //search smaller half first
                if (cur.left != null && Lhalf.distanceTo(p) < candidate.distanceTo(p)) 
                    candidate = nearest(p, cur.left, level+1, Lhalf, candidate);  
                if (cur.right !=null && Rhalf.distanceTo(p) < candidate.distanceTo(p))
                    candidate = nearest(p, cur.right,level+1, Rhalf, candidate);  
            }
            else {
                if (cur.right != null && Rhalf.distanceTo(p) < candidate.distanceTo(p)) 
                    candidate = nearest(p, cur.right, level+1, Rhalf, candidate);  
                if (cur.left  != null && Lhalf.distanceTo(p) < candidate.distanceTo(p))
                    candidate = nearest(p, cur.left,  level+1, Lhalf, candidate);
            }     
        }
        
        else {
            RectHV Bhalf = new RectHV (rect.xmin(), rect.ymin(), rect.xmax(), cur.point.y());  
            RectHV Uhalf = new RectHV (rect.xmin(), cur.point.y(), rect.xmax(), rect.ymax());  
            if (Bhalf.distanceTo(p) < Uhalf.distanceTo(p)) {
                if (cur.left != null && Bhalf.distanceTo(p) < candidate.distanceTo(p)) 
                    candidate = nearest(p, cur.left, level+1, Bhalf, candidate);  
                if (cur.right !=null && Uhalf.distanceTo(p) < candidate.distanceTo(p))
                    candidate = nearest(p, cur.right,level+1, Uhalf, candidate);  
            }
            else {
                if (cur.right !=null && Uhalf.distanceTo(p) < candidate.distanceTo(p))
                    candidate = nearest(p, cur.right, level+1, Uhalf, candidate);  
                if (cur.left != null && Bhalf.distanceTo(p) < candidate.distanceTo(p)) 
                    candidate = nearest(p, cur.left,  level+1, Bhalf, candidate);
            }
        }
        return candidate;        
    }
    
    public static void main(String[] args) {}
}
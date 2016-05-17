/* Bloomberg Interview Question
 THERE ARE SEVERAL LOG FILES COMING BY DATE WITH PRODUCT IDS AND
 I NEED TO REPORT THE TOP 10 (PRODUCT IDS) DURING A MOVING PERIOD 
 OF 1 MONTH. DISCUSSED ABOUT THE DATA STRUCTURES NEEDED TO 
 IMPLEMENT THE SOLUTION.*/

import java.util.*;

public class TopProductFromFile {
    private class product {
	int count;
	String name;
	public product(String name) {
	    this.name = name;
	    this.count = 1;
	}
	public void add() {
	    this.count++;
	}
    }

    private HashMap<String,product> records;
    private PriorityQueue<product> queue;

    public TopProductFromFile() {
	this.records = new HashMap<String,product>();
	this.queue = new PriorityQueue<product>(10,new Comparator<product> () {
		public int compare(product a,product b) {
		    return a.count - b.count;
		}
	    });
    }

    public void add(String name) {
	if(records.containsKey(name)) {
	    records.get(name).add();
	}
	else {
	    this.records.put(name,new product(name));
	}

	if(this.queue.contains(records.get(name))) {
	    this.queue.remove(records.get(name));
	}
	this.queue.add(records.get(name));
	if(this.queue.size() > 10) {
	    this.queue.poll();
	}
    } 

    public void printproduct(String name) {
	if(records.containsKey(name)) {
	    System.out.println(records.get(name).name + " " + Integer.toString(records.get(name).count));
	}
	else {
	    System.out.println("records not found");
	}
    }
    private void printTop10() {
	for(product i:this.queue) {
	    System.out.println(i.name + " " + Integer.toString(i.count));
	}
    }
	
    public static void main(String[] args) {
	TopProductFromFile test =new TopProductFromFile();
	test.add("pd1");
	test.add("pd2");
	test.add("pd3");
	test.add("pd1");
	test.add("pd2");
	test.add("pd3");
	test.add("pd1");
	//test.printTop10();
	for(int i = 0; i < 100; ++i) {
	    test.add("pd"+Integer.toString(i%8+10));
	}
	test.printTop10();
    }


}
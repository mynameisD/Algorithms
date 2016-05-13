import java.util.*;

public class BasicCalculationBitOperation {
    private int add(int a,int b) {
	/* test case 1: 101, 100*/
	while (b != 0) {
	    int tmp = a ^ b;
	    b = (a & b) << 1;
	    a = tmp;
	}
	return a;
    }
 
    private int pos_multiple(int a, int b){
	long ans = 0;
	if (a < 0)
	while (b!=0) {
	    if ((b&1) != 0)
		ans += a;
	    a <<= 1;
	    b >>= 1;
	}
	if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE)
	    throw new ArithmeticException("Exceed limit");	    
	return (int)ans;
    }

    private int pos_divide(int a, int b) {
	int ans = 0;
	for(int i = 31; i >=0; --i) {
	    if ((a>>i) >= b) {
		ans += (1<<i);
		a -= (b<<i);
	    }
	}
	return ans;
    }
    public static void main(String[] args) {
	BasicCalculationBitOperation calculator = new BasicCalculationBitOperation();
	System.out.println(Integer.toString(calculator.add(5,-5)));
	System.out.println(Integer.toString(calculator.multiple(2,-22)));
	System.out.println(Integer.toString(calculator.pos_divide(5,2)));
	System.out.println(Integer.toString(-1/2));
	System.out.println(Integer.toString(!2));
	/*
	switch (choice) {
	case 1: 
	System.out.println(Integer.toBinaryString(5)+" + "+Integer.toBinaryString(-4));
	System.out.println(Integer.toString(5-4)+ " " + Integer.toString(calculator.add(5,-4)));
	*/
    }
}

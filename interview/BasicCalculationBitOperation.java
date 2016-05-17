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

    private int multiple(int a, int b) {
	if (isZero(a) || isZero(b))
	    return 0;
	else if (isNegative(a)) {
	    if (isNegative(b)) {
		return pos_multiple(toNegative(a),toNegative(b));
	    }
	    else {
		return toNegative(pos_multiple(toNegative(a),b));
	    }
	}

	else if (isNegative(b)) {
	    return toNegative(pos_multiple(a,toNegative(b)));
	}
	else {
	    return pos_multiple(a,b);
	}
    }

    private int divide(int a, int b) {
	if (isZero(a))
	    return 0;
	if (isZero(b))
	    throw new ArithmeticException("divide by zero");
	if (isNegative(a)) {
	    if (isNegative(b)) {
		return pos_divide(toNegative(a),toNegative(b));
	    }
	    else {
		return toNegative(pos_divide(toNegative(a),b));
	    }
	}

	else if (isNegative(b)) {
	    return toNegative(pos_divide(a,toNegative(b)));
	}
	else {
	    return pos_divide(a,b);
	}
    }

    private boolean isNegative(int a) {
	return (a&0x80000000) != 0;
    }
    private int toNegative(int a) {
	return ~a + 1;
    }

    private boolean isZero(int a) {
	return (a&0xffffffff) == 0;
    }


    public static void main(String[] args) {
	System.out.println("Basic arithmatic calculation '+-*/' with purely bit operations");
	BasicCalculationBitOperation calculator = new BasicCalculationBitOperation();
	System.out.println(calculator.multiple(-11,-5));
	System.out.println(calculator.divide(10,2));
	System.out.println(calculator.divide(-5,2));
	System.out.println(calculator.divide(20,-4));
	System.out.println(calculator.divide(21,-4));
	System.out.println(calculator.divide(-2,-10));
    }
}

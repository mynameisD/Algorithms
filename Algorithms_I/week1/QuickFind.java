import java.util.Scanner;

public class QuickFind{
public static void main(String[] args)
{
    Scanner input = new Scanner (System.in);
    int [] array = new int[10];
    for(int i=0;i<10;i++){
	array[i]=i;
	    }
    while(input.hasNext()){
	int p = input.nextInt();
	int q = input.nextInt();
	int temp = array[q];
	for(int i=0;i<10;i++){
	    if(array[i]==temp){
		array[i]=array[p];
	    }
	}
    }
    for(int i=0;i<10;i++){
	System.out.print(array[i]+" ");
    }
    System.out.println();
}
}

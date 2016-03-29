import java.util.Scanner;

public class UnionFind{
    public static void main(String[] args){
	int [] id=new int[10];
	int [] sz=new int[10];
	for(int i=0;i<10;i++){
	    id[i]=i;
	    sz[i]=1;
	}
	Scanner input=new Scanner(System.in);
	while(input.hasNext()){
	    int p = input.nextInt();
	    int q = input.nextInt();
	    int rootp=p;
	    while(id[rootp]!=rootp){
		rootp=id[rootp];
	    }
	    int rootq=q;
	    while(id[rootq]!=rootq){
		rootq=id[rootq];
	    }
	    if(sz[rootp]<sz[rootq]){
		id[rootp]=id[rootq];
		sz[rootq]+=sz[rootp];
		System.out.print(rootp+" Connected to "+rootq+"\n");
	    }
	    else{
		id[rootq]=id[rootp];
		sz[rootp]+=sz[rootq];
		System.out.print(rootq+" Connected "+rootp+"\n");
	    }
	}
	for(int i=0;i<10;i++){
	    System.out.print(id[i]+" ");
	}
	System.out.println();
    }	
}

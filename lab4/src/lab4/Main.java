package lab4;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	   public static void main(String[] args) {
	        Stack<Integer> stack = new Stack<>();
	        Scanner inp = new Scanner(System.in);
	        int n,k,i,p;
	        System.out.print("n=");
	        n=inp.nextInt();
	        while(n>0)
	        {
	            stack.push(n%10);
	            n=n/10;
	        }
	        k=0;
	        p=1;
	        while(! stack.empty())
	        {
	            i=stack.pop();
	            k=k+i*p;
	            p=p*10;
	        }
	        System.out.println(k);
	    }
	}

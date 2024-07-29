import java.util.*;

public class Main{
	public static void main(String[] args) {        
		Scanner sc = new Scanner(System.in);        
		
		int N = sc.nextInt();
		
		int first = sc.nextInt();
		
		for(int i = 0; i < N - 1; i++) {
			int ring = sc.nextInt();
			int gcd = GCD(first, ring);
			System.out.println(first / gcd + "/" + ring / gcd);
		}
		
	}
	
	public static int GCD(int a, int b) {
		while(b > 0) {
			int mod = a;
			a = b;
			b = mod % b;
		}
		return a;
	}
	
}
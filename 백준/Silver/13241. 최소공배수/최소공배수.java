import java.util.*;

public class Main{
	public static long A;
	public static long B;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();
		long LCM = (A * B) / GCD(A, B);
		System.out.println(LCM);
	}
	
	public static long GCD(long a, long b) {
		
		while(b > 0) {
			long mod = a;
			a = b;
			b = mod % b;
		}
		
		return a;
	}
		
}
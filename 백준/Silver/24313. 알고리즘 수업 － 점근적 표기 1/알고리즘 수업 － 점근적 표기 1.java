import java.util.*;

public class Main{
	public static void main(String[] args) {        
		Scanner sc = new Scanner(System.in);        
		
		int a1 = sc.nextInt();
		int a0 = sc.nextInt();
		int c = sc.nextInt();
		int n0 = sc.nextInt();
		
		int n = 1;
		int answer = 0;

		if((a1 * n0  + a0 <= c * n0) && c >= a1) {
			answer = 1;
		}else {
			answer = 0 ;
		}
		
		System.out.println(answer);
		
	}
	
}
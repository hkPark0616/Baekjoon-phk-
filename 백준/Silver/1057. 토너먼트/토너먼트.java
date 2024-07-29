import java.util.*;

public class Main{
	public static void main(String[] args) {        
		Scanner sc = new Scanner(System.in);        
		
		int N = sc.nextInt();
		
		int kim = sc.nextInt();
		int lim = sc.nextInt();
		
		int rnd = 1;
		while(true) {
			kim = kim/2 + kim%2;
			lim = lim/2 + lim%2;
			
			if(kim == lim) break;
			
			rnd++;
		}
		
		System.out.println(rnd);
		
	}
}
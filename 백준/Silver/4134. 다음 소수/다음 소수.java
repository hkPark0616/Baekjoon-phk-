import java.util.*;

public class Main{
	
	public static void main(String[] args) throws Exception{        
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		for(int t = 0; t < T; t++) {
			long n = sc.nextLong();
	
			while(true) {
				boolean check = isPrime(n);
				
				if(check) {
					sb.append(n + "\n");
					break;
				}else {
					n++;
				}
			}
		}	
		System.out.println(sb);
	}
	
	
	public static boolean isPrime(long num) {
		if(num < 2) {
			return false;
		}
		
		if(num == 2) {
			return true;
		}
		
		for(int i = 2; i <= Math.sqrt(num); i++) {
			if(num % i == 0) {
				return false;
			}
		}
		
		return true;
	}
}
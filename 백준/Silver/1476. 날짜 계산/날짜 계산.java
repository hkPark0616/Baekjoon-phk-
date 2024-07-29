import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int E = sc.nextInt();
		int S = sc.nextInt();
		int M = sc.nextInt();
		
		int year = 1;
		// 1 1 1 start
		int e = 1; int s = 1; int m = 1;
		
		while(true) {	
			if(e == E && s == S && m == M) {
				break;
			}
			if(e == 16) {
				e = e - 15;
			}else if(s == 29) {
				s = s - 28;
			}else if(m == 20) {
				m = m - 19;
			}else {
				e++;
				s++;
				m++;
				year++;
			}
			
		}

		System.out.println(year);
	}
	
}

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int title = 0;
		int n = 0;
		
		while(true) {
			title++;

			if(String.valueOf(title).contains("666")) {
				n++;
				if(n == N) break;
			}
			
		}
		
		System.out.println(title);
		
	}

}

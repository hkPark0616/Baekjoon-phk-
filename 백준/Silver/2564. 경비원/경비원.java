import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int w = sc.nextInt();
		int h = sc.nextInt();
		
		int n = sc.nextInt();
		int p = 0;
		int arr[] = new int[n];
		
		int sum = 0; 
		
		for(int i = 0; i < n + 1; i++) {
			int dir = sc.nextInt();
			int loc = sc.nextInt();
			int tmp = 0;
			switch(dir) {
				case 1:
					tmp = loc;
					break;
					
				case 2:
					tmp = w + h + w - loc;
					break;
					
				case 3:
					tmp = w + h + w + h - loc;
					break;
					
				case 4:
					tmp = w + loc;
					break;		
			}
			
			if(i < n) {
				arr[i] = tmp;
			}else {
				p = tmp;
			}
		}
		
		for(int i = 0; i < n; i++) {
			int p1 = Math.abs(p - arr[i]);
			int p2 = 2 * w + 2 * h - p1;
			sum += Math.min(p1,  p2);
		}
		
		System.out.println(sum);
		
		sc.close();
	}

}
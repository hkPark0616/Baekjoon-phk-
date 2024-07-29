import java.util.*;

public class Main{
	public static void main(String[] args) {        
		Scanner sc = new Scanner(System.in);        
		
		int answer = 0;
		boolean arr[][] = new boolean[101][101];
		
		for(int i = 0; i < 4; i++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			
			for(int j = x1; j < x1 + (x2 - x1); j++) {
				
				for(int k = y1; k < y1 + (y2 - y1); k++) {
					
					if(!arr[j][k]) {
						arr[j][k] = true;
						answer++;
					}
				}
				
			}
		}
		
		System.out.println(answer);
		
	}
	
}
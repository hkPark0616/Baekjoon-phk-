import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int answer = 0;
		int n = sc.nextInt();
		
		boolean[][] arr = new boolean[101][101];

		for(int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			for(int j = x; j < x + 10; j++) {
				
				for(int k = y; k < y + 10; k++) {
					
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
import java.util.*;

public class Main{
	public static int[][] arr;
	public static int bingo;
	public static int answer;
	public static void main(String[] args) throws Exception{        
		Scanner sc = new Scanner(System.in);
		
		arr = new int[5][5];
		bingo = 0;
		answer = 0;
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
	
		for(int k = 0; k < 25; k++) {
			int num = sc.nextInt();
			
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					if(arr[i][j] == num) {
						arr[i][j] = 0;
						//break;
					}
				}
			}
			
			row();
			column();
			dia1();
			dia2();
			
			if(bingo >= 3) {
				answer = k + 1;
				break;
			}
			
			bingo = 0;
		}

		System.out.println(answer);
			
	}
	
	public static void row() {
		// 가로
		for(int i = 0; i < 5; i++) {
			int cnt = 0;
			for(int j = 0; j < 5; j++) {
				if(arr[i][j] == 0)
					cnt++;
			}
			
			if(cnt == 5) bingo++;
		}
	}
	
	public static void column() {
		// 세로
		for(int i = 0; i < 5; i++) {
			int cnt = 0;
			for(int j = 0; j < 5; j++) {
				if(arr[j][i] == 0)
					cnt++;
			}
			
			if(cnt == 5) bingo++;
		}
	}
	
	public static void dia1() {
		// 대각1(왼 위 -> 오 아래)
		int cnt = 0;
		for(int i = 0; i < 5; i++) {
			if(arr[i][i] == 0) cnt++;
		}
		
		if(cnt == 5) bingo++;
	}
	
	public static void dia2() {
		int cnt = 0;
		for(int i = 0; i < 5; i++) {
			if(arr[i][4 - i] == 0) cnt++;
		}
		
		if(cnt == 5) bingo++;
	}

}
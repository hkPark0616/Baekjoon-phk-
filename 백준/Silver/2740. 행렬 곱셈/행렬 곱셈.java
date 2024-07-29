import java.util.*;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[][] arr1 = new int[sc.nextInt()][sc.nextInt()];
		for(int i = 0; i < arr1.length; i++) {
			for(int j = 0; j < arr1[0].length; j++) {
				arr1[i][j] = sc.nextInt();
			}
		}
		
		int[][] arr2 = new int[sc.nextInt()][sc.nextInt()];
		for(int i = 0; i < arr2.length; i++) {
			for(int j = 0; j < arr2[0].length; j++) {
				arr2[i][j] = sc.nextInt();
			}
		}
		
		int answer[][] = new int[arr1.length][arr2[0].length];
		
		for(int i = 0; i < arr1.length; i++) {
			for(int j = 0; j < arr2[0].length; j++) {
				int sum = 0;;
				for(int k = 0; k < arr2.length; k++) {
					sum += arr1[i][k] * arr2[k][j];
				}
				answer[i][j] = sum;
			}
		}
		
		for(int i = 0; i < answer.length; i++) {
			for(int j = 0; j < answer[0].length; j++) {
				System.out.print(answer[i][j] + " ");
			}
			System.out.println();
		}
	}
}
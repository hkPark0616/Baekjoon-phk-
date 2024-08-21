import java.io.*;
import java.util.*;

public class Main {
	static int N, M, R, arr[][];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < R; i++) {
			int num = Integer.parseInt(st.nextToken());
			switch(num) {
				case 1:
					arr = reverseUpDown();
					break;
				case 2:
					arr = reverseLR();
					break;
				case 3:
					arr = right();
					break;
				case 4:
					arr = left();
					break;
				case 5:
					arr = move1();
					break;
				case 6:
					arr = move2();
					break;
			}
		}
			
		for(int i = 0; i < arr.length; i++) {			
			for(int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static int[][] reverseUpDown() {
		int tempArr[][] = new int[arr.length][arr[0].length];
		for(int i = 0; i <arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				tempArr[i][j] = arr[arr.length - i - 1][j];

			}
		}
		
		return tempArr;
	}
	
	static int[][] reverseLR() {
		int tempArr[][] = new int[arr.length][arr[0].length];
		for(int i = 0; i < arr[0].length; i++) {
			for(int j = 0; j < arr.length; j++) {
				tempArr[j][i] = arr[j][arr[0].length - i - 1];
			}
			
		}
		return tempArr;
	}
	
	static int[][] right() {
		int tempArr[][] = new int[arr[0].length][arr.length];
		for(int i = 0; i < arr[0].length; i++) {
			for(int j = 0; j < arr.length; j++) {
				tempArr[i][j] = arr[arr.length - j - 1][i];
			}
		}
		return tempArr;
	}
	
	static int[][] left() {
		int tempArr[][] = new int[arr[0].length][arr.length];
		for(int i = 0; i < arr[0].length; i++) {
			for(int j = 0; j < arr.length; j++) {
				tempArr[i][j] = arr[j][arr[0].length - i - 1];
			}
		}
		return tempArr;
	}
	
	// 1 -> 2, 2 -> 3, 3 -> 4, 4 -> 1
	static int[][] move1() {
		int tempArr[][] = new int[arr.length][arr[0].length];
		int xsize = arr.length / 2; // 3
		int ysize = arr[0].length / 2; // 4
		
		// 1 -> 2
		for(int i = 0;  i < xsize; i++) {
			for(int j = 0; j < ysize; j++) {
				tempArr[i][ysize + j] = arr[i][j];
			}
		}
		// 2 -> 3
		for(int i = 0;  i < xsize; i++) {
			for(int j = ysize; j < ysize * 2; j++) {
				tempArr[xsize + i][j] = arr[i][j];
			}
		}
		// 3 -> 4
		for(int i = xsize;  i < xsize * 2; i++) {
			for(int j = ysize; j < ysize * 2; j++) {
				tempArr[i][j - ysize] = arr[i][j];
			}
		}
		// 4 -> 1
		for(int i = xsize;  i < xsize * 2; i++) {
			for(int j = 0; j < ysize; j++) {
				tempArr[i - xsize][j] = arr[i][j];
			}
		}

		return tempArr;
	}
	
	// 1 -> 4, 4 -> 3, 3 -> 2, 2 -> 1
	static int[][] move2() {
		int tempArr[][] = new int[arr.length][arr[0].length];
		int xsize = arr.length / 2; // 3
		int ysize = arr[0].length / 2; // 4
		
		// 1 -> 4
		for(int i = 0; i < xsize; i++) {
			for(int j = 0; j < ysize; j++) {
				tempArr[i + xsize][j] = arr[i][j];
			}
		}
		// 4 -> 3
		for(int i = xsize; i < xsize * 2; i++) {
			for (int j = 0; j < ysize; j++) {
				tempArr[i][j + ysize] = arr[i][j];
			}
		}
		// 3 -> 2
		for(int i = xsize; i < xsize * 2; i++) {
			for(int j = ysize; j < ysize * 2; j++) {
				tempArr[i - xsize][j] = arr[i][j];
			}
		}
		// 2 -> 1
		for(int i = 0;  i < xsize; i++) {
			for(int j = ysize; j < ysize * 2; j++) {
				tempArr[i][j - ysize] = arr[i][j];
			}
		}
		
		return tempArr;
	}
}

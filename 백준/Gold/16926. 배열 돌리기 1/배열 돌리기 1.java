import java.util.*;

public class Main {
	static int N, M, R, arr[][], min;
	static int delX[] = {0, 1, 0, -1};
	static int delY[] = {1, 0, -1, 0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		R = sc.nextInt();
		
		arr = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		min = Math.min(N,  M);
		for(int i = 0; i < R; i++) {
			// rotate();
			for(int j = 0; j < min / 2; j++) {
				int temp = arr[j][j];
				
				// left(위) - 오른쪽에서 왼쪽으로 땡김
				for(int k = j; k < M - j - 1; k++) {
					arr[j][k] = arr[j][k + 1];
				}
					
				// up(오) - 아래에서 위로 땡김
				for(int k = j; k < N - j - 1; k++) {
					arr[k][M - j - 1] = arr[k + 1][M - j - 1];
				}
				
				// right(아래) - 왼쪽에서 오른쪽으로 땡김
				for(int k = M - j - 1; k > j; k--) {
					arr[N - j - 1][k] = arr[N - j - 1][k - 1];
				}
				
				// down(왼) - 위에서 아래로 땡김
				for(int k = N - j - 1; k > j; k--) {
					arr[k][j] = arr[k - 1][j];
				}
				
				arr[j + 1][j] = temp;
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void rotate() {
		for(int i = 0; i < min / 2; i++) {
			int x = i;
			int y = i;
			int temp = arr[i][i];
			
			int direction = 0;
			while(direction < 4) {
				int dx = x + delX[direction];
				int dy = y + delY[direction];
				
				if(dx >= i && dx < N - i&& dy >= i && dy < M - i) {
					arr[x][y] = arr[dx][dy];
					x = dx;
					y = dy;
				}else {
					direction++;
				}
			}
			
			arr[i + 1][i] = temp;
			
			for(int a = 0; a < N; a++) {
				for(int b = 0; b < M; b++) {
					System.out.print(arr[a][b] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
}

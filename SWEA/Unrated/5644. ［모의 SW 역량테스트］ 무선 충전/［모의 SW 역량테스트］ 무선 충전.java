import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int x1, x2, y1, y2;
	static int M, A;
	static int[] moveA, moveB;
	static int[] Apos, Bpos;
	static int AP[][];
	static int max;
	static int[] delX = {0, 0, 1, 0, -1}, delY = {0, -1, 0, 1, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			moveA = new int[M + 1];
			moveB = new int[M + 1];
			// 사용자 A
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			
			// 사용자 B
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			
			AP = new int[A][4];
			// AP
			for(int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 4; j++) {
					// a, b, 충전범위, 충전량(0 ~ 3)
					AP[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			max = 0;
			
			Apos = new int[2];
			Bpos = new int[2];
			
			Apos[0] = 1;
			Apos[1] = 1;
			Bpos[0] = 10;
			Bpos[1] = 10;
			
			move();

			System.out.println("#" + t + " " + max);
		}
		
	}
	
	static void move(){
		for(int i = 0; i <= M; i++) {
			Apos[0] += delX[moveA[i]];
			Apos[1] += delY[moveA[i]];
			Bpos[0] += delX[moveB[i]];
			Bpos[1] += delY[moveB[i]];
			
			max += charge();
		}
	}
	
	// c 번째 충전기, A 또는 B의 현재 좌표
	static int check(int c, int x, int y) {
		// A 또는 B의 좌표가 충전 범위부터 얼마나 떨어져있는지 확인해서 충전 범위 내에 있으면 충전량 리턴, 아니면 0 리턴
		return Math.abs(AP[c][0] - x) + Math.abs(AP[c][1] - y) <= AP[c][2] ? AP[c][3] : 0;
	}

	static int charge() {
		int totalSum = 0;
		
		for(int i = 0; i < A; i++) {
			
			for(int j = 0; j < A; j++) {
				int sum = 0;
				
				int asum = check(i, Apos[0], Apos[1]);
				int bsum = check(j, Bpos[0], Bpos[1]);
				
				if(i != j) { // 같은 중전 범위가 아니면 그냥 합침
					sum = asum + bsum;
				}else { // 같은 중전 범위라면 둘 중 더 큰 충전량 선택
					sum = Math.max(asum, bsum);
				}

				if(totalSum < sum) {
					totalSum = sum;
				}
			}
		}
		return totalSum;
		
	}

}

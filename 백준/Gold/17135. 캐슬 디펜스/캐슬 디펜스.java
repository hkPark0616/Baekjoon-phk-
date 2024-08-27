import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, D;
	static int map[][];
	static int copy[][];
	static int arr[];
	static int max;
	static int cnt, kill, archer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		copy = new int[N][M];
		arr = new int[3];
		
		max = 0;
		cnt = 0;

		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) cnt++;
			}
		}

		comb(0, 0);
		
		System.out.println(max);
	}
	
	static void comb(int cnt, int start) {
		if(cnt == 3) {

			defence();
			
			return;
		}
		
		for(int i = start; i < M; i++) {
			arr[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	static void defence() {
	    // 맵 복사
	    for(int i = 0; i < N; i++) {
	        for(int j = 0; j < M; j++) {
	            copy[i][j] = map[i][j];
	        }
	    }
	    
	    kill = 0;
	    int now = N; // 현재 궁수의 위치를 나타내는 행
	    
	    while(now > 0) {
	        List<int[]> targets = new ArrayList<>();
	        
	        for(int k = 0; k < 3; k++) {
	            int minDist = Integer.MAX_VALUE;
	            int targetX = -1;
	            int targetY = -1;
	            
	            for(int i = 0; i < now; i++) {
	                for(int j = 0; j < M; j++) {
	                    if(copy[i][j] == 1) {
	                        int dist = Math.abs(now - i) + Math.abs(arr[k] - j);
	                        if(dist <= D) {
	                            if(dist < minDist || (dist == minDist && j < targetY)) {
	                                minDist = dist;
	                                targetX = i;
	                                targetY = j;
	                            }
	                        }
	                    }
	                }
	            }
	            
	            if(targetX != -1 && targetY != -1) {
	                targets.add(new int[]{targetX, targetY});
	            }
	        }
	        
	        // 각 궁수가 선택한 타겟을 죽임
	        for(int[] target : targets) {
	            if(copy[target[0]][target[1]] == 1) {
	                copy[target[0]][target[1]] = 0;
	                kill++;
	            }
	        }
	        
	        now--; // 궁수 위치 한 칸 위로 이동
	    }
	    
	    max = Math.max(kill, max); // 최대 처치 수 갱신
	}

}

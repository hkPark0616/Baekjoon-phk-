import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int result[][];
	static int team1[], team2[];
	static boolean isPossible;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 0; t < 4; t++) {					
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			result = new int[6][3];
			isPossible = false;
			
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 3; j++) {
					result[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int w = 0, d = 0, l = 0;
			for(int i = 0; i < 6; i++) {
				w += result[i][0];
				d += result[i][1];
				l += result[i][2];
			}
			
			team1 = new int[15];
			team2 = new int[15];
			int cnt = 0;
			for(int i = 0; i < 5; i++) {
				for(int j = i + 1; j < 6; j++) {
					team1[cnt] = i;
					team2[cnt++] = j;
				}
			}
			
			if((w + d + l) != 30) {
				isPossible = false;
			}else {
				dfs(0);
			}
			
			if(isPossible) {
				sb.append("1 ");
			}else {
				sb.append("0 ");
			}
		}
		
		System.out.println(sb);
		
	}
	
	static void dfs(int cnt) {
		if(cnt == 15) {
			isPossible = true;
			return;
		}
		
		int a = team1[cnt];
		int b = team2[cnt];
		
		// a가 이김
		if(result[a][0] > 0 && result[b][2] > 0) {
			result[a][0]--;
			result[b][2]--;
			dfs(cnt + 1);
			result[a][0]++;
			result[b][2]++;
		}
		
		// 비김
		if(result[a][1] > 0 && result[b][1] > 0) {
			result[a][1]--;
			result[b][1]--;
			dfs(cnt + 1);
			result[a][1]++;
			result[b][1]++;
		}
		
		// a가 짐
		if(result[a][2] > 0 && result[b][0] > 0) {
			result[a][2]--;
			result[b][0]--;
			dfs(cnt + 1);
			result[a][2]++;
			result[b][0]++;
		}	
	}
}

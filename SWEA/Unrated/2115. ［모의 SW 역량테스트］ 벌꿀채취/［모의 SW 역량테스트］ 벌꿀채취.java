import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, C, beoltong[][], honey[][];
    static boolean isSelected[];
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 크기 N x N
            M = Integer.parseInt(st.nextToken()); // 선택할 수 있는 벌통의 개수
            C = Integer.parseInt(st.nextToken()); // 꿀 채취 최대 양

            beoltong = new int[N][N];
            honey = new int[N][N - M + 1];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    beoltong[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            isSelected = new boolean[M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N - M + 1; j++) {
                    max = 0;
                    comb(i, j, 0, 0, 0);
                    honey[i][j] = max;
                }
            }
            
            int answer = 0;
            for(int i = 0; i < N; i++) {
            	for(int j = 0; j < N - M + 1; j++) {
            		
            		// 같은 행
            		for(int k = j + M; k < N -M + 1; k++) {
            			answer = Math.max(answer, honey[i][j] + honey[i][k]);
            		}
            		
            		// 다른 행
            		for(int k = i + 1; k < N; k++) {
            			for(int l = 0; l < N - M + 1; l++) {
            				answer = Math.max(answer, honey[i][j] + honey[k][l]);            			}
            		}
            	}
            }
            
            System.out.println("#" + t + " " + answer);
        }
    }
    
    // cnt: 벌통 선택 개수, sum: 현재 벌통에서 꿀의 양, honeyProfit: 현재 벌통 꿀 수익
    static void comb(int a, int b, int cnt, int sum, int honeyProfit) {
        if (sum > C) return;

        if (cnt == M) { // 벌통 M개 다 선택했으면 최대값 갱신
            max = Math.max(max, honeyProfit);
            return;
        }
        
        // 벌통 선택
        isSelected[cnt] = true;        
        comb(a, b, cnt + 1, sum + beoltong[a][b + cnt], honeyProfit + (beoltong[a][b + cnt] * beoltong[a][b + cnt]));
        
        // 벌통 선택 안함
        isSelected[cnt] = false;
        comb(a, b, cnt + 1, sum, honeyProfit);
    }
}

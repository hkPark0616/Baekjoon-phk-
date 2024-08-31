import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N, K, answer;
    static int[][] map;
    static boolean[][] visited;
    static List<int[]> top;
    static int[] delX = {-1, 0, 1, 0}, delY = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // N x N
            K = Integer.parseInt(st.nextToken()); // 지형을 깎을 수 있는 높이 1 ~ k
            answer = 0;

            map = new int[N][N];
            visited = new boolean[N][N];

            top = new ArrayList<>();
            int max = 0; // 봉우리
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, map[i][j]);
                }
            }

            for(int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j] == max){
                        visited[i][j] = true;
                        dfs(i, j, 1, 1);
                        visited[i][j] = false;
                    }
                }
            }

            System.out.println("#" + t + " " + answer);
        }
    }

    static void dfs(int a, int b, int cnt, int cut){
        int current = map[a][b];

        for(int d = 0; d < 4; d++){
            int nx = a + delX[d];
            int ny = b + delY[d];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;

            int next = map[nx][ny];
            if(current > next){
                visited[nx][ny] = true;
                dfs(nx, ny, cnt + 1, cut);
                visited[nx][ny] = false;
            }else if(cut == 1){
                    for(int k = 1; k <= K; k++){
                        if(current > (next - k)){
                            map[nx][ny] -= k;
                            dfs(nx, ny, cnt + 1, 0);
                            map[nx][ny] += k;
                        }
                    }
                }
        }

        answer = Math.max(answer, cnt);
    }
}

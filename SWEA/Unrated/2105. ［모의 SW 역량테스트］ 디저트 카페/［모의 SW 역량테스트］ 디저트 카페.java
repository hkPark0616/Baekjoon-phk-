import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, map[][], answer;
    static int startx, starty;
    static boolean visited[][];
    static boolean eatDessert[];
    // 우하 - 좌하 - 좌상 - 우상
    static int[] delX = {1, 1, -1, -1}, delY = {1, -1, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            visited = new boolean[N][N];

            answer = -1;

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
    
            // 양옆이랑 밑 2칸까지는 여유가 있어야 사각형 만들어짐
            for(int i = 0; i < N - 2; i++) {
                for(int j = 1; j < N - 1; j++){
                    visited = new boolean[N][N]; // 방문 체크
                    eatDessert = new boolean[101]; // 먹은 디저트
                    visited[i][j] = true;
                    eatDessert[map[i][j]] = true;
                    startx = i; // 시작점 x
                    starty = j; // 시작점 y
                    dfs(i ,j, 0, 1);
                }
            }

            System.out.println("#" + t + " " + answer);
        }
    }

    static void dfs(int x, int y,  int prevDir, int cnt) {
        for(int d = prevDir; d < 4; d++){
            int nx = x + delX[d];
            int ny = y + delY[d];

            if(nx >= 0 && nx < N && ny >= 0 && ny < N){
                if((nx == startx) && (ny == starty) && cnt > 2){ // 최소 3번 이상 탐색해서 돌아오면 종료 후 max 갱신
                    answer = Math.max(cnt, answer);
                    return;
                }

                if(!visited[nx][ny] && !eatDessert[map[nx][ny]]){ // 방문 안했거나 안먹었으면
                    visited[nx][ny] = true;
                    eatDessert[map[nx][ny]] = true;
                    dfs(nx, ny, d, cnt + 1);
                    visited[nx][ny] = false;
                    eatDessert[map[nx][ny]] = false;
                }
            }
        }
    }
}

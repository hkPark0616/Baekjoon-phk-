import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int x1, y1, x2, y2;
    static int answer, map[][];
    static Queue<int[]> q;
    static boolean[][][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken()) + 100;
            y1 = Integer.parseInt(st.nextToken()) + 100;
            x2 = Integer.parseInt(st.nextToken()) + 100;
            y2 = Integer.parseInt(st.nextToken()) + 100;

            q = new LinkedList<>();
            map = new int[201][201];
            visited = new boolean[201][201][2]; // 0은 세로, 1은 가로
            answer = 0;

            // 첫 이동은 아무 방향으로 이동 가능
            for(int d = 0; d < 4; d++){
                int nx = x1 + deltas[d][0];
                int ny = y1 + deltas[d][1];

                if(nx >= 0 && nx < 201 && ny >= 0 && ny < 201){
                    if(d == 0 || d == 1){
                        visited[x1][y1][0] = true;
                        q.offer(new int[]{nx, ny, 1, 0});
                    }else{
                        visited[x1][y1][1] = true;
                        q.offer(new int[]{nx, ny, 1, 1});
                    }
                }
            }

            answer = bfs();

            System.out.println("#" + t + " " + answer);
        }
    }

    static int bfs() {
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int t = cur[2];
            int direction = cur[3];

            if (x == x2 && y == y2) {
                return t;
            }

            // direction 반대 방향으로 전환해주기 위해서 ^ 1해서 비트 연산
            // 이전 이동 방향이 세로 이동
            if(direction == 0){
                for(int d = 2; d < 4; d++){
                    int nx = x + deltas[d][0];
                    int ny = y + deltas[d][1];

                    if(nx >= 0 && nx < 201 && ny >= 0 && ny < 201 && !visited[nx][ny][direction ^ 1]){
                        visited[nx][ny][direction ^ 1] = true;
                        q.offer(new int[]{nx, ny, t + 1, direction ^ 1});
                    }
                }
            }
            // 이전 이동 방향이 가로 이동
            else if (direction == 1){
                for(int d = 0; d < 2; d++){
                    int nx = x + deltas[d][0];
                    int ny = y + deltas[d][1];

                    if(nx >= 0 && nx < 201 && ny >= 0 && ny < 201 && !visited[nx][ny][direction ^ 1]){
                        visited[nx][ny][direction ^ 1] = true;
                        q.offer(new int[]{nx, ny, t + 1, direction ^ 1});
                    }
                }
            }
        }

        return 0;
    }
}
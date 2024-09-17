import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, map[][], answer;
    static boolean[][][] visited;
    static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2]; // 벽을 부수지 않은 상태와 부순 상태를 저장
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        bfs(0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void bfs(int a, int b) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{a, b, 1, 0}); // x, y, 거리, 벽을 부쉈는지(0: 안부숨, 1: 부숨)
        visited[a][b][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            int wallBroken = cur[3];

            if (x == N - 1 && y == M - 1) {
                answer = dist;
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                // 벽이 없는 곳
                if (map[nx][ny] == 0 && !visited[nx][ny][wallBroken]) {
                    queue.add(new int[]{nx, ny, dist + 1, wallBroken});
                    visited[nx][ny][wallBroken] = true;
                }

                // 벽을 부술 수 있는 경우
                if (map[nx][ny] == 1 && wallBroken == 0 && !visited[nx][ny][1]) {
                    queue.add(new int[]{nx, ny, dist + 1, 1});
                    visited[nx][ny][1] = true;
                }
            }
        }
    }
}
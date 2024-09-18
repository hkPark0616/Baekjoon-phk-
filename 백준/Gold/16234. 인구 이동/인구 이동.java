import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, L, R, country[][], answer;
    static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        country = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                country[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;

        while (true) {
            boolean moved = false;
            boolean[][] visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        int cnt = bfs(i, j, visited);
                        if (cnt > 1) moved = true;  // 인구 이동이 발생했을 때
                    }
                }
            }

            if (moved) {
                answer++;
            } else {
                break;
            }
        }

        System.out.println(answer);
    }

    static int bfs(int a, int b, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        visited[a][b] = true;
        queue.add(new int[]{a, b});

        int sum = country[a][b];
        int cnt = 1;
        LinkedList<int[]> union = new LinkedList<>();
        union.add(new int[]{a, b});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visited[nx][ny]) continue;

                int diff = Math.abs(country[x][y] - country[nx][ny]);

                if (diff >= L && diff <= R) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                    sum += country[nx][ny];
                    cnt++;
                    union.add(new int[]{nx, ny});
                }
            }
        }

        int unionPop = sum / cnt;
        for (int[] pos : union) {
            country[pos[0]][pos[1]] = unionPop;
        }

        return cnt;
    }
}
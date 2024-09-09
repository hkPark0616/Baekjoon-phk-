import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, cheese[][];
    static int[] delX = {-1, 0, 1, 0}, delY = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cheese = new int[N][M];

        // 치즈 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        int lastCheese = 0;

        while (true) {
            int count = bfs(0, 0);
            if (count == 0) break;  // 치즈가 더 이상 없으면 종료
            lastCheese = count;
            time++;
        }

        System.out.println(time);
        System.out.println(lastCheese);
    }

    static int bfs(int a, int b) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        visited[a][b] = true;
        queue.offer(new int[]{a, b});

        int meltedCheese = 0; // 이번에 녹은 치즈의 개수를 셀 변수

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int x = temp[0];
            int y = temp[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + delX[d];
                int ny = y + delY[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;

                if (cheese[nx][ny] == 1) {
                    cheese[nx][ny] = 0; // 치즈 녹이기
                    meltedCheese++;     // 녹은 치즈 개수 증가
                } else {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return meltedCheese; // 녹은 치즈 개수 반환
    }
}

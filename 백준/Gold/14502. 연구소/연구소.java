import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, map[][], answer;
    static int[] delX = {-1, 0, 1, 0}, delY = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        wall(0, 0);

        System.out.println(answer);
    }

    // 벽 세우기 함수 최적화
    static void wall(int cnt, int start) {
        if (cnt == 3) {
            spreadVirus();
            return;
        }

        for (int i = start; i < N * M; i++) {
            int x = i / M;
            int y = i % M;
            if (map[x][y] == 0) {
                map[x][y] = 1;
                wall(cnt + 1, i + 1);
                map[x][y] = 0;
            }
        }
    }

    // 바이러스 퍼뜨리기 함수 최적화
    static void spreadVirus() {
        int[][] copyArr = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();

        // 지도 복사 및 초기 바이러스 위치 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyArr[i][j] = map[i][j];
                if (copyArr[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // BFS를 통한 바이러스 퍼뜨리기
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int x = temp[0];
            int y = temp[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + delX[d];
                int ny = y + delY[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && copyArr[nx][ny] == 0) {
                    copyArr[nx][ny] = 2;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        // 안전 구역 계산
        calcSafeArea(copyArr);
    }

    // 안전 구역 계산 함수
    static void calcSafeArea(int[][] copyArr) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyArr[i][j] == 0) cnt++;
            }
        }

        answer = Math.max(cnt, answer);
    }
}

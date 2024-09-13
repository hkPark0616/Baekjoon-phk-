import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[] dice = {0, 0, 0, 0, 0, 0};  // 주사위의 6면(위, 좌, 하, 우, 전, 후)
    static int N, M, x, y, K;
    static int[][] deltas = {
            {0, 0},
            {0, 1},   // 동쪽
            {0, -1},  // 서쪽
            {-1, 0},  // 북쪽
            {1, 0},   // 남쪽
    };
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int command = Integer.parseInt(st.nextToken());
            switch (command) {
                case 1:  // 동쪽 이동
                    rollEast();
                    break;
                case 2:  // 서쪽 이동
                    rollWest();
                    break;
                case 3:  // 북쪽 이동
                    rollNorth();
                    break;
                case 4:  // 남쪽 이동
                    rollSouth();
                    break;
            }
        }

        System.out.println(sb);
    }

    static void rollEast() {
        int nx = x + deltas[1][0];
        int ny = y + deltas[1][1];

        if (nx < 0 || ny < 0 || nx >= N || ny >= M) return;

        // 주사위의 6면(위, 좌, 하, 우, 전, 후)
        int temp = dice[0];
        dice[0] = dice[3];
        dice[3] = dice[2];
        dice[2] = dice[1];
        dice[1] = temp;

        update(nx, ny);
    }

    static void rollWest() {
        int nx = x + deltas[2][0];
        int ny = y + deltas[2][1];

        if (nx < 0 || ny < 0 || nx >= N || ny >= M) return;

        int temp = dice[0];
        dice[0] = dice[1];
        dice[1] = dice[2];
        dice[2] = dice[3];
        dice[3] = temp;

        update(nx, ny);
    }

    static void rollNorth() {
        int nx = x + deltas[3][0];
        int ny = y + deltas[3][1];

        if (nx < 0 || ny < 0 || nx >= N || ny >= M) return;

        int temp = dice[0];
        dice[0] = dice[4];
        dice[4] = dice[2];
        dice[2] = dice[5];
        dice[5] = temp;

        update(nx, ny);
    }

    static void rollSouth() {
        int nx = x + deltas[4][0];
        int ny = y + deltas[4][1];

        if (nx < 0 || ny < 0 || nx >= N || ny >= M) return;

        int temp = dice[0];
        dice[0] = dice[5];
        dice[5] = dice[2];
        dice[2] = dice[4];
        dice[4] = temp;

        update(nx, ny);
    }

    static void update(int nx, int ny) {
        if (map[nx][ny] == 0) {
            map[nx][ny] = dice[2];
        } else {
            dice[2] = map[nx][ny];
            map[nx][ny] = 0;
        }
        x = nx;
        y = ny;
        sb.append(dice[0]).append("\n");
    }
}
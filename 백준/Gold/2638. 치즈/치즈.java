import java.io.*;
import java.util.*;

public class Main {
    static int N, M, map[][];
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        // 치즈 맵 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            bfsAir(0, 0);  // 외부 공기 탐색

            if (meltCheese()) break;
            time++;
        }

        System.out.println(time); 
    }

    // 외부 공기 탐색
    static void bfsAir(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[N][M];
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1];


            // 치즈가 외부 공기랑 접촉해있으면, 해당 공기 체크
            for (int d = 0; d < 4; d++) {
                int nx = cx + deltas[d][0];
                int ny = cy + deltas[d][1];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && map[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    // 치즈 녹이기
    static boolean meltCheese() {
        boolean hasCheese = false;
        List<int[]> toMelt = new ArrayList<>();  // 녹일 치즈 저장

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {  // 치즈인 경우
                    int airContact = 0; //
                    for (int d = 0; d < 4; d++) {
                        int nx = i + deltas[d][0];
                        int ny = j + deltas[d][1];

                        // 공기와 접촉한 치즈면 확인
                        if (nx >= 0 && ny >= 0 && nx < N && ny < M && visited[nx][ny]) {
                            airContact++;
                        }
                    }

                    // 공기와 2면 이상 접촉한 치즈들을 녹을 치즈에 추가
                    if (airContact >= 2) {
                        toMelt.add(new int[]{i, j});
                    }
                    hasCheese = true;
                }
            }
        }

        // 녹을 치즈를 모두 녹임
        for (int[] pos : toMelt) {
            map[pos[0]][pos[1]] = 0;
        }

        return !hasCheese;  // 치즈가 다 녹았는지 여부 반환
    }
}
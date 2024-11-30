import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, R, C, L, answer;
    static int[][] map;
    static boolean[][] visited;
    static int[] delX = {-1, 1, 0, 0};
    static int[] delY = {0, 0, -1, 1};
    static int[][] pipes = {
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {1, 1, 0, 0},
            {0, 0, 1, 1},
            {1, 0, 0, 1},
            {0, 1, 0, 1},
            {0, 1, 1, 0},
            {1, 0, 1, 0},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            visited = new boolean[N][M];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < M; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = 1;
            bfs();

            System.out.println("#" + t + " " + answer);

        }
    }

    static void bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();

        q.offer(new int[] {R, C});  // (R, C) 순서로 넣기
        visited[R][C] = true;

        int time = 1; // 경과 시간

        for(int t = 1; t < L; t++) {
            int size = q.size();

            for(int i = 0; i < size; i++) {
                int[] temp = q.poll();
                int r = temp[0];
                int c = temp[1];

                int[] pipe = pipes[map[r][c]];

                for (int d = 0; d < 4; d++) {
                    if (pipe[d] == 0) continue;

                    int nr = r + delX[d];
                    int nc = c + delY[d];

                    int dir = d % 2 == 0 ? d + 1 : d - 1;

                    if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;

                    if (visited[nr][nc] || map[nr][nc] == 0) continue;

                    if (pipes[map[nr][nc]][dir] == 1) {
                        q.offer(new int[] {nr, nc});
                        visited[nr][nc] = true;
                        answer++;
                    }
                }
            }
        }
    }
}

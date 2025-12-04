import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, answer;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas1 = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 위, 오, 아, 왼
    static int[][] deltas2 = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}}; // 1 기준 왼쪽

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        answer = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtrack(0, 0, 0);
        
        System.out.println(answer);
    }

    static void backtrack(int x, int y, int sum) {
        if(y == M) {
            backtrack(x + 1, 0, sum);
            return;
        }

        if(x == N) {
            answer = Math.max(answer, sum);
            return;
        }

        // 부메랑 선택
        if(!visited[x][y]) {
            for(int d = 0; d < 4; d++) {
                int nx1 = x + deltas1[d][0], ny1 = y + deltas1[d][1];
                int nx2 = x + deltas2[d][0], ny2 = y + deltas2[d][1];

                if(check(nx1, ny1) && check(nx2, ny2) && !visited[nx1][ny1] && !visited[nx2][ny2]) {
                    visited[x][y] = true;
                    visited[nx1][ny1] = true;
                    visited[nx2][ny2] = true;

                    int s = map[x][y] * 2 + map[nx1][ny1] + map[nx2][ny2];

                    backtrack(x, y + 1, sum + s);

                    visited[x][y] = false;
                    visited[nx1][ny1] = false;
                    visited[nx2][ny2] = false;
                }
            }
        }

        // 부메랑 선택 X(나무 재료의 특정 위치는 아예 사용하지 않아도 괜찮다)
        backtrack(x, y + 1, sum);
    }

    static boolean check(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
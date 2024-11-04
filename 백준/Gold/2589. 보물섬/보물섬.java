import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static char[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        answer = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 'L') {
                    bfs(i, j);
                }
            }
        }

        System.out.println(answer);
    }

    static void bfs(int a, int b){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M]; // 방문 배열
        int[][] distance = new int[N][M]; // 경로 추적 배열

        q.add(new int[]{a, b});
        visited[a][b] = true;
        int cnt = 0; // 두 지점 사이의 거리

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for(int d = 0; d < deltas.length; d++) {
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] &&  map[nx][ny] == 'L') {
                    visited[nx][ny] = true;
                    distance[nx][ny] = distance[x][y] + 1; // 거리 증가
                    cnt = Math.max(cnt, distance[nx][ny]); // 거리 갱신
                    q.add(new int[]{nx, ny});
                }
            }
        }
        answer = Math.max(cnt, answer);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, answer, maxHeight;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visited;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        answer = 1;
        maxHeight = 0;
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        for(int h = 1; h <= maxHeight; h++) {
            visited = new boolean[N][N];
            int safeArea = 0;

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(!visited[i][j] && map[i][j] >= h) {
                        bfs(i, j, h);
                        safeArea++;
                    }
                }
            }

            answer = Math.max(answer, safeArea);
        }

        System.out.println(answer);
    }

    static void bfs(int a, int b, int h){
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{a, b});
        visited[a][b] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for(int d = 0; d < 4; d++){
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] >= h){
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
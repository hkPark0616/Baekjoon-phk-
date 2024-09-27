import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, map[][], answer, island[][];
    static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        island = new int[N][N];
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;
        int islandNum = 1;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    findIsland(i, j, islandNum);
                    islandNum++;
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 1) {
                    visited = new boolean[N][N];
                    bfs(i, j, island[i][j]);
                }
            }
        }

        System.out.println(answer);
    }

    static void findIsland(int a, int b, int islandNum) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{a, b, islandNum});
        visited[a][b] = true;
        island[a][b] = islandNum;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int n = cur[2];

            for(int d = 0; d < deltas.length; d++) {
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if(visited[nx][ny]) continue;

                if(map[nx][ny] == 1){
                    visited[nx][ny] = true;
                    island[nx][ny] = islandNum;
                    q.add(new int[]{nx, ny, n});
                }
            }
        }
    }

    static void bfs(int a, int b, int islandNum) {
        Queue<int[]> queue = new LinkedList<>();
        visited[a][b] = true;
        queue.add(new int[]{a, b, 0});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int c = cur[2];

            if(map[x][y] == 1 && island[x][y] != islandNum){
                answer = Math.min(answer, c);
                return;
            }

            for(int d = 0; d < deltas.length; d++){
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                if(map[nx][ny] == 0){
                    queue.add(new int[]{nx, ny, c + 1});
                } // 다른 섬에 도달한 경우
                else{
                    queue.add(new int[]{nx, ny, c});
                }
            }
        }
    }
}

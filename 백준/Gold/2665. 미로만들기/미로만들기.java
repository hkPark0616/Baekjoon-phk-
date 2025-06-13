import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[][] matrixs;
    static int[][] dist;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        matrixs = new int[N][N];
        dist = new int[N][N];

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < N; j++) {
                matrixs[i][j] = line.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();

        System.out.println(dist[N - 1][N - 1]);

    }

    static void bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        dist[0][0] = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for(int[] delta: deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];

                if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    int nextCost = matrixs[nx][ny] == 0 ? 1 : 0;

                    if(dist[nx][ny] > dist[x][y] + nextCost) {
                        dist[nx][ny] = dist[x][y] + nextCost;
                        if (nextCost == 0) {
                            q.offerFirst(new int[]{nx, ny});
                        } else {
                            q.offerLast(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
    }
}
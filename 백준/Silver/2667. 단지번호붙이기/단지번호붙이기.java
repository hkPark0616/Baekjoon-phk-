import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < N; j++) {
                int n = str.charAt(j) - '0';
                map[i][j] = n;
            }
        }

        List<Integer> result = new ArrayList<>();
        int houses = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    result.add(bfs(i, j));
                }
            }
        }
        
        Collections.sort(result);
        System.out.println(result.size());
        for (int count : result) {
            System.out.println(count);
        }
    }

    static int bfs(int a, int b) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {a, b});
        visited[a][b] = true;
        int cnt = 1;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for(int[] delta: deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, M, startX, startY;
    static char[][] map;
    static boolean[][] visited;
    static ArrayDeque<int[]> fire;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[M][N];
            visited = new boolean[M][N];
            fire = new ArrayDeque<>();
            for(int i = 0; i < M; i++){
                String line = br.readLine();
                for(int j = 0; j < N; j++){
                    char c = line.charAt(j);
                    map[i][j] = c;
                    if(c == '@'){
                        startX = i;
                        startY = j;
                    }

                    if(c == '*'){
                        fire.offer(new int[]{i, j});
                    }
                }
            }

            int answer = bfs(startX, startY);

            System.out.println(answer == -1 ? "IMPOSSIBLE" : answer);
        }
    }

    static void fire(){
        int size = fire.size();
        for(int i = 0; i < size; i++){
            int[] cur = fire.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if (nx >= 0 && ny >= 0 && nx < M && ny < N && map[nx][ny] == '.') {
                    map[nx][ny] = '*';
                    fire.offer(new int[]{nx, ny});
                }
            }
        }
    }

    static int bfs(int a, int b){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[a][b] = true;
        q.offer(new int[] {a, b, 0});

        while(!q.isEmpty()){

            fire();

            int size = q.size();
            for(int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                int c = cur[2];

                if (x == 0 || x == M - 1 || y == 0 || y == N - 1) {
                    return c + 1;
                }
                for (int d = 0; d < 4; d++) {
                    int nx = x + deltas[d][0];
                    int ny = y + deltas[d][1];

                    if (nx >= 0 && ny >= 0 && nx < M && ny < N && !visited[nx][ny] && map[nx][ny] == '.') {
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny, c + 1});
                    }
                }
            }
        }

        return -1;
    }
}

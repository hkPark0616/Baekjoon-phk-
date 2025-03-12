import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int R, C, sx, sy, ex, ey, answer;
    static char[][] map;
    static ArrayDeque<int[]> floods;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];
        floods = new ArrayDeque<>();

        for (int i = 0; i < R; i++){
            String line = br.readLine();
            for (int j = 0; j < C; j++){
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S'){
                    sx = i;
                    sy = j;
                }
                if (map[i][j] == 'D'){
                    ex = i;
                    ey = j;
                }
                if (map[i][j] == '*'){
                    floods.offer(new int[]{i, j});
                }
            }
        }

        answer = bfs(sx, sy);

        System.out.println(answer == -1 ? "KAKTUS" : answer);
    }

    static void flood(){
        int size = floods.size();
        for (int i = 0; i < size; i++){
            int[] cur = floods.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++){
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if (nx >= 0 && ny >= 0 && nx < R && ny < C && map[nx][ny] == '.'){
                    map[nx][ny] = '*';
                    floods.offer(new int[]{nx, ny});
                }
            }
        }
    }

    static int bfs(int a, int b){
        ArrayDeque<int[]> que = new ArrayDeque<>();
        que.offer(new int[]{a, b, 0});
        visited[a][b] = true;

        while (!que.isEmpty()){

            flood();

            int size = que.size();
            for (int i = 0; i < size; i++){
                int[] cur = que.poll();
                int x = cur[0];
                int y = cur[1];
                int c = cur[2];

                for (int d = 0; d < 4; d++){
                    int nx = x + deltas[d][0];
                    int ny = y + deltas[d][1];

                    if (nx >= 0 && ny >= 0 && nx < R && ny < C && !visited[nx][ny]){

                        if (map[nx][ny] == 'D') {
                            return c + 1;
                        }
                        if (map[nx][ny] == '.'){
                            visited[nx][ny] = true;
                            que.offer(new int[]{nx, ny, c + 1});
                        }
                    }
                }
            }
        }

        return -1;
    }
}
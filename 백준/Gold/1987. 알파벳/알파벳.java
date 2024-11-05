import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, max;
    static char[][] board;
    static boolean[] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader((new InputStreamReader(System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        max = 1;
        board = new char[R][C];
        visited = new boolean[100];

        for(int i = 0; i < R; i++){
            String line = br.readLine();
            for(int j = 0; j < C; j++){
                board[i][j] = line.charAt(j);
            }
        }

        dfs(0, 0, 0);

        System.out.println(max);
    }

    static void dfs(int x, int y, int cnt){
        if(visited[board[x][y]]){
            max = Math.max(max, cnt);
            return;
        }

        visited[board[x][y]] = true;

        for(int d = 0; d < deltas.length; d++){
            int nx = x + deltas[d][0];
            int ny = y + deltas[d][1];

            if(nx >= 0 && ny >= 0 && nx < R && ny < C){
                dfs(nx, ny, cnt + 1);
            }
        }
        visited[board[x][y]] = false;
    }
}
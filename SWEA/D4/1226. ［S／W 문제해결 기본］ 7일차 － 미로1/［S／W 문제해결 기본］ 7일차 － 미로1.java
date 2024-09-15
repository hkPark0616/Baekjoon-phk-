import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = { {-1, 0},{0, 1}, {1, 0}, {0, -1} };
    static int startX, startY, endX, endY;
    static boolean isPossible;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int t = 1; t <= 10; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            map = new int[16][16];
            visited = new boolean[16][16];
            startX = 0;
            startY = 0;
            endX = 0;
            endY = 0;
            isPossible = false;
            for(int i = 0; i < 16; i++){
                String line = br.readLine();
                for(int j = 0; j < 16; j++){
                    map[i][j] = line.charAt(j) - '0';
                    if(map[i][j] == 2){
                        startX = i;
                        startY = j;
                    }
                    else if(map[i][j] == 3){
                        endX = i;
                        endY = j;
                    }
                }
            }

            bfs(startX, startY, endX, endY);

            answer =  isPossible ? 1 : 0;
            
            System.out.println("#" + T + " " + answer);
        }
    }

    static void bfs(int a, int b, int ex, int ey){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {a, b});
        visited[a][b] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            if(x == ex && y == ey ){
                isPossible = true;
                return;
            }

            for(int d = 0; d < 4; d++){
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if(nx >= 0 && nx < 16 && ny >= 0 && ny < 16 && !visited[nx][ny] && map[nx][ny] != 1){
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }
}
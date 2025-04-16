import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int L, R, C;
    static int startL, startR, startC;
    static int endL, endR, endC;
    static char[][][] map;
    static boolean[][][] visited;
    static int[][] deltas = {{0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}, {1, 0, 0}, {-1, 0, 0}};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(L == 0 && R == 0 && C == 0) break;

            map = new char[L][R][C];
            visited = new boolean[L][R][C];
            for(int l = 0; l < L; l++){
                for(int i = 0; i < R; i++){
                    String line = br.readLine();
                    for(int j = 0; j < C; j++){
                        char c = line.charAt(j);
                        map[l][i][j] = c;
                        if(c == 'S'){
                            startL = l;
                            startR = i;
                            startC = j;
                        } else if(c == 'E') {
                            endL = l;
                            endR = i;
                            endC = j;
                        }
                    }
                }
                br.readLine();
            }

            int escapedTime = bfs();

            if(escapedTime == -1){
                sb.append("Trapped!").append("\n");
            } else {
                sb.append("Escaped in ").append(escapedTime).append(" minute(s).\n");
            }
        }

        System.out.println(sb.toString());
    }

    static int bfs(){
        ArrayDeque<int[]> que = new ArrayDeque<>();
        visited[startL][startR][startC] = true;
        que.offer(new int[]{startL, startR, startC, 0});

        while(!que.isEmpty()){
            int[] cur = que.poll();
            int l = cur[0];
            int r = cur[1];
            int c = cur[2];
            int m = cur[3];

            if(map[l][r][c] == 'E'){
                return m;
            }

            for(int[] d : deltas){
                int nl = l + d[0];
                int nr = r + d[1];
                int nc = c + d[2];

                if(nr >= 0 && nr < R && nc >= 0 && nc < C && nl >= 0 && nl < L
                    && !visited[nl][nr][nc]
                    && (map[nl][nr][nc] == '.' || map[nl][nr][nc] == 'E')){
                    visited[nl][nr][nc] = true;
                    que.offer(new int[]{nl, nr, nc, m + 1});
                }
            }
        }
        
        return -1;
    }
}
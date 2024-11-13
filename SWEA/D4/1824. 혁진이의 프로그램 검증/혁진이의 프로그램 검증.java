import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Solution {
    static int R, C;
    static char[][] map;
    static boolean[][][][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static class State{
        int x, y, m, dir;

        public State(int x, int y, int m, int dir) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new char[R][C];
            visited = new boolean[R][C][16][4];

            for(int r = 0; r < R; r++){
                String s = br.readLine();
                for(int c = 0; c < C; c++){
                    char ch = s.charAt(c);
                    map[r][c] = ch;
                }
            }

            System.out.println("#" + t + " " + (bfs() ? "YES" : "NO"));
        }
    }

    static boolean bfs() {
        ArrayDeque<State> queue = new ArrayDeque<>();
        queue.offer(new State(0, 0, 0, 3));
        visited[0][0][0][3] = true;

        while(!queue.isEmpty()){
            State cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int m = cur.m;
            int dir = cur.dir;

            char cmd = map[x][y];

            switch(cmd){
                case '<': dir = 2; break;
                case '>': dir = 3; break;
                case '^': dir = 0; break;
                case 'v': dir = 1; break;
                case '_': dir = (m == 0) ? 3 : 2; break;
                case '|': dir = (m == 0) ? 1 : 0; break;
                case '?':
                    for(int d = 0; d < 4; d++){
                        int nx = (x + deltas[d][0] + R) % R;
                        int ny = (y + deltas[d][1] + C) % C;

                        if(!visited[nx][ny][m][d]){
                            visited[nx][ny][m][d] = true;
                            queue.offer(new State(nx, ny, m, d));
                        }
                    }
                    break;
                case '.': break;
                case '@': return true;
                case '+': m = (m + 1) % 16; break;
                case '-': m = (m == 0) ? 15 : m - 1; break;
                default:
                    if(Character.isDigit(cmd)) m = cmd - '0'; break;
            }

            int nx = (x + deltas[dir][0] + R) % R;
            int ny = (y + deltas[dir][1] + C) % C;

            if(!visited[nx][ny][m][dir]){
                visited[nx][ny][m][dir] = true;
                queue.offer(new State(nx, ny, m, dir));
            }
        }

        return false;
    }
}
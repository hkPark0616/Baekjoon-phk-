import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int R, C;
    static char[][] map;
    static boolean[][][][] visited;
    static int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dc = {0, 0, -1, 1};

    static class State {
        int r, c, mem, dir;
        public State(int r, int c, int mem, int dir) {
            this.r = r;
            this.c = c;
            this.mem = mem;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new char[R][C];
            visited = new boolean[R][C][16][4];

            for (int r = 0; r < R; r++) {
                String line = br.readLine();
                for (int c = 0; c < C; c++) {
                    map[r][c] = line.charAt(c);
                }
            }

            System.out.println("#" + t + " " + (bfs() ? "YES" : "NO"));
        }
    }

    static boolean bfs() {
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0, 0, 0, 3)); // 시작 위치 (0,0), 초기 메모리 0, 오른쪽 방향(3)
        visited[0][0][0][3] = true;

        while (!queue.isEmpty()) {
            State state = queue.poll();
            int r = state.r;
            int c = state.c;
            int mem = state.mem;
            int dir = state.dir;

            char cmd = map[r][c];
            switch (cmd) {
                case '>': dir = 3; break;
                case '<': dir = 2; break;
                case '^': dir = 0; break;
                case 'v': dir = 1; break;
                case '_': dir = (mem == 0) ? 3 : 2; break;
                case '|': dir = (mem == 0) ? 1 : 0; break;
                case '+': mem = (mem + 1) % 16; break;
                case '-': mem = (mem == 0) ? 15 : mem - 1; break;
                case '@': return true; // 프로그램 종료
                case '?':
                    // ? 명령어는 네 방향으로 각각 탐색
                    for (int i = 0; i < 4; i++) {
                        int nr = (r + dr[i] + R) % R;
                        int nc = (c + dc[i] + C) % C;
                        if (!visited[nr][nc][mem][i]) {
                            visited[nr][nc][mem][i] = true;
                            queue.add(new State(nr, nc, mem, i));
                        }
                    }
                    continue;
                default:
                    if (Character.isDigit(cmd)) mem = cmd - '0';
            }

            // 다음 위치 계산 후 큐에 추가
            int nr = (r + dr[dir] + R) % R;
            int nc = (c + dc[dir] + C) % C;

            if (!visited[nr][nc][mem][dir]) {
                visited[nr][nc][mem][dir] = true;
                queue.add(new State(nr, nc, mem, dir));
            }
        }

        return false; // 프로그램 종료 문자를 만나지 않으면 NO
    }
}
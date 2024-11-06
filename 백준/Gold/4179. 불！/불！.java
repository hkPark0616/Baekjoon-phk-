import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C, startX, startY;
    static char[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<int[]> jihunQueue = new LinkedList<>();
    static Queue<int[]> fireQueue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = line.charAt(j);
                map[i][j] = c;
                if (c == 'J') {
                    startX = i;
                    startY = j;
                    jihunQueue.add(new int[]{startX, startY, 1}); // 지훈 위치
                } else if (c == 'F') {
                    fireQueue.add(new int[]{i, j}); // 불 위치
                }
            }
        }

        int answer = bfs();
        System.out.println(answer == -1 ? "IMPOSSIBLE" : answer);
    }

    static int bfs() {
        boolean[][] visitedJihun = new boolean[R][C];
        boolean[][] visitedFire = new boolean[R][C];
        visitedJihun[startX][startY] = true;

        for (int[] fire : fireQueue) {
            visitedFire[fire[0]][fire[1]] = true;
        }

        while (!jihunQueue.isEmpty()) {
            int fireSize = fireQueue.size();

            for (int i = 0; i < fireSize; i++) {
                int[] fire = fireQueue.poll();
                int fx = fire[0], fy = fire[1];

                for (int d = 0; d < 4; d++) {
                    int nfx = fx + deltas[d][0];
                    int nfy = fy + deltas[d][1];
                    if (nfx >= 0 && nfx < R && nfy >= 0 && nfy < C && !visitedFire[nfx][nfy] && map[nfx][nfy] == '.') {
                        visitedFire[nfx][nfy] = true;
                        map[nfx][nfy] = 'F';
                        fireQueue.add(new int[]{nfx, nfy});
                    }
                }
            }

            int jihunSize = jihunQueue.size();

            for (int i = 0; i < jihunSize; i++) {
                int[] jihun = jihunQueue.poll();
                int jx = jihun[0], jy = jihun[1], t = jihun[2];

                if (jx == 0 || jx == R - 1 || jy == 0 || jy == C - 1) {
                    return t;
                }

                for (int d = 0; d < 4; d++) {
                    int njx = jx + deltas[d][0];
                    int njy = jy + deltas[d][1];
                    if (njx >= 0 && njx < R && njy >= 0 && njy < C) {
                        if (!visitedJihun[njx][njy] && map[njx][njy] == '.') {
                            visitedJihun[njx][njy] = true;
                            jihunQueue.add(new int[]{njx, njy, t + 1});
                        }
                    }
                }
            }
        }
        return -1;
    }
}

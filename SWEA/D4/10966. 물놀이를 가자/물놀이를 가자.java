import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static char[][] map;
    static int[][] distance;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N][M];
            distance = new int[N][M];

            Queue<int[]> q = new LinkedList<>();

            for(int i = 0; i < N; i++) {
                String line = br.readLine();
                for(int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);
                    if(map[i][j] == 'W') {
                        q.add(new int[]{i, j});
                        distance[i][j] = 0; // 물 0
                    } else {
                        distance[i][j] = -1; // 육지는 -1
                    }
                }
            }

            // 물에서 시작
            while(!q.isEmpty()) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];

                for(int[] delta : deltas) {
                    int nx = x + delta[0];
                    int ny = y + delta[1];

                    // 아직 도착하지 않은 땅에 대해서 수행함
                    // 따로 distance 배열에 거리를 저장함
                    if(nx >= 0 && ny >= 0 && nx < N && ny < M && distance[nx][ny] == -1) {
                        distance[nx][ny] = distance[x][y] + 1;
                        q.add(new int[]{nx, ny});
                    }
                }
            }

            int answer = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] == 'L') {
                        answer += distance[i][j];
                    }
                }
            }
            
            System.out.println("#" + t + " " + answer);
        }
    }
}
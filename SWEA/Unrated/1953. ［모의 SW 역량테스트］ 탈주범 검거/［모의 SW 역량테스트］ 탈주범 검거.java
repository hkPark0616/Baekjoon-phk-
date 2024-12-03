import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, R, C, L, answer;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] pipes = {
                                {0, 0, 0, 0},
                                {1, 1, 1, 1},
                                {1, 1, 0, 0},
                                {0, 0, 1, 1},
                                {1, 0, 0, 1},
                                {0, 1, 0, 1},
                                {0, 1, 1, 0},
                                {1, 0, 1, 0}
                            };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            visited = new boolean[N][M];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < M; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 맨홀뚜겅이 위치한 지점을 포함
            answer = 1;

            bfs();

            System.out.println("#" + t + " " + answer);
        }
    }

    static void bfs(){
        ArrayDeque<int[]> q = new ArrayDeque<>();

        // 시작점(맨홀 위치)
        visited[R][C] = true;
        q.offer(new int[] {R, C});
        
        // 주어진 시간만큼 반복
        for(int i = 1; i < L; i++){
            // 시간 단위로 탐색하기 위해서
            int size = q.size();
            for(int s = 0; s < size; s++) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];

                int[] pipe = pipes[map[x][y]];

                for(int d = 0; d < 4; d++){
                    // 해당 파이프 방향으로 이동할 수 없으면 넘김
                    if(pipe[d] == 0) continue;
                    
                    int nx = x + deltas[d][0];
                    int ny = y + deltas[d][1];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                    if(visited[nx][ny] || map[nx][ny] == 0) continue;

                    // 현재의 파이프가 다음 파이프(반대 방향으로)랑 연결되는지
                    int dir = d % 2 == 0 ? d + 1 : d - 1;
                    if(pipes[map[nx][ny]][dir] == 1){
                        q.offer(new int[] {nx, ny});
                        visited[nx][ny] = true;
                        answer++;
                    }

                }
            }
        }
    }
}
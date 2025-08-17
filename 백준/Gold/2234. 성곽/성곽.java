import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, roomCnt, max, max2;
    static int[][] map, room;
    static int[] roomSize;
    static int[][] deltas = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}}; // 0 서쪽 , 1 북쪽, 2 동쪽, 3 남쪽

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        room = new int[M][N];
        roomSize = new int[M * N + 1];
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        roomCnt = 0;
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(room[i][j] == 0) {
                    roomCnt++;
                    int size = bfs(i, j, roomCnt);
                    roomSize[roomCnt] = size;
                    max = Math.max(max, size);
                }
            }
        }

        for(int x = 0; x < M; x++) {
            for(int y = 0; y < N; y++) {
                for(int d = 0; d < 4; d++) {
                    int nx = x + deltas[d][0];
                    int ny = y + deltas[d][1];
    
                    if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                    int roomA = room[x][y];
                    int roomB = room[nx][ny];

                    if(roomA != roomB) {
                        int roomSum = roomSize[roomA] + roomSize[roomB];
                        max2 = Math.max(max2, roomSum);
                    }
                }
            }
        }

        sb.append(roomCnt).append("\n");
        sb.append(max).append("\n");
        sb.append(max2).append("\n");

        System.out.println(sb);
    }

    static int bfs(int a, int b, int roomNum) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {a, b});
        room[a][b] = roomNum;
        int size = 1;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for(int d = 0; d < 4; d++) {
                if(((map[x][y] >> d) & 1) == 0) {
                    int nx = x + deltas[d][0];
                    int ny = y + deltas[d][1];
    
                    if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                    if(room[nx][ny] != 0) continue;
    
                    room[nx][ny] = roomNum;
                    size++;
                    q.offer(new int[] {nx, ny});
                }
            }
        }

        return size;
    }
}
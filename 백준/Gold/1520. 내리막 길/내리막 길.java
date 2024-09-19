import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M, N, map[][], answer;
    static int[][] visited;
    static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int h;

        Node(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }

        @Override
        public int compareTo(Node o) {
            return o.h - h;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        visited = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(0, 0);
        answer = visited[M - 1][N - 1];

        System.out.println(answer);
    }

    static void bfs(int startX, int startY){
        // 높이가 높은 곳 부터 처리해나가기 위해 우선순위 큐 사용
        Queue<Node> q = new PriorityQueue<>();
        q.offer(new Node(startX, startY, map[startX][startY]));
        visited[startX][startY] = 1;

        while(!q.isEmpty()){
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int h = cur.h;

            if(x == M - 1 && y == N - 1) continue;

            for(int d = 0; d < 4; d++){
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

                if(h <= map[nx][ny]) continue;

                if(visited[nx][ny] == 0){
                    q.add(new Node(nx, ny, map[nx][ny]));
                }

                visited[nx][ny] += visited[x][y];
            }
        }
    }
}
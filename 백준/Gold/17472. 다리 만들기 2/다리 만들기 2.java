import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, parent[], map[][];
    static boolean visited[][];
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static PriorityQueue<Edge> pq;
    static int island;
    static int bridgeCnt;
    static int answer;
    static class Edge implements  Comparable<Edge>{
        int a, b, w;

        public Edge(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 구분
        island = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    island++;
                    bfs(i, j);
                }
            }
        }

        // 다리 만들기
        pq = new PriorityQueue<Edge>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != 0) {
                    findBridge(i, j, map[i][j]);
                }
            }
        }

        parent = new int[island + 1];
        for(int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        int size = pq.size();
        bridgeCnt = 0;
        answer = 0;
        for(int i = 0; i < size; i++) {
            Edge e = pq.poll();

            int a = find(e.a);
            int b = find(e.b);

            if(a != b){
                union(a, b);
                answer += e.w;
                bridgeCnt++;
            }
        }

        if(answer == 0 || bridgeCnt != island - 1){
            System.out.println("-1");
        }else{
            System.out.println(answer);
        }
    }

    static void union(int a, int b){
        a = parent[a];
        b = parent[b];

        if(a != b){
            parent[b] = a;
        }
    }

    static int find(int a){
        if(a == parent[a]){
            return parent[a];
        }

        return parent[a] = find(parent[a]);
    }

    static void bfs(int a, int b){
        Queue<int[]> q = new LinkedList<>();
        visited[a][b] = true;
        map[a][b] = island;
        q.offer(new int[]{a, b});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for(int d = 0; d < 4; d++){
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    map[nx][ny] = island;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }

    static void findBridge(int a, int b, int num){
        int x = a;
        int y = b;
        int length = 0;

        for(int i = 0; i < 4; i++){
            while (true){
                x = x + deltas[i][0];
                y = y + deltas[i][1];

                if(x >= 0 && x < N && y >= 0 && y < M) {
                    if (map[x][y] == num) {
                        length = 0;
                        x = a;
                        y = b;
                        break;
                    } else if (map[x][y] == 0) {
                        length++;
                    } else {
                        if (length > 1) {
                            pq.offer(new Edge(num, map[x][y], length));
                        }

                        length = 0;
                        x = a;
                        y = b;
                        break;
                    }
                }else{
                    length = 0;
                    x = a;
                    y = b;
                    break;
                }
            }
        }
    }
}

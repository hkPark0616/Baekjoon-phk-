import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int island, bridgeCount, answer;
    static int[][] map;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[] parent;
    static PriorityQueue<Edge> pq;
    static class Edge implements Comparable<Edge>{
        int x, y, w;

        public Edge(int x, int y, int w){
            this.x = x;
            this.y = y;
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

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 구분짓기
        island = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    island++;
                    bfs(i, j);
                }
            }
        }
        
        // 다리 만들기
        pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] != 0){
                    makeBridge(i, j, map[i][j]);
                }
            }
        }
        
        // 크루스칼
        parent = new int[island + 1]; // 섬의 개수(정점)
        for(int i = 0; i < parent.length; i++){
            parent[i] = i;
        }
        
        int size = pq.size();
        bridgeCount = 0;
        answer = 0;
        for(int i = 0; i < size; i++){
            Edge e = pq.poll();

            int a = e.x;
            int b = e.y;
            int w = e.w;

            if(!isSame(a, b)){
                union(a, b);
                answer += w;
                bridgeCount++;
            }
        }

        // 섬이 모두 이어지지 않는다면
        if(answer == 0 || bridgeCount != island - 1){
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

    static boolean isSame(int a, int b){
        return find(a) == find(b);
    }

    static void bfs(int a, int b){
        ArrayDeque<int[]> q = new ArrayDeque<>();

        visited[a][b] = true;
        map[a][b] = island;
        q.offer(new int[] {a, b});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for(int d = 0; d < 4; d++){
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && map[nx][ny] == 1){
                    visited[nx][ny] = true;
                    map[nx][ny] = island;
                    q.offer(new int[] {nx, ny});
                }
            }
        }
    }
    
    static void makeBridge(int a, int b, int num){
        for(int d = 0; d < 4; d++){
            int x = a;
            int y = b;
            int length = 0; // 다리길이

            while(true){
                x = x + deltas[d][0];
                y = y + deltas[d][1];
                
                if(x < 0 || y < 0 || x >= N || y >= M) break; // 범위
                if(map[x][y] == num) break; // 같은 섬은 안됨
                if(map[x][y] > 0){ // 다른 섬일때
                    if(length > 1) { // 다리 길이가 1 이상
                        pq.offer(new Edge(num, map[x][y], length)); // 이전 섬부터 도착한 섬까지 다리 길이(비용)
                    }
                    break;
                }

                // 바다인 경우 계속 다리를 이어나감
                length++;
            }
        }
    }
}
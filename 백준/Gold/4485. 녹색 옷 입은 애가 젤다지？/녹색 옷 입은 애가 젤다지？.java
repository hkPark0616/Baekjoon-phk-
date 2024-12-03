import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int INF = Integer.MAX_VALUE;
    static int N;
    static int[][] map, dist;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static StringBuilder sb = new StringBuilder();
    static class Node implements Comparable<Node>{
        int x, y, w;

        public Node(int x, int y, int w){
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = 1;
        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            if(N == 0) break;

            map = new int[N][N];
            dist = new int[N][N];

            for(int[] d: dist) {
                Arrays.fill(d, INF);
            }

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dist[0][0] = map[0][0];

            Dijkstra();

            sb.append("Problem ").append(t++).append(": ").append(dist[N - 1][N - 1] + "\n");
        }

        System.out.println(sb.toString());
    }

    static void Dijkstra(){
        PriorityQueue<Node> q = new PriorityQueue<>();

        q.offer(new Node(0, 0, map[0][0]));

        while(!q.isEmpty()){
            Node node = q.poll();
            int x = node.x;
            int y = node.y;

            for(int d = 0; d < 4; d++){
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if(nx >= 0 && ny >= 0 && nx < N && ny < N){
                    int next = dist[x][y] + map[nx][ny];
                    if(dist[nx][ny] > next){
                        dist[nx][ny] = next;
                        q.offer(new Node(nx, ny, dist[nx][ny]));
                    }
                }
            }
        }
    }
}
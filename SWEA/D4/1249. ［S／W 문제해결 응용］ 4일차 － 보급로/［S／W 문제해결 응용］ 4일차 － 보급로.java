import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int INF = Integer.MAX_VALUE;
    static int N, answer;
    static int[][] map, dist;
    static List<List<Node>> graph;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static class Node implements Comparable<Node>{
        int x, y, weight;

        public Node(int x, int y, int weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            dist = new int[N][N];
            answer = Integer.MAX_VALUE;

            for(int i = 0; i < N; i++){
                String s = br.readLine();
                for(int j = 0; j < N; j++){
                    map[i][j] = s.charAt(j) - '0';
                }
            }

            for(int[] d: dist){
                Arrays.fill(d, INF);
            }

            Dijkstra(0, 0);

            System.out.println("#" + t + " " + dist[N - 1][N - 1]);
        }
    }

    static void Dijkstra(int startX, int startY){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];

        dist[startX][startY] = 0;
        visited[startX][startY] = true;
        pq.offer(new Node(startX, startY, 0));

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int x = node.x;
            int y = node.y;

            for(int d = 0; d < 4; d++){
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if(nx >= 0 && ny >= 0 && nx < N && ny < N){
                    int next = dist[x][y] + map[nx][ny];
                    if(dist[nx][ny] > next){
                        dist[nx][ny] = next;
                        pq.offer(new Node(nx, ny, dist[nx][ny]));
                    }
                }
            }
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N, map[][], answer;
    static List<List<Node>> graph;
    static int dist[][];
    static int INF = Integer.MAX_VALUE;
    static int[][] deltas = {
                                {-1, 0},
                                {0, 1},
                                {1, 0},
                                {0, -1}
                            };
    static class Node implements Comparable<Node> {
        int x;
        int y;
        int weight;

        public Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            dist = new int[N][N];
            answer = Integer.MAX_VALUE;

            graph = new ArrayList<>();
            for(int i = 1; i <= N * N; i++){
                graph.add(new ArrayList<>());
            }

            // 거리 배열을 무한대로 초기화
            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], INF);
            }

            for(int i = 0; i < N; i++) {
                String input = br.readLine();
                for(int j = 0; j < N; j++) {
                    map[i][j] = input.charAt(j) - '0';
                }
            }

            Dijkstra(0, 0);

            answer = dist[N - 1][N - 1];
            System.out.println("#" + t + " " + answer);
        }
    }

    static void Dijkstra(int startX, int startY) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];

        dist[startX][startY] = 0;

        pq.offer(new Node(startX, startY, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int currentX = node.x;
            int currentY = node.y;

            if(visited[currentX][currentY]) continue;

            visited[currentX][currentY] = true;

            for(int d = 0; d < deltas.length; d++) {
                int nextX = currentX + deltas[d][0];
                int nextY = currentY + deltas[d][1];

                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
                    int next = dist[currentX][currentY] + map[nextX][nextY];
                    if(dist[nextX][nextY] > next) {
                        dist[nextX][nextY] = next;
                        pq.offer(new Node(nextX, nextY, dist[nextX][nextY]));
                    }
                }
            }
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, answer, map[][], cost[][];
    static int INF = Integer.MAX_VALUE;
    static int[] delX = {-1, 0, 1, 0}, delY = {0, 1, 0, -1};
    static StringBuilder sb = new StringBuilder();

    static class Node implements Comparable<Node> {
        int from;
        int to;
        int weight;

        Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return weight - node.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = 1;
        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            answer = 0;

            if(N == 0) break;

            map = new int[N][N];
            cost = new int[N][N];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    cost[i][j] = INF;
                }
            }

            cost[0][0] = map[0][0];

            Dijkstra(0, 0);
            answer = cost[N - 1][N - 1];

            sb.append("Problem " + tc++ + ": " + answer + "\n");
        }

        System.out.println(sb.toString());
    }

    static void Dijkstra(int a, int b){
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(a, b, map[0][0]));

        while(!pq.isEmpty()){
            Node current = pq.poll();

            int x = current.from;
            int y = current.to;
            int curCost = current.weight;

            for(int d = 0; d < 4; d++){
                int nx = x + delX[d];
                int ny = y + delY[d];

                if(nx >= 0 && nx < N && ny >= 0 && ny < N){
                    if(cost[nx][ny] > curCost + map[nx][ny]){
                        cost[nx][ny] = curCost + map[nx][ny];
                        pq.offer(new Node(nx, ny, cost[nx][ny]));
                    }
                }
            }
        }
    }
}

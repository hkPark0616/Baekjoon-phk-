import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, parent[];
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static class Node implements Comparable<Node> {
        int from;
        int to;
        int weight;

        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
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

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for(int i = 0; i <= N; i++){
            parent[i] = i;
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.offer(new Node(a, b, w));
        }

        int size = pq.size();
        long reduce = 0;
        long sum = 0;
        int cnt = 0;
        boolean flag = true;
        for(int i = 0; i < size; i++){
            Node node = pq.poll();
            sum += node.weight;
            if(!isSame(node.from, node.to)){
                reduce += node.weight;
                cnt++;
                union(node.from, node.to);

            }
        }

        System.out.println(cnt != N - 1 ? -1 : sum - reduce);
    }

    static void union(int a, int b){
        a = parent[a];
        b = parent[b];

        if(a != b){
            parent[b] = a;
        }
    }

    static int find(int a){
        if(parent[a] == a) return parent[a];

        return parent[a] = find(parent[a]);
    }

    static boolean isSame(int a, int b){
        return find(a) == find(b);
    }
}

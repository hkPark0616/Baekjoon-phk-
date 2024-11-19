import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] parent;
    static PriorityQueue<Node> pq;

    static class Node implements Comparable<Node> {
        int a, b, w;

        public Node(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for(int i = 0; i <= N; i++){
            parent[i] = i;
        }

        pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int w = Integer.parseInt(st.nextToken());
                if(w != 0){
                    pq.offer(new Node(i, j, w));
                }
            }
        }

        int size = pq.size();
        long answer = 0;
        for(int i = 0; i < size; i++){
            Node node = pq.poll();
            int from = node.a;
            int to = node.b;
            int weight = node.w;

            if(!isSame(from, to)){
                answer += weight;
                union(from, to);
            }
        }

        System.out.println(answer);
    }

    static void union(int a, int b){
        a = parent[a];
        b = parent[b];

        if(a != b){
            parent[b] = a;
        }
    }

    static int find(int a){
        if( a == parent[a]){
            return parent[a];
        }

        return parent[a] = find(parent[a]);
    }

    static boolean isSame(int a, int b){
        return find(a) == find(b);
    }
}
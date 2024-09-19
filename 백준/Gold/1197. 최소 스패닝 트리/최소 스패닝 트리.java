import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V, E, parent[];
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

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        for(int i = 0; i <= V; i++){
            parent[i] = i;
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.offer(new Node(a, b, w));
        }

        int size = pq.size();
        int answer = 0;
        for(int i = 0; i < size; i++){
            Node node = pq.poll();
            int from = node.from;
            int to = node.to;
            int weight = node.weight;

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
        if(a == parent[a]){
            return parent[a];
        }
        return parent[a] = find(parent[a]);
    }

    static boolean isSame(int a, int b){
        return find(a) == find(b);
    }
}
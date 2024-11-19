import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V, E, parent[];
    static PriorityQueue<Node> pq = new PriorityQueue<Node>();
    static class Node implements Comparable<Node> {
        int a, b, w;

        public Node(int a, int b, int w){
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
        int max = 0;
        for(int i = 0; i < size; i++){
            Node node = pq.poll();
            int a = node.a;
            int b = node.b;
            int w = node.w;

            if(!isSame(a, b)){
                answer += w;
                max = w;
                union(a, b);
            }

        }

        System.out.println(answer - max);
    }

    static void union(int a, int b){
        a = parent[a];
        b = parent[b];

        if(a != b){
            parent[b] = a;
        }
    }

    static int find(int a){
        if(parent[a] == a){
            return parent[a];
        }

        return parent[a] = find(parent[a]);
    }

    static boolean isSame(int a, int b){
        return find(a) == find(b);
    }
}
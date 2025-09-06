import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, K;
    static int[] parent;
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        parent = new int[N + 1];
        for(int i = 0; i <= N; i++) parent[i] = i;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= K; i++) pq.offer(new Edge(0, Integer.parseInt(st.nextToken()), 0));
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(u, v, w));
        }

        int answer = 0;
        while(!pq.isEmpty()) {
            Edge e = pq.poll();

            if(!isSame(e.from, e. to)) {
                union(e.from, e.to);
                answer += e.weight;
            }
        }
        
        System.out.println(answer);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) {
            parent[b] = a;
        }
    }

    static int find(int a) {
        if(a == parent[a]) return parent[a];

        return parent[a] = find(parent[a]);
    }

    static boolean isSame(int a, int b) {
        return find(a) == find(b);
    }
}
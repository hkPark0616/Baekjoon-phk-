import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
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

        N = Integer.parseInt(br.readLine());

        parent = new int[N];
        for(int i = 0; i < N; i++) parent[i] = i;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int total = 0;
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                
                if(c == '0') continue;
                
                int len = Character.isLowerCase(c) ? (c - 'a' + 1) : (c - 'A' + 27);
                total += len;
                
                if(i != j) pq.offer(new Edge(i, j, len));
            }
        }

        int cnt = 0;
        int sum = 0;
        while(!pq.isEmpty()) {
            Edge e = pq.poll();

            if(!isSame(e.from, e.to)) {
                union(e.from, e.to);
                cnt++;
                sum += e.weight;                
            }
        }

        System.out.println(cnt == N - 1 ? total - sum : -1);
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
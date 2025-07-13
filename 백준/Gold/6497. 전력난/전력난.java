import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int m, n, total, min;
    static int[] parent;
    static PriorityQueue<Edge> pq;
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
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            
            if (m == 0 && n == 0) break;

            total = min = 0;
            pq = new PriorityQueue<>();
            
            parent = new int[m];
            for(int i = 0; i < m; i++) parent[i] = i;
            
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                
                pq.offer(new Edge(x, y, z));
                total += z;
            }
    
            while(!pq.isEmpty()) {
                Edge e = pq.poll();
    
                if(!isSame(e.from, e.to)) {
                    union(e.from, e.to);
                    min += e.weight;
                }
            }
    
            System.out.println(total - min);   
        }
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
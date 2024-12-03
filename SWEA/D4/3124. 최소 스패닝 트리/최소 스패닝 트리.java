import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int V, E, parent[];
    static long answer;
    static PriorityQueue<Edge> pq;
    static class Edge implements Comparable<Edge>{
        int a, b, w;

        public Edge(int a, int b, int w){
            this.a = a;
            this.b = b;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            parent = new int[V + 1];
            for(int i = 0; i <= V; i++){
                parent[i] = i;
            }

            pq = new PriorityQueue<>();
            for(int i = 0; i < E; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                pq.offer(new Edge(a, b, w));
            }

            answer = 0;
            int size = pq.size();
            for(int i = 0; i < size; i++){
                Edge e = pq.poll();

                if(!isSame(e.a, e.b)){
                    union(e.a, e.b);
                    answer += e.w;
                }
            }

            System.out.println("#" + t + " " + answer);
        }
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
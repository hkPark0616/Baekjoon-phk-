import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] parent;
    static char[] type;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.weight, e.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        type = new char[N + 1];
        parent = new int[N + 1];
        for(int i = 0; i <= N; i++) parent[i] = i;

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            type[i] = st.nextToken().charAt(0);
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if(type[u] == type[v]) continue;

            pq.offer(new Edge(u, v, d));
        }

        int answer = 0;
        int cnt = 0;
        while(!pq.isEmpty()) {
            Edge e = pq.poll();

            if(!isSame(e.from, e.to)) {
                union(e.from, e.to);
                answer += e.weight;
                cnt++;
            }
        }

        System.out.println(cnt == N - 1 ? answer : -1);
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
import java.util.*;
import java.lang.*;
import java.io.*;

// Kruscal
class Main {
    static int N, M, maxWeight, start, end;
    static int[] parent;
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
            return Integer.compare(e.weight, this.weight);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];

        for(int i = 0; i <= N; i++) parent[i] = i;
        

        maxWeight = 0;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(A, B, C));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int answer = 0;
        
        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            union(e.from, e.to);

            // 만약 두 공장이 같은 부모를 같는다면 연결됐다 -> 이때의 가중치가 정답
            if(isSame(start, end)) {
                answer = e.weight;
                break;
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
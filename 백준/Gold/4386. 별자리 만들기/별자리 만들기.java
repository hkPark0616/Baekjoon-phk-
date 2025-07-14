import java.util.*;
import java.io.*;

class Main {
    static int n;
    static int[] parent;
    static List<Star> list;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        parent = new int[n];
        for(int i = 0; i < n; i++) parent[i] = i;

        list = new ArrayList<>();
        pq = new PriorityQueue<>();

        // 별 위치 저장
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            
            list.add(new Star(x, y));
        }

        // 모든 별에 대해 간선 생성
        for(int i = 0; i < n; i++) {
            Star star1 = list.get(i);
            for(int j = i + 1; j < n; j++) {
                Star star2 = list.get(j);

                // 유클리드 거리
                double dist = Math.sqrt(Math.pow(star1.x - star2.x, 2) + Math.pow(star1.y - star2.y, 2));

                pq.offer(new Edge(i, j, dist));
            }
        }
        
        double sum = 0;
        while(!pq.isEmpty()) {
            Edge e = pq.poll();

            if(!isSame(e.from, e.to)) {
                union(e.from, e.to);
                sum += e.weight;
            }
        }

        System.out.printf("%.2f\n", sum);
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

    static class Star {
        double x, y;

        public Star(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static class Edge implements Comparable<Edge> {
        int from, to;
        double weight;
        
        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }

}

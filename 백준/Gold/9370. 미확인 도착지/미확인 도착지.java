import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m, t; // 교차로, 도로, 목적지 후보
    static int s, g, h; // 출발지, g와 h 사이의 도로 지나가
    static int[] distS, distG, distH;
    static List<List<Edge>> graph;
    static List<Integer> candidaties, answer;
    static StringBuilder sb = new StringBuilder();
    static class Edge implements Comparable<Edge> {
        int to, weight;

        public Edge(int to, int weight) {
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
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for(int i = 0; i <= n; i++) graph.add(new ArrayList<>());
            
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph.get(a).add(new Edge(b, d));
                graph.get(b).add(new Edge(a, d));
            }

            candidaties = new ArrayList<>();
            for(int i = 0; i < t; i++) {
                int x = Integer.parseInt(br.readLine());
                candidaties.add(x);
            }

            // s, g, h에서 각각 출발하는 다익스트라 실행
            distS = Dijkstra(s); // s에서 모든 정점까지 최단거리 구하기
            distG = Dijkstra(g); // g -> h와 h -> x의 최단 거리 구하기 용도(g에서 모든 정점까지)
            distH = Dijkstra(h); // h -> g와 g -> x의 최단 거리 구하기 용도(h에서 모든 정점까지)

            answer = new ArrayList<>();

            for(int x: candidaties) {
                // s -> g  +  g -> h  +  h -> x
                int path1 = distS[g] + distG[h] + distH[x];
                // s -> h  +  h -> g  +  g -> x
                int path2 = distS[h] + distH[g] + distG[x];

                // x 로 가는 최단 경로가 g-h/h-g 간선을 지나갔는지
                if(distS[x] == path1 || distS[x] == path2) {
                    answer.add(x);
                }
            }

            Collections.sort(answer);
            
            for (int i = 0; i < answer.size(); i++) {
                sb.append(answer.get(i));
                if (i < answer.size() - 1) sb.append(" ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
    }

    static int[] Dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            int now = cur.to;

            if(cur.weight > dist[now]) continue;

            for(Edge nextEdge: graph.get(now)) {
                int next = dist[now] + nextEdge.weight;

                if(dist[nextEdge.to] > next) {
                    dist[nextEdge.to] = next;
                    pq.offer(new Edge(nextEdge.to, dist[nextEdge.to]));
                }
            }
        }
        
        return dist;
    } 
}
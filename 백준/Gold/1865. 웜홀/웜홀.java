import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, W;
    static int S, E, T;
    static int[] dist;
    static List<Edge> edgeList;
    static class Edge {
        int s, e, t;

        public Edge(int s, int e, int t) {
            this.s = s;
            this.e = e;
            this.t = t;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int TC = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < TC; tc++) {
            edgeList = new ArrayList<>();
            
            // 지점 수, 도로 개수, 웜홀 개수
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            
            // 도로 정보
            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                S = Integer.parseInt(st.nextToken());
                E = Integer.parseInt(st.nextToken());
                T = Integer.parseInt(st.nextToken());
                edgeList.add(new Edge(S, E, T));
                edgeList.add(new Edge(E, S, T)); // 양방향 도로
            }

            // 웜홀 정보
            for (int w = 0; w < W; w++) {
                st = new StringTokenizer(br.readLine());
                S = Integer.parseInt(st.nextToken());
                E = Integer.parseInt(st.nextToken());
                T = Integer.parseInt(st.nextToken());
                edgeList.add(new Edge(S, E, -T)); // 시간 되돌리므로 음수로
            }

            // 모든 정점을 출발점으로
            for (int i = 1; i <= N; i++) {
                edgeList.add(new Edge(0, i, 0));
            }

            System.out.println(BellmanFord(0) ? "YES" : "NO");
        }
    }

    static boolean BellmanFord(int start) {
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for(int i = 0; i < N; i++) {
            for(Edge e: edgeList) {
                if(dist[e.s] != Integer.MAX_VALUE && dist[e.e] > dist[e.s] + e.t) {
                    dist[e.e] = dist[e.s] + e.t;
                }
            }
        }

        for (Edge e : edgeList) {
            if (dist[e.s] != Integer.MAX_VALUE && dist[e.e] > dist[e.s] + e.t) {
                return true; // 시간이 줄어들면서 출발 위치로 돌아오는 게 가능 -> 음수 사이클 존재
            }
        }
        
        return false;
    }
}
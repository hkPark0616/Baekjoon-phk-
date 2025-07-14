import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, maxWeight, start, end;
    static List<List<Edge>> graph = new ArrayList<>();
    static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        maxWeight = 0;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new Edge(B, C));
            graph.get(B).add(new Edge(A, C));
            
            maxWeight = Math.max(maxWeight, C);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        System.out.println(parametricSearch());
    }

    // 운반 가능한 최대 중량 찾기(이분탐색)
    static int parametricSearch() {
        int left = 1;
        int right = maxWeight;
        int answer = 0;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(canMove(mid)) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    // bfs
    static boolean canMove(int mid) {
        boolean[] visited = new boolean[N + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();

            if(cur == end) return true;

            for(Edge e: graph.get(cur)) {
                // mid는 현재 운반 가능한 다리 중량
                // 지나는 다리들의 중량이 mid 이상이어야 운반 가능
                if(!visited[e.to] && e.weight >= mid) {
                    visited[e.to] = true;
                    q.offer(e.to);
                }
            }
        }
        
        return false;
    }
}
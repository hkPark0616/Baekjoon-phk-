import java.util.*;
import java.lang.*;
import java.io.*;

// 백준 16947
class Main {
    static int N;
    static boolean[] visited, hasCycle;
    static int[] dist;
    static List<List<Integer>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        hasCycle = new boolean[N + 1];
        dist = new int[N + 1];

        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        dfs(1, -1);

        bfs();

        for(int i = 1; i <= N; i++) {
            sb.append(dist[i] + " ");
        }

        System.out.println(sb);
    }

    static int dfs(int cur, int prev) {
        visited[cur] = true;

        for(int next: graph.get(cur)) {
            if(next == prev) continue; // 이전 역으로 가면 패스
            
            if(visited[next]) { // 이미 방문 노드인데, 이전 역도 아니면
                hasCycle[cur] = true;
                return next; // 싸이클 시작 노드 리턴
            }

            int cycleStart = dfs(next, cur);
            if(cycleStart != -1) {
                hasCycle[cur] = true;
                if(cycleStart == cur) return -1; // 싸이클 한 바퀴 다 돌음
                else return cycleStart;
            }
        }

        return -1;
    }

    static void bfs() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        visited = new boolean[N + 1];

        for(int i = 1; i <= N; i++) {
            if(hasCycle[i]) {
                q.offer(i);
                visited[i] = true;
                dist[i] = 0;
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next: graph.get(cur)) {
                if(!visited[next]) {
                    visited[next] = true;
                    dist[next] = dist[cur] + 1;
                    q.offer(next);
                }
            }
        }
    }
}
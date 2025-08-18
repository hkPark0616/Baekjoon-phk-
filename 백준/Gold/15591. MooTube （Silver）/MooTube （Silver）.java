import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, Q;
    static boolean[] visited;
    static List<List<Video>> graph = new ArrayList<>();
    static class Video {
        int to, usado;

        public Video(int to, int usado) {
            this.to = to;
            this.usado = usado;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            graph.get(p).add(new Video(q, r));
            graph.get(q).add(new Video(p, r));
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            visited = new boolean[N + 1];

            int result = bfs(k, v);

            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }

    static int bfs(int k, int v) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        visited[v] = true;
        q.offer(v);
        int cnt = 0;

        while(!q.isEmpty()) {
            int cur = q.poll();
            for(Video video: graph.get(cur)) {
                if(visited[video.to]) continue;
                if(video.usado >= k) {
                    visited[video.to] = true;
                    q.offer(video.to);
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
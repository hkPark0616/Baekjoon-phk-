import java.util.*;
import java.lang.*;
import java.io.*;

// 백준 15971
class Main {
    static int N, R1, R2;
    static List<List<int[]>> graph = new ArrayList<>();
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R1 = Integer.parseInt(st.nextToken());
        R2 = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        visited = new boolean[N + 1];

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[] {b, w});
            graph.get(b).add(new int[] {a, w});
        }
        
        int answer = bfs();

        System.out.println(answer);
    }


    static int bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[R1] = true;
        q.offer(new int[] {R1, 0, 0});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int to = cur[0];
            int w = cur[1];
            int maxEdge = cur[2];

            if(to == R2) return w - maxEdge;

            for(int[] next: graph.get(to)) {
                if(!visited[next[0]]) {
                    visited[next[0]] = true;
                    q.offer(new int[] {next[0], w + next[1], Math.max(maxEdge, next[1])});
                }
            }
        }

        return -1;
    }
}
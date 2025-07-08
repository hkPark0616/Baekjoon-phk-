import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, K;
    static boolean[] visited;
    static int[] prev;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[100001];
        prev = new int[100001];

        System.out.println(bfs(N, K));

        List<Integer> path = new ArrayList<>();
        for(int i = K; i != N; i = prev[i]) {
            path.add(i);
        }
        path.add(N);
        Collections.reverse(path);

        for(int p: path) {
            System.out.print(p + " ");
        }
    }

    static int bfs(int s, int d) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[s] = true;
        q.offer(new int[] {s, 0});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int c = cur[0];
            int t = cur[1];

            if(c == d) {
                return t;
            }

            int[] nextDir = {c - 1, c + 1, c * 2};

            for(int next: nextDir) {
                if(next >= 0 && next < 100001 && !visited[next]) {
                    visited[next] = true;
                    q.offer(new int[] {next, t + 1});
                    prev[next] = c;
                }
            }
        }
        
        return -1;
    }
}
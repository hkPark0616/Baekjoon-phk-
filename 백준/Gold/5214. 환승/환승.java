import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, K, M;
    static boolean[] visited;
    static List<List<Integer>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 역을 연결하는 하이퍼튜브를 노드라고 가정(N 이후의 값)
        visited = new boolean[N + M + 1];
        for(int i = 0; i <= N + M; i++) graph.add(new ArrayList<>());
        
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int h = N + i; // 하이퍼튜브 노드
            for(int k = 0; k < K; k++) {
                int s = Integer.parseInt(st.nextToken());
                graph.get(s).add(h);
                graph.get(h).add(s);
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[1] = true;
        q.offer(new int[] {1, 1});

        while(!q.isEmpty()) {
            int[] c = q.poll();
            int cur = c[0];
            int cnt = c[1];
            
            if(cur == N) return cnt;

            for(int next: graph.get(cur)) {
                if(!visited[next]) {
                    visited[next] = true;
                    
                    if(next <= N) { // 역을 거쳤으면 cnt + 1
                        q.offer(new int[] {next, cnt + 1});
                    } else { // 하이퍼튜브를 거쳤으면 cnt 그대로 진행
                        q.offer(new int[] {next, cnt});   
                    }
                }
            }
        }
        
        return -1;
    }
}
import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= 100; i++) graph.add(new ArrayList<>());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
        }

        System.out.println(bfs());
    }

    static int bfs() {
        boolean[] visited = new boolean[101];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {1, 0});
        visited[1] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int pos = cur[0];
            int cnt = cur[1];

            if(pos == 100) {
                return cnt;
            }

            for(int i = 1; i <= 6; i++) {
                int nextPos = pos + i;
                if(nextPos > 100) continue; // 100 넘으면 안됨

                // 사다리나 뱀 만났을 때
                if(!graph.get(nextPos).isEmpty()) {
                    nextPos = graph.get(nextPos).get(0);
                }

                if(!visited[nextPos]) {
                    visited[nextPos] = true;
                    q.offer(new int[] {nextPos, cnt + 1});
                }
            }
        }
        
        return -1;
    }
}
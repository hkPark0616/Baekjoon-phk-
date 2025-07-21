import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] indegree;
    static List<List<Integer>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        indegree = new int[N + 1];
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
        
            int a = Integer.parseInt(st.nextToken());
        
            for (int i = 1; i < num; i++) {
                int b = Integer.parseInt(st.nextToken());
        
                graph.get(a).add(b);
                indegree[b]++;
                a = b;
            }
        }

        topologySort();
    }

    static void topologySort() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        int cnt = 0;

        for(int i = 1; i <= N; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            sb.append(cur).append("\n");
            cnt++;
            
            for(int n: graph.get(cur)) {
                indegree[n]--;
                
                if(indegree[n] == 0) {
                    q.offer(n);
                }
            }
        }

        if (cnt == N) {
            System.out.println(sb.toString());
        } else {
            System.out.println(0);
        }
    }
}
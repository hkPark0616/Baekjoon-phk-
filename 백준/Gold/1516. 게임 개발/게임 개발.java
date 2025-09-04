import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[] indegree, time;
    static List<List<Integer>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        indegree = new int[N + 1];
        time = new int[N + 1];
        
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while(true) {
                int n = Integer.parseInt(st.nextToken());

                if(n == -1) break;

                graph.get(n).add(i);
                indegree[i]++;
            }

        }

        topologySort();
    }

    static void topologySort() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int[] result = new int[N + 1];
        
        for(int i = 1; i <= N; i++) {
            if(indegree[i] == 0) q.offer(i);
            result[i] = time[i];
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next: graph.get(cur)) {
                indegree[next]--;

                if(indegree[next] == 0) {
                    q.offer(next);
                }

                result[next] = Math.max(result[next], result[cur] + time[next]);
            }
        }

        for(int i = 1; i <= N; i++) {
            System.out.println(result[i]);
        }
    }
}
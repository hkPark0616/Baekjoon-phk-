import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[] indegree, time, result;
    static List<List<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        indegree = new int[N + 1];
        time = new int[N + 1];
        result = new int[N + 1];
        
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            time[i] = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            for(int j = 0; j < n; j++) {
                int b = Integer.parseInt(st.nextToken());
                graph.get(b).add(i);
                indegree[i]++;
            }
        }

        System.out.println(topologySort());
    }

    static int topologySort() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int answer = 0;

        for(int i = 1; i <= N; i++) {
            // 선행 작업이 없는 작업들은 시작 시간이 소요시간
            if(indegree[i] == 0) {
                q.offer(i);
                result[i] = time[i];
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            List<Integer> list = graph.get(cur);
            for(int next: list) {
                indegree[next]--;
                
                // 여러 선행 작업 중 가장 늦게 끝나는 시점 이후에 시작해야 하므로, 그에 따라 완료 시간을 갱신
                result[next] = Math.max(result[next], result[cur] + time[next]);
                
                if(indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
    
        for(int i = 1; i <= N; i++) {
            answer = Math.max(answer, result[i]);
        }
        
        return answer;
    }
}
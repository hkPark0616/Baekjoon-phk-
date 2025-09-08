import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int K, M, P;
    static int[] indegree;
    static List<List<Integer>> graph;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());

            K = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());

            indegree = new int[M + 1];
            graph = new ArrayList<>();
            for(int i = 0; i <= M; i++) graph.add(new ArrayList<>());
            
            for(int i = 0; i < P; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                graph.get(A).add(B);
                indegree[B]++;
            }

            System.out.println(K + " " + topologySort());
        }
        
    }

    static int topologySort() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int[] strahlerOrder = new int[M + 1]; // 각 노드의 strahler 순서
        int[] maxStrahlerOrder = new int[M + 1]; // 부모들 중 가장 큰 Strahler 순서
        int[] cnt = new int[M + 1]; // 그 최대값을 가진 부모의 개수
        
        for(int i = 1; i <= M; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
                strahlerOrder[i] = 1; // 강의 근원인 노드의 순서는 1
                maxStrahlerOrder[i] = 1;
            }
        }

        int result = 0;

        while(!q.isEmpty()) {
            int cur = q.poll();

            // 부모 중 최대값을 가진게 2개 이상이면 순서는 +1
            if(cnt[cur] > 1) { 
                strahlerOrder[cur] = maxStrahlerOrder[cur] + 1;
            } else { // 1개이면 그대로
                strahlerOrder[cur] = maxStrahlerOrder[cur];
            }

            result = Math.max(result, strahlerOrder[cur]);
            
            for(int next: graph.get(cur)) {
                indegree[next]--;

                // 현재 값이 next가 본 부모들 중 가장 큰 값보다 더 큰 값
                if(strahlerOrder[cur] > maxStrahlerOrder[next]) {
                    maxStrahlerOrder[next] = strahlerOrder[cur]; // 최대 갱신
                    cnt[next] = 1; // 초기화
                } else if(strahlerOrder[cur] == maxStrahlerOrder[next]) {
                    cnt[next]++; // 같은 최대값을 가진 부모 추가
                }

                if(indegree[next] == 0) q.offer(next);
            }
        }

        return result;
    }
}
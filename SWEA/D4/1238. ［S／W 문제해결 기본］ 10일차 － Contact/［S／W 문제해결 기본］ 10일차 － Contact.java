import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int K, S, answer;
    static List<Integer>[] graph;
    static int distance[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int t = 1; t <= 10; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            S= Integer.parseInt(st.nextToken());
            answer = 0;

            graph = new ArrayList[101];
            for(int i = 1; i < 101; i++){
                graph[i] = new ArrayList<Integer>();
            }
            distance = new int[101];
            Arrays.fill(distance, -1);

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < K/2; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a].add(b); // a -> b
            }
            sb.append("#" + t + " ");
            bfs(S);
            sb.append(answer);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        distance[start] = 0; // 시작 정점으로부터의 거리값 계산
        queue.offer(start); // 시작 큐 삽입

        int max = 0;
        while(!queue.isEmpty()) {
            int current = queue.poll();
            // 같은 depth 만큼
            for(int i = 0; i < graph[current].size(); i++) {
                int next = graph[current].get(i);
                // 아직 방문하지 않은 노드
                if(distance[next] == -1) {
                    distance[next] = distance[current] + 1; // 방문 체크
                    queue.offer(next);
                }
            }
        }

        int maxDistance = distance[0];
        for(int i = 1; i < 101; i++){
            if(maxDistance <= distance[i]) {
                maxDistance = distance[i];
                answer = i;
            }
        }
    }

}
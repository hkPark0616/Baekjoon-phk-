import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, W;
    static int[] arr;
    static int[] indegree;
    static List<List<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[N + 1];
            indegree = new int[N + 1];
            graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int time = Integer.parseInt(st.nextToken());
                arr[i] = time;
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph.get(a).add(b);
                indegree[b]++;
            }
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());

            int answer = topologySort();

            System.out.println(answer);
        }
    }
    static int topologySort(){
        int[] result = new int[N + 1];
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            if(indegree[i] == 0) {
                result[i] = arr[i];
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            // 인접 리스트
            List<Integer> list = graph.get(cur);

            for(int i = 0; i < list.size(); i++) {
                int num = list.get(i);
                result[num] = Math.max(result[num], result[cur] + arr[num]);
                indegree[num]--;

                if(indegree[num] == 0) {
                    queue.add(num);
                }
            }
        }

        return result[W];
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, indegree[];
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        indegree = new int[N + 1];
        graph = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);

            indegree[b]++;
        }

        topologySort();
    }

    static void topologySort(){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int[] semester = new int[N + 1];

        for(int i = 1; i <= N; i++){
            if(indegree[i] == 0){
                q.offer(i);
                semester[i] = 1;
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            List<Integer> list = graph.get(cur);

            for(int next: list){
                indegree[next]--;

                if(indegree[next] == 0){
                    q.offer(next);
                    semester[next] = semester[cur] + 1;
                }
            }
        }

        for(int i = 1; i <= N; i++){
            System.out.print(semester[i] + " ");
        }
    }
}
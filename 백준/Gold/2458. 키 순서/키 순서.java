import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static boolean[] visited;
    static List<List<Integer>> graph = new ArrayList<>();
    static List<List<Integer>> reverseGraph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>()); // 큰 학생
            reverseGraph.add(new ArrayList<>()); // 작은 학생
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            // 키가 a보다 b가 크다
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            reverseGraph.get(b).add(a);
        }

        int result = 0;

        // 학생 돌면서 작은애, 큰애 연결된 수 구하고
        // 더해서 N - 1 이면 순서를 아는 학생
        for(int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            int taller = dfs(i, graph);

            visited = new boolean[N + 1];
            int smaller = dfs(i, reverseGraph);

            if(taller + smaller == N - 1) {
                result++;
            }
        }


        System.out.println(result);
    }

    static int dfs(int cur, List<List<Integer>> g) {
        int count = 0;
        visited[cur] = true;

        for(int nextStudent: g.get(cur)) {
            if(!visited[nextStudent]) {
                count += dfs(nextStudent, g) + 1; //  + 1 은 직접 연결된 학생 포함
            }
        }

        
        return count;
    }
}
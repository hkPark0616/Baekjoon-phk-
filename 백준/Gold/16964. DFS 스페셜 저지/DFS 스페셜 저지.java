import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static List<List<Integer>> graph = new ArrayList<>();
    static List<Integer> result = new ArrayList<>();
    static boolean[] visited;
    static int[] order, priority;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        for(int i = 0 ; i <= N; i++) graph.add(new ArrayList<>());

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        order = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) order[i] = Integer.parseInt(st.nextToken());

        priority = new int[N + 1]; // 노드 x가 입력 순서에서 몇 번째에 등장했는지를 기록
        for(int i = 0; i < N; i++) priority[order[i]] = i;

        // 우선순위 기준 정렬
        for(int i = 1; i <= N; i++) graph.get(i).sort((a, b) -> priority[a] - priority[b]);

        result.add(1);
        dfs(1);

        boolean answer = true;
        for(int i = 0; i < N; i++) {
            if(result.get(i) != order[i]) {
                answer = false;
                break;
            }
        }

        System.out.println(answer ? 1 : 0);
    }

    static void dfs(int x) {
        if(visited[x]) return;
        visited[x] = true;

        for(int n: graph.get(x)) {
            if(!visited[n]) {
                result.add(n);
                dfs(n);
            }
        }
    }
}
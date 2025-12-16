import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, S;
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] gomgom, visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        gomgom = new boolean[N + 1];
        visited = new boolean[N + 1];
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
        }

        S = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < S; i++) gomgom[Integer.parseInt(st.nextToken())] = true;

        System.out.println(dfs(1) ? "yes" : "Yes");
    }

    static boolean dfs(int cur) {
        if(gomgom[cur]) return false;

        visited[cur] = true;

        // 더 이상 간선을 따라서 이동할 수 없는 경우 투어리스트 여행 종료
        if(graph.get(cur).isEmpty()) return true;

        for(int next: graph.get(cur)) {
            if(!visited[next]) {
                if(dfs(next)) return true;
            }
        }

        return false;
    }
}
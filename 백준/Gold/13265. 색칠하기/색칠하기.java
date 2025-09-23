import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] color;
    static boolean answer;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            graph = new ArrayList<>();
            for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph.get(x).add(y);
                graph.get(y).add(x);
            }

            color = new int[N + 1];
            Arrays.fill(color, -1);
            answer = true;
            for(int i = 1; i <= N; i++) {
                if(color[i] == -1) dfs(i, 0);
            }

            sb.append(answer ? "possible" : "impossible").append("\n");
        }

        System.out.println(sb);
    }

    // c -> 0, 1 번갈아 칠하기
    static void dfs(int cur, int c) {
        color[cur] = c;
        
        for(int next: graph.get(cur)) {
            if(color[next] == -1) { // 아직 안칠함
                dfs(next, c == 0 ? 1 : 0);
            } else if(color[next] == c) { // 이미 사용한 색상
                answer = false;
            }
        }
    }
}
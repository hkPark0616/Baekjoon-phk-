import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int A, B, C;
    static boolean[][] visited;
    static List<Integer> result = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new boolean[201][201];

        dfs(0, 0, C);

        Collections.sort(result);

        for(int r: result) System.out.print(r + " ");
    }

    static void dfs(int a, int b, int c) {
        if(visited[a][b]) return;

        visited[a][b] = true;

        if(a == 0) result.add(c);

        int[] now = {a, b, c};
        int[] cap = {A, B, C};

        // 조합
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(i == j) continue;

                int[] next = Arrays.copyOf(now, 3);

                // i -> j 로 옮겨
                // 보낼 수 있는 물 vs 받는 물통에 남은 공간 -> 음수가 되는 상황 고려
                int move = Math.min(now[i], cap[j] - now[j]);

                next[i] -= move;
                next[j] += move;

                dfs(next[0], next[1], next[2]);
            }
        }
    }
}
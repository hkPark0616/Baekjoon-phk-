import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[100001];

        System.out.println(bfs(N, K));
    }

    static int bfs(int s, int d){
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        visited[s] = true;
        queue.offer(new int[] {s, 0});

        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            int c = cur[0];
            int t = cur[1];

            if(c == d) {
                return t;
            }

            if (c * 2 < 100001 && !visited[c * 2]) {
                visited[c * 2] = true;
                queue.offer(new int[]{c * 2, t});
            }
            if (c - 1 >= 0 && !visited[c - 1]) {
                visited[c - 1] = true;
                queue.offer(new int[]{c - 1, t + 1});
            }
            if (c + 1 < 100001 && !visited[c + 1]) {
                visited[c + 1] = true;
                queue.offer(new int[]{c + 1, t + 1});
            }
        }

        return -1;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int MAX = 100001;
    static int[] visited;
    static int N, K, min, cnt;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new int[MAX];
        Arrays.fill(visited, -1);
        min = Integer.MAX_VALUE;
        cnt = 0;
        bfs(N, K);

        System.out.println(min);
        System.out.println(cnt);
    }

    static void bfs(int s, int d){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        visited[s] = 0;

        while(!queue.isEmpty()){
            int cur = queue.poll();

            // 동생을 찾았는데 더 작은 시간이 걸리면 갱신 해주고 cnt는 다시 1로
            // 똑같은 시간이 걸렸으면 방법 + 1
            if(cur == d) {
                if(visited[cur] < min){
                    min = visited[cur];
                    cnt = 1;
                }else if(visited[cur] == min){
                    cnt++;
                }
                continue;
            }
            
            // 이동 가능한 다음 위치 배열
            int[] nextMove = {cur - 1, cur + 1, cur * 2};
            for(int next: nextMove){
                // 이동한 위치가 범위 내에 있고, 아직 방문하지 않았거나 cur에 기록된 시간이 next위치의 시간과 같으면
                // 즉, 해당 위치에 도달한 적은 있지만, 다른 경로를 통해서 도달할 수 있다면
                if(next >= 0 && next < MAX && (visited[next] == -1 || visited[next] == visited[cur] + 1)){
                    queue.offer(next);
                    visited[next] = visited[cur] + 1;
                }
            }
        }
    }
}

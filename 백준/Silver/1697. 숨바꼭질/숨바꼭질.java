import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int MAX = 100001;
    static int[] visited;
    static int N, K, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new int[MAX];

        answer = 0;
        bfs(N, K);

        System.out.println(answer);
    }

    static void bfs(int s, int d){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        visited[s] = 0;

        while(!queue.isEmpty()){
            int cur = queue.poll();
            // 동생을 찾으면 걸린 시간 리턴
            if(cur == d) {
                answer = visited[cur];
                return;
            }
            
            // 이동 가능한 다음 위치 배열
            int[] nextMove = {cur - 1, cur + 1, cur * 2};
            for(int next: nextMove){
                // 이동한 위치가 범위 내에 있고, 아직 방문하지 않았으면 이동 시간 기록
                if(next >= 0 && next < MAX && visited[next] == 0){
                    queue.offer(next);
                    visited[next] = visited[cur] + 1;
                }
            }
        }
    }
}
import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, K;
    static ArrayDeque<long[]> q = new ArrayDeque<>();
    static Set<Long> visited = new HashSet<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            long pos = Long.parseLong(st.nextToken());
            q.offer(new long[] {pos, 0});
            visited.add(pos);
        }

        System.out.println(bfs());
    }

    static long bfs() {
        int cnt = 0;
        long answer = 0;
        
        while(!q.isEmpty() && cnt < K) {
            long[] cur = q.poll();
            long x = cur[0];
            long dist = cur[1];

            long nx1 = x - 1;
            long nx2 = x + 1;

            // 왼
            if(!visited.contains(nx1)) {
                visited.add(nx1);
                q.offer(new long[] {nx1, dist + 1});
                answer += dist + 1;
                cnt++;
                if(cnt == K) break;
            }

            // 오
            if(!visited.contains(nx2)) {
                visited.add(nx2);
                q.offer(new long[] {nx2, dist + 1});
                answer += dist + 1;
                cnt++;
                if(cnt == K) break;
            }
        }
        return answer;
    }
}
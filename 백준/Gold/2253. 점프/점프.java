import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static boolean[] smallStones;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        smallStones = new boolean[N + 1];
        for(int i = 0; i < M; i++) {
            smallStones[Integer.parseInt(br.readLine())] = true;
        }
          
        System.out.println(bfs());
    }

    static int bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>(); // stone, speed, cnr
        boolean[][] visited = new boolean[N + 1][150]; // 최대 속도 150 정도 -> k(k + 1) / 2 <= 10000

        q.offer(new int[] {1, 0, 0});
        visited[1][0] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int stone = cur[0];
            int speed = cur[1];
            int cnt = cur[2];

            if(stone == N) return cnt;

            // x-1, x, x + 1
            for(int next = speed - 1; next <= speed + 1; next++) {
                if(next <= 0) continue;
                
                int ns = stone + next;
                if(ns > N) continue;
                if(smallStones[ns]) continue;
                
                if(!visited[ns][next]) {
                    visited[ns][next] = true;
                    q.offer(new int[] {ns, next, cnt + 1});
                }
            }
        }

        return -1;
    }
}
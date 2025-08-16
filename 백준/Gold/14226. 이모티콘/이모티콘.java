import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int S;
    static boolean[][] visited = new boolean[1001][1001];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(br.readLine());

        bfs();
    }

    static void bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {1, 0, 0}); // 화면, 클립, 시간
        visited[1][0] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int monitor = cur[0];
            int clip = cur[1];
            int time = cur[2];

            if(monitor == S) {
                System.out.println(time);
                return;
            }

            // 1번 연산
            if(monitor > 0 && monitor < 1001) {
                if(!visited[monitor][monitor]) {
                    q.offer(new int[] {monitor, monitor, time + 1});
                    visited[monitor][monitor] = true;
                }
            }

            // 2번 연산
            if(clip > 0) {
                int n = monitor + clip;
                if(n < 1001 && !visited[n][clip]) {
                    q.offer(new int[] {n, clip, time + 1});
                    visited[n][clip] = true;
                }
            }

            // 3번 연산
            if(monitor > 0) {
                int nm = monitor - 1;
                if(!visited[nm][clip]) {
                    visited[nm][clip] = true;
                    q.offer(new int[] {nm, clip, time + 1});
                }
            }
        }
    }
}
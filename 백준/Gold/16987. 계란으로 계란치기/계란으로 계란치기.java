import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, max;
    static int[][] aggs;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        aggs = new int[N][2];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            aggs[i][0] = Integer.parseInt(st.nextToken());
            aggs[i][1] = Integer.parseInt(st.nextToken());
        }

        max = 0;
        dfs(0);

        System.out.println(max);
    }

    static void dfs(int idx) {
        if(idx == N) {
            int cnt = 0;
            for(int i = 0; i < N; i++) if(aggs[i][0] <= 0) cnt++;
            
            max = Math.max(max, cnt);
            return;
        }

        if(aggs[idx][0] < 0) { // 손에 든 계란이 깨짐, 다음 계란
            dfs(idx + 1);
            return;
        }

        boolean isHit = false; // 계란 쳤는지 여부
        for(int i = 0; i < N; i++) {
            if(i == idx || aggs[i][0] <= 0) continue;

            isHit = true;
            
            aggs[idx][0] -= aggs[i][1];
            aggs[i][0] -= aggs[idx][1];

            dfs(idx + 1);

            aggs[idx][0] += aggs[i][1];
            aggs[i][0] += aggs[idx][1];
        }

        if(!isHit) dfs(idx + 1); // 깨지지 않은 다른 계란이 없으면, 다음 계란
    }
}
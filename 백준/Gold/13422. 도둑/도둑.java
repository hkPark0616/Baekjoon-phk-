import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            int[] houses = new int[N];
            long[] prefixSum = new long[N + 1];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                houses[i] = Integer.parseInt(st.nextToken());
            }

            // 집 길이와 연속된 집 길이 같을때
            if (N == M) {
                long sum = 0;
                for (int h : houses) sum += h;
                sb.append(sum < K ? 1 : 0).append('\n');
                continue;
            }

            int cnt = 0;
            long sum = 0;
            for(int i = 0; i < M; i++) {
                sum += houses[i];
            }
            cnt = sum < K ? 1 : 0;

            for(int i = 1; i < N; i++) {
                sum -= houses[i - 1];
                sum += houses[(i + M - 1) % N];
                if(sum < K) cnt++;
            }
        
            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}
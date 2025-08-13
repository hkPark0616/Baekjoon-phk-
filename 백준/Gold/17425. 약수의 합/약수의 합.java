import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        int MAX = 1000000;
        long[] sum = new long[MAX + 1];

        // i는 j의 약수
        // i의 배수들에 합을 저장
        for(int i = 1; i <= MAX; i++) {
            for(int j = i; j <= MAX; j += i) {
                sum[j] += i;
            }
        }

        long[] prefixSum = new long[MAX + 1];
        for(int i = 1; i <= MAX; i++) {
            prefixSum[i] = prefixSum[i - 1] + sum[i];
        }
        
        for(int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(prefixSum[N]).append("\n");
        }

        System.out.println(sb);
    }
}
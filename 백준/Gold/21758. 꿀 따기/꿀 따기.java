import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());

        int[] honey = new int[N];
        long[] sum = new long[N];

        st = new StringTokenizer(br.readLine());
        honey[0] = Integer.parseInt(st.nextToken());
        sum[0] = honey[0];
        for(int i = 1; i < N; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + honey[i];
        }

        long totalSum = sum[N - 1];

        // 왼쪽 끝 시작, 꿀통 오른쪽 끝
        long maxSum1 = 0;
        for(int i = 1; i < N - 1; i++) {
            long curSum = (totalSum - honey[0]) + (totalSum - sum[i]) - honey[i];
            maxSum1 = Math.max(maxSum1, curSum);
        }

        // 오른쪽 끝 시작, 꿀통 왼쪽 
        long maxSum2 = 0;
        for(int i = 1; i < N - 1; i++) {
            long curSum = (totalSum - honey[N - 1]) + sum[i - 1] - honey[i];
            maxSum2 = Math.max(maxSum2, curSum);
        }

        int maxMid = 0;
        for (int i = 1; i < N - 1; i++) {
            if (honey[i] > maxMid) maxMid = honey[i];
        }
        long maxSum3 = totalSum - honey[0] - honey[N - 1] + maxMid;

        long answer = Math.max(maxSum1, Math.max(maxSum2, maxSum3));

        System.out.println(answer);
    }
}
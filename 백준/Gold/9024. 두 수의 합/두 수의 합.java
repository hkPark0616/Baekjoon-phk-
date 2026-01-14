import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, K;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[n];
            
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

            Arrays.sort(arr);
            
            int left = 0;
            int right = n - 1;
            int minDiff = Integer.MAX_VALUE;
            int count = 0;

            while(left < right) {
                int sum = arr[left] + arr[right];
                int diff = Math.abs(K - sum);

                if(diff < minDiff) {
                    minDiff = diff;
                    count = 1;
                } else if(diff == minDiff) {
                    count++;
                }

                if(sum < K) left++;
                else right--;
            }

            sb.append(count).append("\n");
        }
        
        System.out.println(sb);
    }
}
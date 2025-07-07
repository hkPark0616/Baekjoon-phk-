import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        
        while(right < N) {
            sum += arr[right];

            while(sum >= S) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= arr[left];
                left++;
            }

            right++;
        }
        
        System.out.println(minLen == Integer.MAX_VALUE ? 0 : minLen);
    }
}
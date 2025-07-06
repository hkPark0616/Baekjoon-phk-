import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
        }

        Arrays.sort(arr);

        int left = 0;
        int right = 0;
        int minDiff = Integer.MAX_VALUE;

        while(right < N) {
            int diff = arr[right] - arr[left];

            if(left == right) {
                right++;
                continue;
            }

            if(diff >= M) {
                minDiff = Math.min(minDiff, diff);
                left++;
            } else {
                right++;
            }
        }

        System.out.println(minDiff);
    }
}
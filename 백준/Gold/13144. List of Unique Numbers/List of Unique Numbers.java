import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] cnt = new int[100001];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int l = 0;
        long answer = 0;
        for(int r = 0; r < N; r++) {
            cnt[arr[r]]++;

            while(cnt[arr[r]] > 1) { // 중복
                cnt[arr[l]]--;
                l++;
            }

            answer += (r - l + 1); // 길이 누적
        }

        System.out.println(answer);
    }
}
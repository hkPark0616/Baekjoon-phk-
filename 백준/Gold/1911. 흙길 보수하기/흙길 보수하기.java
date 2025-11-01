import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());;

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][2];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            arr[i][0] = s;
            arr[i][1] = e;
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int cnt = 0;
        int pos = 0; // 마지막으로 덮은 널빤지 위치

        for(int i = 0; i < N; i++) {
            int start = arr[i][0];
            int end = arr[i][1];

            // 이미 덮인 곳 -> 덮은 끝지점부터 시작
            if(pos < start) {
                pos = start;
            }

            // 덮기
            while(pos < end) {
                pos += L;
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, L, R, X, answer;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        answer = 0;
        arr = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, 0, Integer.MAX_VALUE, 0);

        System.out.println(answer);
    }

    // 캠프에 사용할 문제는 두 문제 이상
    // 문제 난이도의 합은 L보다 크거나 같고, R보다 작거나 같아야 한다
    // 가장 어려운 문제와 가장 쉬운 문제의 난이도 차이는 X보다 크거나 같아야 한다.
    static void dfs(int idx, int cnt, int sum, int min, int max) {
        if(idx == N) {
            if(cnt >= 2 && sum >= L && sum <= R && (max - min) >= X) {
                answer++;
            }
            return;
        }

        dfs(idx + 1, cnt + 1, sum + arr[idx], Math.min(min, arr[idx]), Math.max(max, arr[idx]));

        dfs(idx + 1, cnt, sum, min, max);
    }
}
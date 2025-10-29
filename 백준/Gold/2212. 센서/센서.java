import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        
        int[] diff = new int[N - 1]; // 각 인접 센서간의 거리
        for(int i = 0; i < N - 1; i++) diff[i] = arr[i + 1] - arr[i];

        Arrays.sort(diff);

        int answer = 0;
        // K개의 집중국으로 나누기 위해 가장 큰 (K-1)간격을 제외한 거리들의 합
        for(int i = 0; i < N - K; i++) {
            answer += diff[i];
        }

        System.out.println(answer);
    }

}
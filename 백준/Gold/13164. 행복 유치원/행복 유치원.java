import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());;

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        
        Arrays.sort(arr);
        
        // 인접한 학생들끼리의 키 차이
        // 어디서 그룹을 나눌지에 대한 기준
        int[] diff = new int[N - 1];
        for (int i = 0; i < N - 1; i++) diff[i] = arr[i + 1] - arr[i];

        Arrays.sort(diff);
        
        long answer = 0;
        for(int i = 0; i < N - K; i++) {
            answer += diff[i];
        }

        System.out.println(answer);
    }
}
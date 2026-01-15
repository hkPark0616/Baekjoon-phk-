import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int left = 0, right = N - 1;
        int answer = 0;

        while(left < right) {
            int cnt = right - left - 1;
            int min = Math.min(arr[left], arr[right]);
            int teamStat = cnt * min;

            answer = Math.max(answer, teamStat);

            // 팀의 능력치가 높아지려면, 최소 능력치를 높이는 쪽으로
            if(arr[left] < arr[right]) left++;
            else right--;
        }

        System.out.println(answer);
    }
}
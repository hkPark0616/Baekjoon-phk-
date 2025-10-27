import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            String[] nums = new String[N];

            for(int i = 0; i < N; i++) nums[i] = br.readLine();

            Arrays.sort(nums); // 짧은 번호를 먼저 삽입하기 위해서

            boolean answer = true;
            for (int i = 0; i < N - 1; i++) {
                if(nums[i + 1].startsWith(nums[i])) {
                    answer = false;
                    break;
                }
            }

            sb.append(answer ? "YES\n" : "NO\n");
        }

        System.out.println(sb);
    }
}
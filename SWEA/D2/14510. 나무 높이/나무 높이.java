import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[] tree = new int[N];
            int max = 0;
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                tree[i] = Integer.parseInt(st.nextToken());
                max = Math.max(max, tree[i]);
            }

            int answer = 0;
            int total = 0;
            int oddCnt = 0;

            for(int i = 0; i < N; i++){
                int diff = max - tree[i];

                if(diff % 2 == 1) oddCnt++;

                total += diff;
            }

            if((oddCnt - 1) * 3 + 1 >= total){
                answer = oddCnt * 2 - 1;
            }else{
                answer = (total / 3) * 2 + total % 3;
            }

            System.out.println("#" + t + " " + answer);
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, answer;
    static int[][] houses;
    static int startX, startY, endX, endY;
    static int[] arr;
    static boolean[] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            houses = new int[N][2];
            arr = new int[N];
            isSelected = new boolean[N];
            answer = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());

            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());

            for(int i = 0; i < N; i++){
                houses[i][0] = Integer.parseInt(st.nextToken());
                houses[i][1] = Integer.parseInt(st.nextToken());
            }

            permutation(0);

            System.out.println("#" + t + " " + answer);
        }
    }

    static void permutation(int cnt){
        if(cnt == N){
            int result = 0;

            // 회사에서 첫번째 고객까지
            result += Math.abs(startX - houses[arr[0]][0]) + Math.abs(startY - houses[arr[0]][1]);

            // 고객의 좌표
            for(int i = 0; i < N - 1; i++){
                int customer = arr[i];
                int nextCustomer = arr[i + 1];

                result += Math.abs(houses[customer][0] - houses[nextCustomer][0]) + Math.abs(houses[customer][1] - houses[nextCustomer][1]);
            }
            
            // 마지막 고객의 좌표에서 집까지
            result += Math.abs(houses[arr[N - 1]][0] - endX) + Math.abs(houses[arr[N - 1]][1] - endY);

            answer = Math.min(answer, result);

            return;
        }

        for(int i = 0; i < N; i++){
            if(!isSelected[i]){
                isSelected[i] = true;
                arr[cnt] = i;
                permutation(cnt + 1);
                isSelected[i] = false;
            }
        }
    }
}
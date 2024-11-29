import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N, answer;
    static int[][] house;
    static int[] arr;
    static boolean[] selected;
    static int startX, startY, endX, endY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            answer = Integer.MAX_VALUE;
            house = new int[N][2];
            arr = new int[N];
            selected = new boolean[N];

            st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());

            for(int i = 0; i < N; i++){
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                house[i][0] = a;
                house[i][1] = b;
            }



            perm(0);

            System.out.println("#" + t + " " + answer);
        }
    }

    static void perm(int cnt){
        if(cnt == N){
            int result = 0;

            result += Math.abs(startX - house[arr[0]][0]) + Math.abs(startY - house[arr[0]][1]);
            for(int i = 0; i < N - 1; i++){
                result += Math.abs(house[arr[i]][0]- house[arr[i + 1]][0]) + Math.abs(house[arr[i]][1] - house[arr[i + 1]][1]);
            }
            result += Math.abs(endX - house[arr[N - 1]][0]) + Math.abs(endY- house[arr[N - 1]][1]);

            answer = Math.min(result, answer);

            return;
        }

        for(int i = 0; i < N; i++){
            if(!selected[i]){
                selected[i] = true;
                arr[cnt] = i;
                perm(cnt + 1);
                selected[i] = false;
            }
        }
    }
}
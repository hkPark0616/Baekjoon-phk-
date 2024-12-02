import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
    static int N;
    static int[][] position;
    static boolean[] selected;
    static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            position = new int[N][2];
            selected = new boolean[N];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                position[i][0] = x;
                position[i][1] = y;
            }

            answer = Long.MAX_VALUE;

            combination(0, 0);

            System.out.println("#" + t + " " + answer);
        }
    }

    static void combination(int start, int cnt){
        if(cnt == N / 2){
            vectorCalc();
            return;
        }

        for(int i = start; i < N; i++){
            selected[i] = true;
            combination(i + 1, cnt + 1);
            selected[i] = false;
        }
    }

    static void vectorCalc(){
        long sumX = 0;
        long sumY = 0;


        for(int i = 0; i < N; i++){
            if(selected[i]){
                sumX += position[i][0];
                sumY += position[i][1];
            }else{
                sumX -= position[i][0];
                sumY -= position[i][1];
            }
        }

        long result = sumX * sumX + sumY * sumY;

        answer = Math.min(answer, result);
    }
}
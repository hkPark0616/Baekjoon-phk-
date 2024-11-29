import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static long answer;
    static int[][] pos;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            answer = Long.MAX_VALUE;
            pos = new int[N][2];
            selected = new boolean[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                pos[i][0] = x;
                pos[i][1] = y;
            }

            comb(0, 0);

            System.out.println("#" + t + " " + answer);
        }
    }

   static void comb(int start, int count) {
        if (count == N / 2) {
            calc();
            return;
        }

        for (int i = start; i < N; i++) {
            selected[i] = true;
            comb(i + 1, count + 1);
            selected[i] = false;
        }
    }

    static void calc() {
        long sumX = 0;
        long sumY = 0;

        for (int i = 0; i < N; i++) {
            if (selected[i]) {
                sumX += pos[i][0];
                sumY += pos[i][1];
            } else {
                sumX -= pos[i][0];
                sumY -= pos[i][1];
            }
        }

        long result = sumX * sumX + sumY * sumY;

        answer = Math.min(answer, result);
    }
}
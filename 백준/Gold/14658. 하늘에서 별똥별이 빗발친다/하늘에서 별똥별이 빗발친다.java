import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, L, K;
    static int[][] stars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stars = new int[K][2];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars[i][0] = x;
            stars[i][1] = y;
        }

        int maxCovered = 0;

        // 모든 별을 기준으로 LxL 트램펄린 왼쪽 아래 좌표를 잡는다
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                int left = stars[i][0]; // 왼쪽 기준
                int bottom = stars[j][1]; // 아래쪽 기준

                int count = 0;
                for (int k = 0; k < K; k++) {
                    int x = stars[k][0];
                    int y = stars[k][1];
                    // 현재 트램펄린 위치에 대해서 모든 별이 범위에 포함되는지 체크
                    if (x >= left && x <= left + L && y >= bottom && y <= bottom + L) {
                        count++;
                    }
                }

                maxCovered = Math.max(maxCovered, count);
            }
        }

        System.out.println(K - maxCovered);
    }
}
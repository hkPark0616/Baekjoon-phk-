import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        char[][] board = new char[N][M];
        int[][] diffB = new int[N][M]; // 좌상단 검정 패턴(같으면 0, 다르면 1)
        int[][] diffW = new int[N][M]; // 좌상단 흰색 패턴(같으면 0, 다르면 1)
        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                char startB = ((i + j) % 2 == 0) ? 'B' : 'W'; // B로 시작하는 패턴
                char startW = ((i + j) % 2 == 0) ? 'W' : 'B'; // W로 시작하는 패턴

                diffB[i][j] = (board[i][j] == startB) ? 0 : 1;
                diffW[i][j] = (board[i][j] == startW) ? 0 : 1;
            }
        }

        int[][] prefixSumB = new int[N + 1][M + 1];
        int[][] prefixSumW = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                prefixSumB[i][j] = prefixSumB[i - 1][j] + prefixSumB[i][j - 1] - prefixSumB[i - 1][j - 1] + diffB[i - 1][j - 1];
                prefixSumW[i][j] = prefixSumW[i - 1][j] + prefixSumW[i][j - 1] - prefixSumW[i - 1][j - 1] + diffW[i - 1][j - 1];
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int r = 0; r <= N - K; r++) {
            for(int c = 0; c <= M - K; c++) {
                // r, c 에서 K * K
                int r2 = r + K - 1;
                int c2 = c + K - 1;

                int B = prefixSumB[r2 + 1][c2 + 1] - prefixSumB[r][c2 + 1] - prefixSumB[r2 + 1][c] + prefixSumB[r][c];
                int W = prefixSumW[r2 + 1][c2 + 1] - prefixSumW[r][c2 + 1] - prefixSumW[r2 + 1][c] + prefixSumW[r][c];

                int min = Math.min(B, W);

                answer = Math.min(answer, min);
            }
        }

        System.out.println(answer);
    }
}
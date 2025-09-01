import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int M, N;
    static int[][] map;
    static int[][] deltas = {{0, -1}, {-1, -1}, {-1, 0}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][M];

        for(int i = 0; i < M; i++) Arrays.fill(map[i], 1);

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dayGrow(a, b, c);
        }
        
        grow();
        printMap();
    }

    static void printMap() {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                sb.append(map[i][j]);
                if(j < M - 1) sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    // 0, 1, 2
    static void dayGrow(int a, int b, int c) {
        for(int i = M - 1; i >= 0; i--) {
            if(a > 0) {
                a--;
            } else if(b > 0) {
                b--;
                map[i][0] += 1;
            } else if(c > 0) {
                c--;
                map[i][0] += 2;
               
            }
        }

        for(int i = 1; i < M; i++) {
            if(a > 0) {
                a--;
            } else if(b > 0) {
                b--;
                map[0][i] += 1;
            } else if(c > 0) {
                c--;
                map[0][i] += 2;
            }
        }
    }

    static void grow() {
        for(int i = 1; i < M; i++) {
            for(int j = 1; j < M; j++) {
                int maxGrow = 0;

                for(int[] delta: deltas) {
                    int ni = i + delta[0];
                    int nj = j + delta[1];

                    maxGrow = Math.max(maxGrow, map[ni][nj]);
                }

                map[i][j] = maxGrow;
            }
        }
    }
}
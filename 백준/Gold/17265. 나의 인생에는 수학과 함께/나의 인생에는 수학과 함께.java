import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static char[][] map;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int[][] deltas = {{1, 0}, {0, 1}}; // 하, 우
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        dfs(0, 0, map[0][0] - '0', ' ');

        System.out.println(max + " " + min);
    }

    static void dfs(int x, int y, int cal, char op) {
        if(x == N - 1 && y == N - 1) {
            min = Math.min(min, cal);
            max = Math.max(max, cal);
            return;
        }

        for(int[] delta: deltas) {
            int nx = x + delta[0];
            int ny = y + delta[1];

            if(nx >= N || ny >= N) continue;

            char c = map[nx][ny];
            if(c == '+' || c == '-' || c == '*') {
                dfs(nx, ny, cal, c); // 연산자 저장
            } else {
                int result = calc(cal, c - '0', op);
                dfs(nx, ny, result, ' '); // 연산자 사용
            }
        }
    }

    static int calc(int a, int b, char op) {
        if(op == '+') return a + b;
        if(op == '-') return a - b;
        if(op == '*') return a * b;
        return a;
    }
}
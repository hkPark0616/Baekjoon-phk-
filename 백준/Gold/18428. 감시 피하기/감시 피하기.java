import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static char[][] map;
    static List<int[]> empty = new ArrayList<>();
    static List<int[]> teachers = new ArrayList<>();
    static boolean possible = false;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j] == 'X') empty.add(new int[] {i, j});
                else if(map[i][j] == 'T') teachers.add(new int[] {i, j});
            }
        }

        backtrack(0, 0);

        System.out.println(possible ? "YES" : "NO");    
    }

    static void backtrack(int idx, int cnt) {
        if(cnt == 3) {
            if(check()) possible = true;
            return;
        }

        if(idx == empty.size()) return;
        if(possible) return;

        int[] p = empty.get(idx);
        int x = p[0];
        int y = p[1];

        map[x][y] = 'O';
        backtrack(idx + 1, cnt + 1);

        map[x][y] = 'X';
        backtrack(idx + 1, cnt);
    }

    static boolean check() {
        for(int[] teacher: teachers) {
            int x = teacher[0];
            int y = teacher[1];

            for(int[] delta: deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];

                while(nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if(map[nx][ny] == 'O') break;
                    if(map[nx][ny] == 'S') return false;

                    nx += delta[0];
                    ny += delta[1];
                }
            }
        }
        
        return true;
    }
}
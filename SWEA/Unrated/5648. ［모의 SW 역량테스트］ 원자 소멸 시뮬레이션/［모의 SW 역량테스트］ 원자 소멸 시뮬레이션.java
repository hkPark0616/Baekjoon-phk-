import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int N, answer;
    static int[][] map = new int[4001][4001];;
    static boolean[][] visited = new boolean[4001][4001];
    static List<int[]> atoms;
    // 상, 하, 좌, 우
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    // 원자 정보
    static class Atom{
        int x;
        int y;
        int d;
        int k;

        public Atom(int x, int y, int d, int k) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.k = k;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            atoms = new ArrayList<>();
            answer = 0;

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = (Integer.parseInt(st.nextToken()) * 2 + 2000);
                int y = (Integer.parseInt(st.nextToken()) * 2 + 2000);
                int d = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                atoms.add(new int[]{y, x, d, k});
                map[x][y] = k;
            }

            while (atoms.size() != 0) {
                move();
            }

            System.out.println("#" + t + " " + (answer));
        }
    }

    static void move(){
        for(int i = 0; i < atoms.size(); i++) {
            int x = atoms.get(i)[1];
            int y = atoms.get(i)[0];
            map[x][y] = 0;

            atoms.get(i)[0] += deltas[atoms.get(i)[2]][0];
            atoms.get(i)[1] += deltas[atoms.get(i)[2]][1];

            int nx = atoms.get(i)[1];
            int ny = atoms.get(i)[0];

            if(nx >= 0 && nx < 4001 && ny >= 0 && ny < 4001) {
                map[nx][ny] += atoms.get(i)[3];
            }else{
                atoms.remove(i);
                i--;
            }
        }

        for(int i = 0; i < atoms.size(); i++) {
            int x = atoms.get(i)[1];
            int y = atoms.get(i)[0];

            if(visited[x][y]) {
                map[x][y] -= atoms.get(i)[3];
                answer += atoms.get(i)[3];
                atoms.remove(i);
                i--;
                if(map[x][y] == 0) {
                    visited[x][y] = false;
                }
            }else if(map[x][y] > atoms.get(i)[3] && !visited[x][y]) {
                visited[x][y] = true;
                map[x][y] -= atoms.get(i)[3];
                answer += atoms.get(i)[3];
                atoms.remove(i);
                i--;
            }
        }
    }
}
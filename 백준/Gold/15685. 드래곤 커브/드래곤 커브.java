import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static boolean[][] matrix;
    static List<DragonCurve> list = new ArrayList<>();
    static int N;
    static int[][] deltas = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    static class DragonCurve {
        int x, y, d, g;

        public DragonCurve(int x, int y, int d, int g) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.g = g;
        }
    }
    
    // 0 : 오른쪽, 1 : 위쪽, 2 : 왼쪽, 3 : 아래쪽
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        matrix = new boolean[101][101];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            list.add(new DragonCurve(x, y, d, g));
        }

        for(DragonCurve dc: list) {
            drawDragonCurve(dc.x, dc.y, dc.d, dc.g);
        }

        System.out.println(calc());
    }

    static void drawDragonCurve(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);

        // g번 세대까지의 드래곤커브의 방향을 미리 저장해두고 이동할때 사용
        for(int i = 0; i < g; i++) {
            for(int j = directions.size() - 1; j >= 0; j--) { // 끝점에서 방향 전환이어서 거꾸로 함
                int nextDir = (directions.get(j) + 1) % 4;
                directions.add(nextDir);
            }
        }

        // 그리기
        matrix[x][y] = true;
        for(int dir: directions) {
            x += deltas[dir][0];
            y += deltas[dir][1];
            if(x >= 0 && x <= 100 && y >= 0 && y <= 100) {
                matrix[x][y] = true;
            }
        }
    }

    static int calc() {
        int cnt = 0;

        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                if(matrix[i][j] && matrix[i + 1][j] && matrix[i][j + 1] && matrix[i + 1][j + 1]) {
                    cnt++;
                }
            }
        }
        
        return cnt;
    }
}
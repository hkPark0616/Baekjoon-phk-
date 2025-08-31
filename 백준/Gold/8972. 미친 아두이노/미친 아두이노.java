import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int R, C;
    static int jx, jy;
    static List<int[]> arduino = new ArrayList<>();
    static char[][] map;
    static char[] strArr;
    static int[][] deltas = {{0, 0}, {1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 0}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i = 0; i < R; i++) {
            String s = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'I') {
                    jx = i;
                    jy = j;
                } else if(map[i][j] == 'R') {
                    arduino.add(new int[] {i, j});
                }
            }
        }

        strArr = br.readLine().toCharArray();

        for(int i = 0; i < strArr.length; i++) {

            if (moveJongsu(strArr[i] - '0')) {
                System.out.println("kraj " + (i + 1));
                return;
            }

            if(moveArduino()) {
                System.out.println("kraj " + (i + 1));
                return;
            }
        }
        
        print();
    }

    static void print() {
        for(int i = 0; i < R; i++) Arrays.fill(map[i], '.');

        map[jx][jy] = 'I';

        for(int[] a: arduino) map[a[0]][a[1]] = 'R';

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static boolean moveJongsu(int dir) {
        int nx = jx + deltas[dir][0];
        int ny = jy + deltas[dir][1];

        if(map[nx][ny] == 'R') return true;

        map[jx][jy] = '.';
        map[nx][ny] = 'I';
        jx = nx;
        jy = ny;
        
        return false;
    }

    static boolean moveArduino() {
        int[][] arduinoCnt = new int[R][C];
        List<int[]> next = new ArrayList<>();
        for(int i = 0; i < arduino.size(); i++) {
            int x = arduino.get(i)[0];
            int y = arduino.get(i)[1];
            int minDir = 5;
            int minDist = getDistance(x, y);
            
            for(int d = 1; d < deltas.length; d++) {
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];
                
                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;

                int nd = getDistance(nx, ny);
                if(nd < minDist) {
                    minDist = nd;
                    minDir = d;
                }
            }

            int nx = x + deltas[minDir][0];
            int ny = y + deltas[minDir][1];
    
            if(nx == jx && ny == jy) return true;

            arduinoCnt[nx][ny]++;
            next.add(new int[] {nx, ny});
        }

        List<int[]> arduino2 = new ArrayList<>(); // 안터진 아두이노 목록
        for(int[] n: next) if(arduinoCnt[n[0]][n[1]] == 1) arduino2.add(n);

        arduino = arduino2;

        return false;
    }

    static int getDistance(int r2, int s2) {
        return Math.abs(jx - r2) + Math.abs(jy - s2);
    }
}
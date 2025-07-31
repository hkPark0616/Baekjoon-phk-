import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, answer;
    static int rotateX, rotateY;
    static int dir = 0; 
    static int step = 1;
    static int stepCount, dirCount;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] spreadX = {
        {-1, 1, -1, 1, -2, 2, -1, 1, 0}, // 좌
        {-1, -1, 0, 0, 0, 0, 1, 1, 2},   // 하
        {-1, 1, -1, 1, -2, 2, -1, 1, 0}, // 우
        {1, 1, 0, 0, 0, 0, -1, -1, -2}  // 상
    };
    static int[][] spreadY = {
        {1, 1, 0, 0, 0, 0, -1, -1, -2},  // 좌
        {-1, 1, -1, 1, -2, 2, -1, 1, 0}, // 하
        {-1, -1, 0, 0, 0, 0, 1, 1, 2},   // 우
        {-1, 1, -1, 1, -2, 2, -1, 1, 0}  // 상
    };
    static int[] sandPercent = {1, 1, 7, 7, 2, 2, 10, 10, 5}; // 비율
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotateX = rotateY = N / 2;
        while (!(rotateX == 0 && rotateY == 0)) {
            moveNext();
            spreadSand();
            updateDirection();
        }

        System.out.println(answer);
    }

    static void moveNext() {
        rotateX += dx[dir];
        rotateY += dy[dir];
    }

    static void updateDirection() {
        stepCount++;
        if (stepCount == step) {
            dir = (dir + 1) % 4;
            dirCount++;
            stepCount = 0;
            if (dirCount == 2) {
                step++;
                dirCount = 0;
            }
        }
    }

    static void spreadSand() {
        int curSand = map[rotateX][rotateY];
        map[rotateX][rotateY] = 0;

        int outSand = 0;
        int totalSpreadSand = 0;

        for(int i = 0; i < 9; i++) {
            int nx = rotateX + spreadX[dir][i];
            int ny = rotateY + spreadY[dir][i];

            int sandAmount = curSand * sandPercent[i] / 100;
            totalSpreadSand += sandAmount;

            if(nx < 0 || nx >= N || ny < 0 || ny >= N ) {
                outSand += sandAmount;
            } else {
                map[nx][ny] += sandAmount;
            }
        }

        int ax = rotateX + dx[dir];
        int ay = rotateY + dy[dir];

        int remainSand = curSand - totalSpreadSand;

        if(ax < 0 || ax >= N || ay < 0 || ay >= N ) {
            outSand += remainSand;
        } else {
            map[ax][ay] += remainSand;
        }
        
        answer += outSand;
    }
}
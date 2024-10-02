import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, map[][], answer;
    static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T  = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(map[i][j] == 0){
                        for(int d = 0; d < 4; d++){
                            play(i, j, d);
                        }
                    }
                }
            }

            System.out.println("#" + t + " " + answer);
        }
    }

    static void play(int startX, int startY, int dir){
        int x = startX;
        int y = startY;
        int score = 0;

        while(true){
            x += deltas[dir][0];
            y += deltas[dir][1];

            if(x < 0 || x >= N || y < 0 || y >= N){
                dir = (dir + 2) % 4;
                score++;
                continue;
            }

            if((x == startX && y == startY) || map[x][y] == -1){
                answer = Math.max(answer, score);
                return;
            }

            if(map[x][y] >= 1 && map[x][y] <= 5){
                dir = changeDirection(dir, map[x][y]);
                score++;
            }

            if(map[x][y] >= 6 && map[x][y] <= 10){
                int[] pos = findOut(x, y, map[x][y]);
                x = pos[0];
                y = pos[1];
            }
        }
    }

    static int[] findOut(int x, int y, int num){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == num && (i != x || j != y)){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{x, y};
    }

    static int changeDirection(int dir, int block){
        switch (block){
            case 1:
                if(dir == 0) return 2;
                if(dir == 1) return 3;
                if(dir == 2) return 1;
                if(dir == 3) return 0;
                break;
            case 2:
                if(dir == 0) return 1;
                if(dir == 1) return 3;
                if(dir == 2) return 0;
                if(dir == 3) return 2;
                break;
            case 3:
                if(dir == 0) return 3;
                if(dir == 1) return 2;
                if(dir == 2) return 0;
                if(dir == 3) return 1;
                break;
            case 4:
                if(dir == 0) return 2;
                if(dir == 1) return 0;
                if(dir == 2) return 3;
                if(dir == 3) return 1;
                break;
            case 5:
                return (dir + 2) % 4;
        }
        return dir;
    }
}

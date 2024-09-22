import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R, map[][], scores;
    static String[][] status;
    static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        status = new String[N][M];
        scores = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                status[i][j] = "S";
            }
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            int attackX = Integer.parseInt(st.nextToken()) - 1;
            int attackY = Integer.parseInt(st.nextToken()) - 1;
            String dir = st.nextToken();
            switch (dir){
                case "N":
                    attack(attackX, attackY, 0);
                    break;
                case "E":
                    attack(attackX, attackY, 1);
                    break;
                case "S":
                    attack(attackX, attackY, 2);
                    break;
                case "W":
                    attack(attackX, attackY, 3);
                    break;
            }
            
            st = new StringTokenizer(br.readLine());
            int defendX = Integer.parseInt(st.nextToken());
            int defendY = Integer.parseInt(st.nextToken());
            defend(defendX - 1, defendY - 1);
        }

        System.out.println(scores);
        print();
    }

    static void attack(int a, int b, int d){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {a, b, map[a][b]});

        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            int x = temp[0];
            int y = temp[1];
            int h = temp[2];

            if(map[x][y] == 0) continue;

            map[x][y] = 0;
            status[x][y] = "F";
            scores++;

            int nx = x;
            int ny = y;
            for(int i = 1; i < h; i++){
                nx += deltas[d][0];
                ny += deltas[d][1];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(map[nx][ny] != 0){
                    status[nx][ny] = "F";
                    queue.offer(new int[] {nx, ny, map[nx][ny]});
                }
            }
        }
    }

    static void defend(int x, int y){
        if(status[x][y].equals("F")){
            status[x][y] = "S";
        }
    }

    static void print(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                System.out.print(status[i][j] + " ");
            }
            System.out.println();
        }
    }
}

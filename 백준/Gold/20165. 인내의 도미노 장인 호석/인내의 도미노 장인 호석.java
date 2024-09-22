import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                status[i][j] = "S";
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
        if (status[a][b].equals("F")) { // 이미 방문한 칸이면 종료
            return;
        } else {
            int size = map[a][b] - 1;
            status[a][b] = "F";
            scores++;

            while (size > 0) {
                int nx = a + deltas[d][0];
                int ny = b + deltas[d][1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    return;
                }
                size--;
                a = nx;
                b = ny;

                if (status[nx][ny].equals("F")) {
                    continue;
                }
                // 안넘어뜨린칸
                status[nx][ny] = "F";

                // 새로 방문한 곳과 기존 도미노 사이즈 비교
                size = Math.max(size, map[nx][ny] - 1);

                //방문 횟수 증가
                scores++;
            }
        }
    }

    static void defend(int x, int y){
        if(status[x][y].equals("F")){
            status[x][y] = "S";
        }else{
            return;
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
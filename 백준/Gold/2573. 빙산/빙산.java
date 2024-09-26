import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, map[][], cnt;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int years = 0;
        while(true){
            visited = new boolean[N][M];
            cnt = 0;

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] != 0 && !visited[i][j]) {
                        bfs(i, j);
                        cnt++;
                    }
                }
            }
            if(cnt >= 2) {
                System.out.println(years);
                break;
            }

            if(check()){
                if(cnt < 2){
                    System.out.println(0);
                }
                    break;
            }


            melt();
            years++;
        }
    }
    static void print(){
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean check(){
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != 0) return false;
            }
        }
        return true;
    }
    static void melt(){
        int[][] loc = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                int seas = 0;
                if(map[i][j] != 0) {
                    for(int d = 0; d < 4; d++) {
                        int nx = i + deltas[d][0];
                        int ny = j + deltas[d][1];
                        if(nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 0) {
                            seas++;
                        }
                    }
                    loc[i][j] = seas;
                }
            }
        }



        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != 0) {
                    map[i][j] -= loc[i][j];
                    if(map[i][j] <= 0) {
                        map[i][j] = 0;
                    }
                }
            }
        }
    }

    static void bfs(int a, int b){
        Queue<int[]> queue = new LinkedList<>();
        visited[a][b] = true;
        queue.offer(new int[] {a, b});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for(int i = 0; i < 4; i++){
                int nx = x + deltas[i][0];
                int ny = y + deltas[i][1];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && map[nx][ny] != 0) {
                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny});
                }
            }
        }
    }
}

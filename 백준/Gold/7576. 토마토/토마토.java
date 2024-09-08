import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M, N, box[][], answer;
    static boolean visited[][];
    static Queue<int[]> queue = new LinkedList<>();
    static int[] delX = {-1, 0, 1, 0}, delY = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        answer = -1;

        box = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] == 1){
                    queue.offer(new int[]{i, j, 0});
                }
            }
        }

        bfs();

        if(!check())
            answer = -1;

        System.out.println(answer);
    }

    static void bfs(){
        int cnt = 0;
        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            int x = temp[0], y = temp[1];
            cnt = temp[2];

            visited[x][y] = true;

            for(int d = 0; d < 4; d++){
                int nx = x + delX[d];
                int ny = y + delY[d];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M && box[nx][ny] == 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    box[nx][ny] = 1;
                    queue.add(new int[]{nx, ny, cnt + 1});
                }
            }
        }

        answer = cnt;
    }

    static boolean check(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(box[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}

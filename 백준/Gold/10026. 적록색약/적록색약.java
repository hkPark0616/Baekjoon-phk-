import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, cnt1, cnt2;
    static char[][] arr;
    static boolean[][] visited;
    static int[] delX = {-1, 0, 1, 0}, delY = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        cnt1 = 0;
        cnt2 = 0;

        arr = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]){
                    bfs1(i, j);
                    cnt1++;
                }
            }
        }

        visited = new boolean[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(arr[i][j] == 'R') {
                    arr[i][j] = 'G';
                }
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]){
                    bfs2(i, j);
                    cnt2++;
                }
            }
        }


        System.out.println(cnt1 + " " + cnt2);
    }

    static void bfs1(int a, int b){
        Queue<int[]> queue = new LinkedList<>();

        visited[a][b] = true;
        queue.offer(new int[] {a, b});

        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            int x = temp[0];
            int y = temp[1];

            for(int d = 0; d < 4; d++){
                int nx = x + delX[d];
                int ny = y + delY[d];

                if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && arr[nx][ny] == arr[x][y]){
                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny});
                }
            }
        }
    }

    static void bfs2(int a, int b){
        Queue<int[]> queue = new LinkedList<>();

        visited[a][b] = true;
        queue.offer(new int[] {a, b});

        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            int x = temp[0];
            int y = temp[1];

            for(int d = 0; d < 4; d++){
                int nx = x + delX[d];
                int ny = y + delY[d];

                if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && arr[nx][ny] == arr[x][y]){
                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny});
                }
            }
        }
    }
}

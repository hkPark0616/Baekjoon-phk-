import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N, answer, now;
    static int[][] arr;
    static boolean[][] visited;
    static int[] delX = {1, 0, -1, 0}, delY = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            arr = new int[N][N];

            answer = 0;

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int m = 0; m <= 100; m++){
                visited = new boolean[N][N];
                now = 0;

                for(int i = 0; i < N; i++){
                    for(int j = 0; j < N; j++){
                        if(arr[i][j] == m){
                            arr[i][j] = 0;
                        }
                    }
                }

                for(int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (!visited[i][j] && arr[i][j] != 0) {
                            bfs(i, j);
                            now++;
                        }

                    }
                }
                answer = Math.max(answer, now);
            }

            System.out.println("#" + t + " " + answer);
        }
    }

    static void bfs(int a, int b){
        Queue<int []> queue = new LinkedList<>();

        queue.offer(new int []{a, b});
        visited[a][b] = true;

        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            int x = temp[0], y = temp[1];

            for(int d = 0; d < 4; d++){
                int nx = x + delX[d];
                int ny = y + delY[d];

                if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && arr[nx][ny] != 0){
                    visited[nx][ny] = true;
                    queue.offer(new int []{nx, ny});
                }
            }
        }
    }
}

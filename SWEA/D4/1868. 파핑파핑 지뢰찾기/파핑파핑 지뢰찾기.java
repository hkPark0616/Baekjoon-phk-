import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N, answer;
    static char[][] arr;
    static boolean[][] visited;
    static int[] delX = {-1, -1, 0, 1, 1, 1, 0, -1}, delY = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T  = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            answer = Integer.MAX_VALUE;
            int cnt = 0;
            arr = new char[N][N];
            visited = new boolean[N][N];

            for(int i = 0; i < N; i++){
                String line = br.readLine();
                for(int j = 0; j < N; j++){
                    arr[i][j] = line.charAt(j);
                }
            }

            // 일단 지뢰가 있는 부분은 탐색을 못하기 때문에 방문체크
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(arr[i][j] == '*') visited[i][j] = true;
                }
            }

            // '.' 인곳, 방문하지 않은 곳 그리고 주변에 지뢰가 없는 곳
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(arr[i][j] == '.' && !visited[i][j] && check(i, j)){
                        bfs(i, j);
                        cnt++;
                    }
                }
            }

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(!visited[i][j]) cnt++;
                }
            }
//            answer = Math.min(answer, cnt);

            System.out.println("#" + t + " " + cnt);
        }
    }

    static void bfs(int a, int b){
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{a, b});
        visited[a][b] = true;

        while(!queue.isEmpty()){
            int[] temp = queue.poll();

            int x = temp[0];
            int y = temp[1];

            if(!check(x, y)){
                continue;
            }

            for(int d = 0; d < 8; d++){
                int nx = x + delX[d];
                int ny = y + delY[d];

                if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    queue.offer(new int[] {nx, ny});
                }
            }
        }
    }

    static boolean check(int x, int y){
        for(int d = 0; d < 8; d++){
            int nx = x + delX[d];
            int ny = y + delY[d];

            if(nx >= 0 && nx < N && ny >= 0 && ny < N && arr[nx][ny] == '*'){
                return false;
            }
        }
        return true;
    }
}

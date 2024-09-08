import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N, W, H, map[][], arr[], copy[][], max;
    static int[] delX = {-1, 0, 1, 0}, delY = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            copy = new int[H][W];
            for(int i = 0; i < H; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j++){
                    map[i][j] = copy[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            max = Integer.MAX_VALUE;
            arr = new int[N];

            perm(0);

            System.out.println("#" + t + " " + max);
        }
    }

    static void perm(int cnt){
        if(cnt == N){
            play();
            calBricks();
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    map[i][j] = copy[i][j];
                }
            }
            return;
        }

        for(int i = 0; i < W; i++){
            arr[cnt] = i;
            perm(cnt + 1);
        }
    }

    static void play(){
        int r = 0, c = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < H; j++){
                if(map[j][arr[i]] != 0){
                    r = j;
                    c = arr[i];
                    break;
                }
            }

            breakBricks(r, c);
            gravity();
        }
    }

    static void breakBricks(int row, int col){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {row, col, map[row][col]});

        map[row][col] = 0;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int range = cur[2];

            for(int i = 0; i < range; i++){
                for(int d = 0; d < 4; d++){
                    int nr = r + delX[d] * i;
                    int nc = c + delY[d] * i;

                    if(nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] > 0){
                        queue.add(new int[] {nr, nc, map[nr][nc]});
                        map[nr][nc] = 0;
                    }
                }
            }
        }
    }

    static void gravity(){
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < W; i++){
            for(int j = 0; j < H; j++){
                if(map[j][i] != 0){
                    st.push(map[j][i]);
                }
            }

            for(int j = H - 1; j >= 0; j--){
                if(st.isEmpty()){
                    map[j][i] = 0;
                }else{
                    map[j][i] = st.pop();
                }
            }

        }
    }

    static void calBricks(){
        int cnt = 0;
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                if(map[i][j] != 0){
                    cnt++;
                }
            }
        }
        max = Math.min(cnt, max);
    }
}

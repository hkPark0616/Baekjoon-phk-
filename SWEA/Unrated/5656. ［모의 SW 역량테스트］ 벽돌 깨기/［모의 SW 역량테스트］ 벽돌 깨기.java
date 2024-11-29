import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
    static int N, W, H, max;
    static int[][] map, copy;
    static int[] arr;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

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
            arr = new int[N];

            for(int i = 0; i < H; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j++){
                    map[i][j] = copy[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            max = Integer.MAX_VALUE;

            perm(0);
            System.out.println("#" + t + " " + max);
        }
    }

    static void perm(int cnt){
        if(cnt == N){
            play();
            calc();
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

    static void breakBricks(int r, int c){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c, map[r][c]});

        map[r][c] = 0;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int w = cur[2];

            for(int i = 0; i < w; i++){
                for(int d = 0; d < 4; d++){
                    int nx = x + deltas[d][0] * i;
                    int ny = y + deltas[d][1] * i;

                    if(nx >= 0 && nx < H && ny >= 0 && ny < W && map[nx][ny] > 0){
                        queue.add(new int[]{nx, ny, map[nx][ny]});
                        map[nx][ny] = 0;
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

    static void calc(){
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
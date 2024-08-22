import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[][] room;
    static boolean[][] visited;
    static List<Integer> roomCnt;
    static int idx, max;
    static int[] delX = {0, 1, 0, -1}, delY = {1, 0, -1, 0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            room = new int[N][N];
            visited = new boolean[N][N];
            roomCnt = new ArrayList<>();

            idx = 0; // 처음에 출발해야하는 방 번호
            max = 0; // 최대 이동 방 개수

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    room[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    dfs(room[i][j], i, j, 1);
                }
            }

            sb.append("#" + t + " ");
            sb.append(idx + " " + max + "\n");
        }
        System.out.println(sb);
    }

    static void dfs(int start, int x, int y, int depth){

        visited[x][y] = true;

        for(int i = 0; i < 4; i++){
            int nx = x + delX[i];
            int ny = y + delY[i];
            if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

            if(visited[nx][ny]) continue;

            // 이동하려는 방의 숫자가 정확히 1 크면
            if(room[x][y] == room[nx][ny] - 1){
                dfs(start, nx, ny, depth + 1);
            }
        }

        if(depth > max){ // 경로 중 경로의 길이가 가장 긴거
            max = depth; // 가장 긴 경로
            idx = start;
        }

        if(depth == max){ // 방의 개수가 최대인 방이 여럿 있으면, 시작 원소가 가장 작은 값 선택
            idx = Math.min(start, idx);
        }

        visited[x][y] = false;

    }
}
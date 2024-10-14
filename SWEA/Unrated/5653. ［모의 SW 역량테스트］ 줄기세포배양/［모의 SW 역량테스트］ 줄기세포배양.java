import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, K, answer;
    static List<Cell> cells;
    static PriorityQueue<Cell> pq;
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상, 하, 좌, 우

    static class Cell implements Comparable<Cell> {
        int x, y;
        int life;
        int status;
        int time;

        // 0: 죽은 상태 / 1: 활성 상태 / 2: 비활성 상태
        public Cell(int x, int y, int life, int time) {
            this.x = x;
            this.y = y;
            this.life = life;
            this.status = 2;
            this.time = time;
        }

        @Override
        public int compareTo(Cell o) {
            return o.life - this.life;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            cells = new ArrayList<>();
            pq = new PriorityQueue<>();
            visited = new boolean[N + 2 * K][M + 2 * K];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < M; j++) {
                    int n = Integer.parseInt(st.nextToken());
                    if(n != 0){
                        cells.add(new Cell(i + K, j + K, n, n));
                        visited[i + K][j + K] = true;
                    }
                }
            }

            simulation();
            answer = calc();

            System.out.println("#" + t + " " + answer);
        }
    }

    static void simulation() {
        for(int k = 1; k <= K; k++){
            while (!pq.isEmpty()) {
                Cell c = pq.poll();
                int x = c.x;
                int y = c.y;

                if(!visited[x][y]){
                    visited[x][y] = true;
                    cells.add(c);
                }
            }

            for(int i = 0; i < cells.size(); i++){
                Cell c = cells.get(i);

                // 죽은 상태 넘김
                if(c.status == 0) continue;

                // 비활성 상태의 세포가 X 시간이 지났을때
                else if(c.status == 2 && c.time == k){
                    c.status = 1; // 상태 전환
                    c.time = k + c.life; // 현재 시간 보다 X 시간이 지난 후에 죽는 상태로

                    for(int d = 0; d < deltas.length; d++){
                        int nx = c.x + deltas[d][0];
                        int ny = c.y + deltas[d][1];

                        // 새로 생길 세포들은 현재 시간을 기준으로 +1
                        // (현재 셀의 시간에 + 1)
                        pq.add(new Cell(nx, ny, c.life, c.life + k + 1));
                    }
                }
                // 활성 상태 세포가 X 시간이 지났을때 죽은 상태로 전환
                else if(c.status == 1 && c.time == k){
                    c.status = 0;
                    c.time = 0;
                    c.life = 0;
                }
            }
        }
    }

    static int calc() {
        int cnt = 0;
        for(Cell c : cells) {
            if(c.status == 1 || c. status == 2){
                cnt++;
            }
        }
        return cnt;
    }
}
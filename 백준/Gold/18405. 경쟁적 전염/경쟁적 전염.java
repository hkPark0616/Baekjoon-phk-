import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] map;
    static int S, X, Y;
    static Queue<Virus> q;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static class Virus implements Comparable<Virus>{
        int x, y, num, time;

        public Virus(int x, int y, int num, int time){
            this.x = x;
            this.y = y;
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Virus o) {
            return this.num - o.num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        q = new ArrayDeque<>();
        ArrayList<Virus> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    list.add(new Virus(i, j, map[i][j], 0));
                }
            }
        }

        Collections.sort(list, new Comparator<Virus>() {
            @Override
            public int compare(Virus o1, Virus o2) {
                return o1.num - o2.num;
            }
        });

        for (Virus virus : list) {
            q.offer(virus);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        bfs();

        System.out.println(map[X - 1][Y - 1]);
    }

    static void bfs(){
        while (!q.isEmpty()) {
            Virus cur = q.poll();

            int x = cur.x;
            int y = cur.y;
            int num = cur.num;
            int time = cur.time;

            if(time == S) return;

            for(int d = 0; d < 4; d++){
                int nx = x + deltas[d][0];
                int ny = y + deltas[d][1];

                if(nx >= 0 && ny >= 0 && nx < N && ny < N && map[nx][ny] == 0){
                    map[nx][ny] = num;
                    q.offer(new Virus(nx, ny, num, time + 1));
                }
            }
        }
    }
}
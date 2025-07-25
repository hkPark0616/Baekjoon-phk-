import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static char[][] field = new char[12][6];
    static boolean[][] visited;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static class Puyo {
        int x, y;

        public Puyo(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int i = 0; i < 12; i++) {
            String line = br.readLine();
            for(int j = 0; j < 6; j++) {
                field[i][j] = line.charAt(j);
            }
        }

        int cnt = 0;
        while(true) {
            boolean exploded = false;
            visited = new boolean[12][6];

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (field[i][j] != '.') {
                        boolean result = bfs(i, j, field[i][j]); // 해당 위치에서 뿌요 터졌는지 확인
                        if (result) {
                            exploded = true; // 하나라도 터졌다면 true
                        }
                    }
                }
            }

            if(!exploded) break;

            down();
            cnt++;
        }
        
        System.out.println(cnt);
    }

    static boolean bfs(int a, int b, char color) {
        List<Puyo> list = new ArrayList<>();
        ArrayDeque<Puyo> q = new ArrayDeque<>();
        q.offer(new Puyo(a, b));
        list.add(new Puyo(a, b));
        visited[a][b] = true;

        while(!q.isEmpty()) {
            Puyo cur = q.poll();
            int x = cur.x;
            int y= cur.y;

            for(int[] delta: deltas) {
                int nx = x + delta[0];
                int ny = y + delta[1];

                if(nx >= 0 && nx < 12 && ny >= 0 && ny < 6 && !visited[nx][ny] && field[nx][ny] == color) {
                    list.add(new Puyo(nx, ny));
                    q.offer(new Puyo(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        if(list.size() >= 4) {
            pop(list);
            return true;
        }

        return false;
    }

    static void pop(List<Puyo> list) {
        for(Puyo p: list) {
            field[p.x][p.y] = '.';
        }
    }

    static void down() {
        for(int j = 0; j < 6; j++) {
            int empty = 11; // 뿌요를 채워넣을 빈 자리 행 번호

            for(int i = 11; i >= 0; i--) {
                if(field[i][j] != '.') { // 해당 칸에 뿌요가 있으면 아래로 내림
                    char temp = field[i][j];
                    field[i][j] = '.';
                    field[empty][j] = temp;
                    empty--;
                }
            }

            for(int i = empty; i >= 0; i--) {
                field[i][j] = '.';
            }
        }
    }
}
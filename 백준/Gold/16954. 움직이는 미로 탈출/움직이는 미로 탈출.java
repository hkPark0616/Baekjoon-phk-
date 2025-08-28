import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static char[][] board = new char[8][8];
    static int[][] deltas = {{0, 0}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 8; i++) {
            String s = br.readLine();
            for(int j = 0; j < 8; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        System.out.println(bfs() ? 1 : 0);
    }

    static boolean bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {7, 0});

        while(!q.isEmpty()) {
            boolean[][] visited = new boolean[8][8];
            int size = q.size();
            
            for (int s = 0; s < size; s++) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];

                if (board[x][y] == '#') continue;
                
                if(x == 0 && y == 7) return true;

                for(int[] delta: deltas) {
                    int nx = x + delta[0];
                    int ny = y + delta[1];
    
                    if(nx >= 0 && ny >= 0 && nx < 8 && ny < 8 && board[nx][ny] != '#' && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.offer(new int[] {nx, ny});
                    }
                }
            }

            moveWall();
        }
        return false;
    }

    static void moveWall() {
        for(int i = 7; i >= 0; i--) {
            for(int j = 0; j < 8; j++) {
                if(board[i][j] == '#') {
                    board[i][j] = '.';
                    if(i + 1 < 8) board[i + 1][j] = '#';
                }
            }
        }
    }
}
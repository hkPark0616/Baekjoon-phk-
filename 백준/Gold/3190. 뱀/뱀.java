import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K, L, map[][], answer, time;
    static boolean isEnd;
    static Queue<int[]> queue = new LinkedList<>();
    static int a, b;
    static int curDir;
    static int dir[][] = {
            {-1, 0},  // 상
            {0, 1},   // 우
            {1, 0},   // 하
            {0, -1},  // 좌
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        map[0][0] = 2;
        queue.offer(new int[] {0, 0});
        curDir = 1;  // 오른쪽 방향
        isEnd = false;
        time = 0;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1; // 1-based index -> 0-based index
            int c = Integer.parseInt(st.nextToken()) - 1;

            map[r][c] = 1;
        }

        answer = 0;
        a = 0;
        b = 0;
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);

            play(X, C);

            if(isEnd) break;
        }

        if (!isEnd) {
            while (!isEnd) {
                play(Integer.MAX_VALUE, 'X'); // 게임이 끝날 때까지 실행
            }
        }

        System.out.println(answer + 1);
    }

    static void play(int x, char c) {
        while(time < x) {
            if(isEnd) {
                answer = time;
                return;
            }

            // 현재 방향에 따라 이동
            int nx = a + dir[curDir][0];
            int ny = b + dir[curDir][1];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2) {
                isEnd = true;
                answer = time;
                return;
            }

            // 사과가 있는 경우
            if(map[nx][ny] == 1) {
                map[nx][ny] = 2;
                queue.offer(new int[] {nx, ny});
            }
            // 빈 칸일 경우
            else if(map[nx][ny] == 0) {
                map[nx][ny] = 2;
                queue.offer(new int[] {nx, ny});
                int[] tail = queue.poll();
                map[tail[0]][tail[1]] = 0;
            }

            a = nx;
            b = ny;
            time++; // 시간 증가
        }

        // 방향 변경
        changeDirection(c);
    }

    static void changeDirection(char changeD) {
        if(changeD == 'L') {
            curDir = (curDir + 3) % 4;  // 왼쪽으로 90도 회전
        } else if(changeD == 'D') {
            curDir = (curDir + 1) % 4;  // 오른쪽으로 90도 회전
        }
    }
}
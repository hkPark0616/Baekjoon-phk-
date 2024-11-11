import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int answer, x, y, dir;
    // 상 하 좌 우
    static int dx[] = {1, 0, -1, 0};
    static int dy[] = {0, 1, 0, -1};
    static String str;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            str = br.readLine();

            answer = 0;
            move();
            if(answer == -1) {
                System.out.println("#" + tc + " " + "oo" );
            }else {
                System.out.println("#" + tc + " " + answer);
            }
        }
    }

    // 이 작업은 무한히 반복 될 것
    static void move() {
        // 원점
        x = 0;
        y = 0;
        dir = 0;
        for(int i=0;i<4;i++) {
            int startDir = dir;
            command(); // 커맨드 수행
            if(x==0 && y==0) {
                // 원점에 도착
                return;
            }
            if(dir==startDir) {
                // 마지막 명령어 수행 결과가 시작 방향과 같다면 무한
                answer = -1;
                return;
            }
        }
    }

    static void command() {
        for(int i=0;i<str.length();i++) {
            char now = str.charAt(i);
            if(now=='S') {
                x = x + dx[dir];
                y = y + dy[dir];
                int dis = (int) (Math.pow(x, 2) + Math.pow(y, 2));
                answer = Math.max(answer, dis);
            }else if(now=='L') {
                dir = (dir+1)%4;
            }else if(now=='R') {
                dir = (dir+3)%4;
            }
        }
    }
}
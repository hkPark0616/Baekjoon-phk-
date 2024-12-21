import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static List<Fireball>[][] map;
    static int[] delX = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] delY = {0, 1, 1, 1, 0, -1, -1, -1};

    static class Fireball {
        int r, c, m, s, d;

        public Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map[r][c].add(new Fireball(r, c, m, s, d));
        }


        for (int k = 0; k < K; k++) {
            move();
            mergeAndSplit();
        }

        System.out.println(getTotalM());
    }

    static void move(){
        List<Fireball>[][] newMap = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (Fireball fireball : map[i][j]) {
                    int nx = (fireball.r + delX[fireball.d] * fireball.s % N + N) % N;
                    int ny = (fireball.c + delY[fireball.d] * fireball.s % N + N) % N;
                    fireball.r = nx;
                    fireball.c = ny;
                    newMap[nx][ny].add(fireball);
                }
            }
        }

        map = newMap;
    }

    static void mergeAndSplit(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j].size() > 1){
                    int odd = 0; // 방향 짝수
                    int even = 0; // 방향 홀수
                    int totalM = 0; // 총 질량
                    int totalS = 0; // 총 속력

                    int size = map[i][j].size();

                    for(Fireball f : map[i][j]){
                        totalM += f.m;
                        totalS += f.s;
                        if(f.d % 2 == 0) even++;
                        else odd++;
                    }

                    map[i][j].clear();

                    if(totalM / 5 > 0){ // 질량이 0이 아닌 파이어볼
                        int nM = totalM / 5; // 나누어진 파이어볼의 질량
                        int nS = totalS / size; // 나누어진 속력
                        // 파이아볼의 방향
                        // 모든 파이어볼의 방향이 모두 짝수이거나(even > 0, odd == 0) 홀수이면(odd > 0, even == 0) => 0, 2, 4, 6
                        int[] newDirs = (even == 0 || odd == 0) ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7};
                        
                        // 파이어볼 나누기
                        for(int d: newDirs){
                            map[i][j].add(new Fireball(i, j, nM, nS, d));
                        }
                    }
                }
            }
        }
    }
    
    // 남아있는 파이어볼의 질량의 합
    static int getTotalM(){
        int total = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(Fireball f: map[i][j]){
                    total += f.m;
                }
            }
        }
        return total;
    }
}
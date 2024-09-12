import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, map[][], answer;
    static boolean[][] cloudDisappear, cloud;
    static int[][] dir= {
                            {0, 0},
                            {0, -1},
                            {-1, -1},
                            {-1, 0},
                            {-1, 1},
                            {0, 1},
                            {1, 1},
                            {1, 0},
                            {1, -1},
                        };
    static int[][] diaDir = {
                                {-1, -1},
                                {-1, 1},
                                {1, 1},
                                {1, -1},
                            };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = 0;

        map = new int[N][N];
        cloudDisappear = new boolean[N][N];
        cloud = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Cloud Position Initialization
        cloud[N - 1][0] = cloud[N - 1][1] = cloud[N - 2][0] = cloud[N - 2][1] = true;

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            moveCloud(d, s);
            cloudAppear();
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                answer += map[i][j];
            }
        }

        System.out.println(answer);
    }

    static void moveCloud(int direction, int s){
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(cloud[i][j]){
                    cloud[i][j] = false;
                    int nx = (i + dir[direction][0] * s) % N;
                    int ny = (j + dir[direction][1] * s) % N;

                    // 음수일 경우 N을 더해줌으로써 반대편으로 이동
                    if (nx < 0) nx += N;
                    if (ny < 0) ny += N;

                    // 이동하고 비 내림 +1
                    map[nx][ny] += 1;
                    // 비가 내리고 구름이 사라진 위치
                    cloudDisappear[nx][ny] = true;
                }
            }
        }

        // 대각 물 여부 확인 후 물의 양 갱신
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(cloudDisappear[i][j]){
                    int cnt = 0;
                    for(int d = 0; d < 4; d++){
                        int dx = i + diaDir[d][0];
                        int dy = j + diaDir[d][1];

                        if(dx >= 0 && dx < N && dy >= 0 && dy < N && map[dx][dy] > 0){
                            cnt++;;
                        }
                    }
                    map[i][j] += cnt;
                }
            }
        }
    }

    // 구름이 사라졌던 자리 제외하고, 2 이상인 곳 구름 생성
    // 구름 사라졌던 자리 초기화
    static void cloudAppear(){
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] >= 2 && !cloudDisappear[i][j]){
                    cloud[i][j] = true;
                    map[i][j] -= 2;
                }else{
                    cloudDisappear[i][j] = false;
                }
            }
        }
    }
}
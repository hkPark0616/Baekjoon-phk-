import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Microbe {
    int cnt;
    int max;
    int dir;

    public Microbe(int cnt, int max, int dir) {
        this.cnt = cnt;
        this.max = max;
        this.dir = dir;
    }
}

public class Solution {
    static int N, M, K, answer;
    static Microbe[][] map;
    static Microbe[][] tempMap;
    static int[] delX = { 0, -1, 1, 0, 0 }, delY = { 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            answer = 0;
            map = new Microbe[N][N];
            tempMap = new Microbe[N][N];

            // 초기 미생물 군집 정보 입력
            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int microbeCnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                map[x][y] = new Microbe(microbeCnt, microbeCnt, dir);
            }

            // M번의 이동 수행
            for(int m = 0; m < M; m++) {
                move(); // 이동 및 병합 함수 호출
            }

            // 최종 결과 계산
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == null) continue;
                    answer += map[i][j].cnt;
                }
            }

            System.out.println("#" + t + " " + answer);
        }
    }

    static void move() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == null) continue;

                int nx = i + delX[map[i][j].dir];
                int ny = j + delY[map[i][j].dir];
                // 이동하려는 곳 비어있으면 값 이동
                if (tempMap[nx][ny] == null) {
                    tempMap[nx][ny] = map[i][j];
                } else { // 다른 군집이 존재
                    // 새로운 군집의 max가 더 큰 경우 두 군집 병합
                    if (tempMap[nx][ny].max > map[i][j].max) {
                        tempMap[nx][ny].cnt += map[i][j].cnt;
                    }
                    // 기존 군집이 더 큰 경우, 일단 합쳐주고 방향 바꿈
                    else {
                        tempMap[nx][ny].cnt += map[i][j].cnt;
                        tempMap[nx][ny].max = map[i][j].max;
                        tempMap[nx][ny].dir = map[i][j].dir;
                    }
                }
                map[i][j] = null; // 이동하고 원래 위치 군집 비움
            }
        }

        // 테두리에서의 처리 및 map 업데이트
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tempMap[i][j] == null) continue;

                // 테두리 처리
                if (i == 0 || i == N - 1 || j == 0 || j == N - 1) {
                    tempMap[i][j].cnt /= 2;
                    tempMap[i][j].dir = reverseDirection(tempMap[i][j].dir);
                }

                // map 업데이트
                map[i][j] = tempMap[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tempMap[i][j] == null) continue;
                map[i][j] = tempMap[i][j];
                map[i][j].max = map[i][j].cnt;
                tempMap[i][j] = null;
            }
        }

    }

    // 방향을 반대로 변경하는 함수
    static int reverseDirection(int dir) {
        if (dir == 1) return 2;
        if (dir == 2) return 1;
        if (dir == 3) return 4;
        if (dir == 4) return 3;
        return 0;
    }
}
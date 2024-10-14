import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, T;
    static int x, d, k;
    static int answer;
    static List<List<Integer>> plates = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        answer = 0;

        // 원판 초기화
        for (int i = 0; i < N; i++) plates.add(new ArrayList<>());

        // 원판 숫자 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                plates.get(i).add(n);
            }
        }

        // 회전 및 계산 작업
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());  // 회전할 원판 번호 배수
            d = Integer.parseInt(st.nextToken());  // 회전 방향 (0: 시계, 1: 반시계)
            k = Integer.parseInt(st.nextToken());  // k 칸 회전

            // x의 배수인 원판 회전
            for (int j = 0; j < plates.size(); j++) {
                if ((j + 1) % x == 0) {
                    simulation(j + 1, d, k);  // j+1번째 원판 회전
                }
            }

            // 인접한 같은 숫자가 있는지 확인
            if (!findNearBy()) {
                // 인접한 같은 숫자가 없으면 평균 계산 후 처리
                int[] rs = calc();
                int sum = rs[0], cnt = rs[1];
                if (cnt > 0) {
                    double avg = (double) sum / cnt;  // 평균 구하기 (소수점)
                    noNearBy(avg);
                }
            }
        }

        // 최종 합 출력
        answer = calc()[0];
        System.out.println(answer);
    }

    // 회전 시뮬레이션
    static void simulation(int plateIdx, int dir, int k) {
        k = k % M;  // M보다 큰 회전 횟수 조정
        if (dir == 0) {
            turnRight(plateIdx - 1, k);  // 시계 방향 회전
        } else {
            turnLeft(plateIdx - 1, k);   // 반시계 방향 회전
        }
    }

    static void turnLeft(int plateIdx, int k) {
        List<Integer> plate = plates.get(plateIdx);
        List<Integer> temp = new ArrayList<>(plate.subList(k, M));
        temp.addAll(plate.subList(0, k));
        plates.set(plateIdx, temp);
    }

    static void turnRight(int plateIdx, int k) {
        List<Integer> plate = plates.get(plateIdx);
        List<Integer> temp = new ArrayList<>(plate.subList(M - k, M));
        temp.addAll(plate.subList(0, M - k));
        plates.set(plateIdx, temp);
    }


    // 인접한 같은 숫자가 있는지 확인
    static boolean findNearBy() {
        boolean flag = false;
        boolean[][] toRemove = new boolean[N][M];  // 제거할 숫자 표시

        // 원판 상에서 인접한 같은 숫자 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = plates.get(i).get(j);
                if (num == 0) continue;

                // 좌우 비교
                int leftIdx = j == 0 ? M - 1 : j - 1;
                int rightIdx = (j + 1) % M;

                if (plates.get(i).get(leftIdx) == num) {
                    toRemove[i][j] = true;
                    toRemove[i][leftIdx] = true;
                    flag = true;
                }
                if (plates.get(i).get(rightIdx) == num) {
                    toRemove[i][j] = true;
                    toRemove[i][rightIdx] = true;
                    flag = true;
                }

                // 상하 비교 추가
                if (i > 0 && plates.get(i - 1).get(j) == num) {
                    toRemove[i][j] = true;
                    toRemove[i - 1][j] = true;
                    flag = true;
                }
                if (i < N - 1 && plates.get(i + 1).get(j) == num) {
                    toRemove[i][j] = true;
                    toRemove[i + 1][j] = true;
                    flag = true;
                }
            }
        }

        // 표시된 숫자들을 0으로 변경
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (toRemove[i][j]) {
                    plates.get(i).set(j, 0);
                }
            }
        }

        return flag;
    }

    // 원판에서 남은 숫자의 합과 개수 계산
    static int[] calc() {
        int sum = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = plates.get(i).get(j);
                if (num != 0) {
                    sum += num;
                    cnt++;
                }
            }
        }
        return new int[]{sum, cnt};
    }

    // 인접한 숫자가 없는 경우 평균값에 따라 숫자 조정
    static void noNearBy(double avg) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = plates.get(i).get(j);
                if (num != 0) {
                    if (num > avg) plates.get(i).set(j, num - 1);
                    else if (num < avg) plates.get(i).set(j, num + 1);
                }
            }
        }
    }
}
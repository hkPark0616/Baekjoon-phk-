import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] arr = new long[N];
        long total = 0;
        for(int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
            total += arr[i];
        }

        int left = 0, right = 0;
        long clockwise = 0; // 시계방향으로 간 거리
        long answer = 0;

        // left를 시작점으로 모든 탑 확인
        while(left < N) {
            // 시계 + 반시계 = 전체둘레
            // 시계방향 거리 늘리기(시계가 반시계보다 짧을 때만 실제 거리가 증가)
            // 시계 > 반시계 -> 시계 > total - 시계 -> 2 * 시계 > total -> 시계 > total / 2
            while(right < left + N && clockwise + arr[right % N] <= total / 2) {
                clockwise += arr[right % N];
                right++;
            }

            // 반시계 = total - 시계
            answer = Math.max(answer, Math.min(clockwise, total - clockwise));

            // 시계방향 거리 줄이기
            clockwise -= arr[left];
            left++;
        }

        System.out.println(answer);
    }
}
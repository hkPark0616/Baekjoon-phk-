import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[] trees = new int[N];
            int max = 0;

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                trees[i] = Integer.parseInt(st.nextToken());
                // 가장 높은 나무 찾기
                // 가장 높은 나무를 기준으로 다른 나무의 높이를 맞춤
                if(trees[i] > max){
                    max = trees[i];
                }
            }

            int diff = 0;
            int totalDiff = 0;
            int oneDayCount = 0;

            for (int i = 0; i < N; i++) {
                // 각 나무가 얼마나 낮춰져야 하는지
                diff = max - trees[i];

                // 모든 나무의 차이를 합
                totalDiff += diff;

                // 이미 맞춰진 나무는 넘어감
                if(diff == 0) continue;

                // 1일 작업으로 해결할 수 있는 횟수
                // (짝수는 2일 작업, 홀수는 1일 작업)
                oneDayCount += diff % 2;
            }

            // 2일 작업으로 해결할 수 있는 총 작업 일수 + 나머지 작업 일수(1일 작업)
            // 3으로 나눈 몫에 대해서는 2일 작업을 1번, 1일 작업을 1번을 동시에 수행
            // 즉, 전체 차이에서 2일 작업을 할 수 있는 횟수
            // + 2일 작업만으로도 남은 일수가 있다면, 그건 1일 작업이 필요한 부분
            int days = (totalDiff / 3) * 2 + (totalDiff % 3);

            // 전체 작업을 최단 기간 내에 해결하기 위해 필요한 일수를 계산
            // 1일 작업을 포함한 최종 일수를 계산함
            // 2일 작업이 가능한 횟수(days / 2)과 1일 작업(days % 2)을 조합하여 계산된 일수
            int one = days / 2 + days % 2;

            int answer = 0;
            // 1일 작업으로 해결할 수 있는 횟수(oneDayCount)가 1일 작업 포함 횟수보다 작거나 같으면
            // 즉, 1일 작업으로 해결할 수 있는 작업량이 충분하다면 계산된 days가 최종일수로 적합함
            if(oneDayCount <= one) {
                answer = days;
            }else {
                // 아니라면 1일 작업으로 해결해야 할 일이 많아서 1일 작업만으로는 모든 작업을 처리할 수 없음
                // 필요한 1일 작업 횟수를 2일 작업에 맞춰서 조정
                // 예를 들어, 1일 작업 횟수가 많으면 1일 작업을 처리하는 데 더 많은 시간이 필요하고,
                // 그 경우에는 1일 작업을 여러 번 하는 것보다 2일 작업을 하는 것이 더 효율적이므로,
                // 최단 기간을 계산하기 위해 1일 작업 횟수에 따라 하루를 빼주는 방식으로 해결
                answer = oneDayCount * 2 - 1;  // 1일 작업 횟수에 따른 최소 일수를 계산
            }

            System.out.println("#" + t + " " + answer);
        }
    }
}
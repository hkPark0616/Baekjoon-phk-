import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(br.readLine());  // 테스트 케이스 개수
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= testCaseCount; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] taskCount = new int[K + 1];  // 각 일에 대해 선택된 횟수 카운트
            StringTokenizer tasks = new StringTokenizer(br.readLine());
            StringTokenizer times = new StringTokenizer(br.readLine());

            PriorityQueue<int[]> persuasionQueue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));  // 설득 시간을 기준으로 우선순위 큐
            for (int i = 0; i < N; i++) {
                int job = Integer.parseInt(tasks.nextToken());
                taskCount[job]++;
                int persuasionTime = Integer.parseInt(times.nextToken());
                persuasionQueue.offer(new int[] { job, persuasionTime });  // 작업 번호, 설득 시간
            }

            int noSelectedCnt = 0;  // 선택되지 않은 일의 수
            for (int i = 1; i <= K; i++) {
                if (taskCount[i] == 0) {
                    noSelectedCnt++;  // 선택되지 않은 일이 있으면 카운트
                }
            }

            long minTotalTime = 0;

            // 설득해야 할 일들을 처리
            while (noSelectedCnt > 0) {
                int[] task = persuasionQueue.poll();  // 가장 설득 시간이 적은 작업을 꺼냄
                if (taskCount[task[0]] == 1) {
                    continue;  // 이미 선택된 일은 건너뜀
                }

                minTotalTime += task[1];  // 설득 시간 더하기
                taskCount[task[0]]--;  // 해당 일의 선택된 횟수 감소
                noSelectedCnt--;

                if (noSelectedCnt == 0) {
                    break;  // 더 이상 설득할 일이 없으면 종료
                }
            }

            sb.append("#").append(tc).append(" ").append(minTotalTime).append("\n");
        }

        System.out.println(sb);
    }
}
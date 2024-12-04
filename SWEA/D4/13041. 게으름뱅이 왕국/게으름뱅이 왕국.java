import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] taskCount = new int[K + 1];
            StringTokenizer tasks = new StringTokenizer(br.readLine());
            StringTokenizer times = new StringTokenizer(br.readLine());
            
            // 설득 시간 오름차순 정렬
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            for(int i = 0; i < N; i++){
                int task = Integer.parseInt(tasks.nextToken());
                taskCount[task]++; // 해당 일 카운트 증가
                int time = Integer.parseInt(times.nextToken());
                pq.offer(new int[] {task, time}); // 일 번호, 설득 시간
            }

            int notSelectedCount = 0; // 선택되지 않은 일의 수
            for(int i = 1; i <= K; i++){
                // 선택되지 않은 일 카운트 증가
                if(taskCount[i] == 0){
                    notSelectedCount++;
                }
            }

            long minTime = 0;

            // 선택되지 않은 일(설득해야 하는 일)들을 처리
            while(notSelectedCount > 0){
                int[] task = pq.poll();

                // 이미 선택된 일은 건너뜀
                if(taskCount[task[0]] == 1){
                    continue;
                }

                minTime+= task[1]; // 설득 시간
                taskCount[task[0]]--; // 설득한 일의 선택된 횟수 감소
                notSelectedCount--; // 선택되지 않은 일 개수 감소

                // 더 이상 설득해야할 일(선택되지 않은 일)이 없으면 종료
                if(notSelectedCount == 0) break;
            }

            sb.append("#" + t + " " + minTime + "\n");
        }

        System.out.println(sb.toString());
    }
}
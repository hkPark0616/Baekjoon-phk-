import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[] tree = new int[N];
            int max = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                tree[i] = Integer.parseInt(st.nextToken());
                if (tree[i] > max) {
                    max = tree[i];
                }
            }

            int diff = 0;
            int totalDiff = 0;
            int oneDayCount = 0;

            for (int i = 0; i < N; i++) {
                diff = max - tree[i];
                
                totalDiff += diff;
                
                if(diff == 0) continue;

                oneDayCount += diff % 2; // 1일 작업으로 해결할 수 있는 횟수
            }
            
            // 2일 작업으로 해결할 수 있는 총 작업 일수 + 나머지 작업 일수(1일 작업)
            int days = (totalDiff / 3) * 2 + (totalDiff % 3);
            
            // 1일 작업 포함
            // 2일 작업과 1일 작업을 조합하여 계산된 일수
            int one = days / 2 + days % 2;
            
            int answer = 0;
            // 1일 작업으로 해결할 수 있는 횟수가 1일 작업 포함 횟수보다 작거나 같으면
            // 즉, 1일 작업으로 해결할 수 있는 작업량이 충분하다면 계산된 days가 최종일수로 적합함
            if(oneDayCount <= one) {
            	answer = days;
            }else {
            	// 아니라면 1일 작업만으로는 모든 작업 처리할 수 없음
            	// 필요한 1의 갯수(oneDayCount)가 충족되는 날이 최단기간
            	answer = oneDayCount * 2 - 1;
            }

            System.out.println("#" + t + " " + answer);
        }
    }
}

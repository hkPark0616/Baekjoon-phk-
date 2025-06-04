import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int w: works) {
            pq.offer(w);
        }

        while(n != 0) {
            int work = pq.poll() - 1;
            pq.offer(work);

            n--;
        }
        
        while(!pq.isEmpty()) {
            int num = pq.poll();
            if(num > 0) {
                answer += Math.pow(num, 2);
            }
        }
        
        return answer;
    }
}
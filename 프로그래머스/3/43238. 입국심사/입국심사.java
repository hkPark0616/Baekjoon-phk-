import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long max = 0;
        
        for(int i = 0; i < times.length; i++) max = Math.max(max, times[i]); 
        Arrays.sort(times);
        
        answer = search(n, max, times);
        
        return answer;
    }
    
    static long search(int n, long max, int[] times) {
        long result = 0;
        long start = 0;
        long end = n * max;
        
        while(start <= end) {
            long mid = (start + end) / 2;
            
            long sum = 0;
            for(int i = 0; i < times.length; i++) {
                long cnt = mid / times[i];
                
                sum += cnt;
            }
            
            if(sum >= n) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            
        }
        
        return result;
    }
}
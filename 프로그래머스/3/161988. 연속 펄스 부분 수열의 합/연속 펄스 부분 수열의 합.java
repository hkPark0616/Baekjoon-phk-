import java.util.*;
import java.lang.*;

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        
        int N = sequence.length;
        long[] pulse1 = new long[N];
        long[] pulse2 = new long[N];
        
        for(int i = 0; i < N; i++) { // 펄스수열 미리 곱해놓기
            pulse1[i] = sequence[i] * ((i % 2) == 0 ? 1 : -1);
            pulse2[i] = sequence[i] * ((i % 2) == 0 ? -1 : 1);
        }
        
        answer = Math.max(sum(pulse1), sum(pulse2));
        
        return answer;
    }
    
    static long sum(long[] pulse) {
        long max = pulse[0];
        long cur = pulse[0];
        for(int i = 1; i < pulse.length; i++) {
            cur = Math.max(pulse[i], cur + pulse[i]);
            max = Math.max(max, cur);
        }
        return max;
    }
}
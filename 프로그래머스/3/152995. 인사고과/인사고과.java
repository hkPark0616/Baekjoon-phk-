import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        int[] wanho = scores[0];

        Arrays.sort(scores, (a, b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });

        int sum = wanho[0] + wanho[1];
        int rank = 1;
        int max = 0;
        for(int[] s: scores) {
            if(s[1] < max) { // 점수가 모두 더 높은 사람이 있음
                if(s == wanho) return -1;
            } else {
                max = Math.max(max, s[1]);
                if(s[0] + s[1] > sum) rank++;
            }
        }
        
        answer = rank;
        
        return answer;
    }
}
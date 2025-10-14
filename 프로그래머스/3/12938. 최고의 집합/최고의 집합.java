import java.util.*;

class Solution {
    // n개의 수로 이루어진 집합
    // 집합의 합이 s가 되는 집합
    // 그 중에서 곱이 최대인 집합 찾기
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        if(s < n) return new int[] {-1};
        
        int a = s / n; // 몫
        int b = s % n; // 나머지
        
        Arrays.fill(answer, a);
        int idx = 0;
        while(b-- > 0) {
            answer[idx] += 1;
            idx++;
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
}
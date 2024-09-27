import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int n = citations.length;
        int[] count = new int[n + 1];
        
        // 인용 횟수를 카운트합니다. n번 이상 인용된 논문은 n으로 처리합니다.
        for (int citation : citations) {
            if (citation >= n) {
                count[n]++;
            } else {
                count[citation]++;
            }
        }
        
        System.out.println(Arrays.toString(count));
        int total = 0;
        // 뒤에서부터 카운트를 누적하면서 h-index를 찾습니다.
        for (int i = n; i >= 0; i--) {
            total += count[i];
            if (total >= i) {
                return i;
            }
        }
        
        return 0;
    }
}

import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        String base3 = "";
    
        while(n > 0){
            base3 += n % 3;
            n /= 3;
        }
        answer = Integer.parseInt(base3, 3);

        return answer;
    }
}
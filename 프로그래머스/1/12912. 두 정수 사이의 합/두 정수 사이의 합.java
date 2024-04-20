import java.lang.*;

class Solution {
    public long solution(int a, int b) {
        long answer = 0;
        long diff = Math.abs(a-b);
        long num = 0;

        if(a < b){
            for(long i = a; i <= b; i++){
                answer = answer + i;
            }
            return answer;
        }else{
            for(long i = b; i <= a; i++){
                answer = answer + i;
            }
            return answer;
        }
    }
}
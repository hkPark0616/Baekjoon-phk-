import java.lang.*;

class Solution {
    public int solution(int num) {
        int answer = 0;
        long n = Long.valueOf(num);
        int cnt = 0;
        
        if(n == 1){
          return 0;  
        }else{
            for(int i = 0; i <= 500; i++){
                if(n == 1){
                    cnt = i;
                    break;
                } 
                if((n % 2) == 0){
                    n /= 2;
                    cnt++;
                }else{
                    n = (n * 3) + 1;
                    cnt++;
                }
            }
        }
        
        if(cnt >= 500){
            answer = -1;
        }else{
            answer = cnt;
        }
            
        return answer;
    }
}
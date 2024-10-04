import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;

        String str = convert(n, k);
        
        String arr[] = str.split("0");
        System.out.println(Arrays.toString(arr));
        for(String s: arr){
            if( s.length() != 0 && isPrime(Long.parseLong(s))){
                answer++;
            }
        }
        
        return answer;
    }
    
    public String convert(int num, int k){
        StringBuilder sb = new StringBuilder();
        while(num > 0){
            int temp = num % k;
            sb.append(temp);
            num /= k;
        }
        
        return sb.reverse().toString();
    }
    
    public boolean isPrime(long num){
        if(num < 2) return false;
        
        for(int i = 2; i <= (int)Math.sqrt(num); i++){
            if(num % i == 0) return false;
        }
        
        return true;
    }
}
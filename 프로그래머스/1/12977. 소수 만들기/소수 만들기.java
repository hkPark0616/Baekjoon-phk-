import java.util.*;
import java.lang.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < nums.length; i++){
            list.add(nums[i]);
        }
        
        Collections.sort(list);
        
        for(int i = 0; i < list.size(); i++){
            for(int j = i + 1; j < list.size(); j++){
                for(int k = j + 1; k < list.size(); k++){
                    int sum = list.get(i) + list.get(j) + list.get(k);
                    boolean isPrime = prime(sum);
                    if(isPrime){
                        answer++;
                    }
                    
                }
            }
        }

        return answer;
    }
    
    public boolean prime(int n) {
	for (int i = 2; i<=(int)Math.sqrt(n); i++) {
      if (n % i == 0) {
          return false;
      }
	}
	return true;
}
}
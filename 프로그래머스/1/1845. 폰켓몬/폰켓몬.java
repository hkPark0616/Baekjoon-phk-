import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        
        Arrays.sort(nums);
        int cnt = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != cnt){
                cnt = nums[i];
                answer++;
            }
        }
        
        if((nums.length / 2) <= answer){
            answer = nums.length / 2;
        }
        return answer;
    }
}
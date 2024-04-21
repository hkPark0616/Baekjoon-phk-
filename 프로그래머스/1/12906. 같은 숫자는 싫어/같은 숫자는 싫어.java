import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        ArrayList<Integer> list = new ArrayList<>();
        int prev = -1;
        
        for(int i : arr){
            if(i != prev){
                list.add(i);
                prev = i;
            }
        }

        answer = new int[list.size()];
        
        for(int i = 0; i < list.size(); i++){
            
            answer[i] = list.get(i);
        }
        return answer;
    }
}
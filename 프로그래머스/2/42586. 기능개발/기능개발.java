import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = new int[progresses.length];
        List<Integer> days = new ArrayList<>();
        
        for(int i = 0; i < progresses.length; i++){
            int completeDays = 0;
            int progress = progresses[i];
            while(progress < 100){
                progress += speeds[i];
                completeDays++;
            }
            days.add(completeDays);
        }
        
        System.out.println(days);
        return answer;
    }
}
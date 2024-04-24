import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer;
        ArrayList<Integer> list = new ArrayList<>();
        
        //Arrays.sort(numbers);
        
        for(int i = 0; i < numbers.length; i++){
            for(int j = i + 1; j < numbers.length; j++){
                int sum = numbers[i] + numbers[j];
                if(list.contains(sum)){
                    continue;
                }else{
                    list.add(numbers[i] + numbers[j]);
                }
                
            }
        }
        
        answer = new int[list.size()];
        
        Collections.sort(list);
        
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
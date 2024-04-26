import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        
        
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> dataList = new ArrayList<>();
        
        for(int i = 0; i < score.length; i++){
            arrayList.add(score[i]);
            Collections.sort(arrayList, Collections.reverseOrder());
            if(arrayList.size() < k + 1){
                answer[i] = arrayList.get(arrayList.size() - 1);
            }else{
                answer[i] = arrayList.get(k - 1);
            }
            
        }
        
        
        return answer;
    }
}
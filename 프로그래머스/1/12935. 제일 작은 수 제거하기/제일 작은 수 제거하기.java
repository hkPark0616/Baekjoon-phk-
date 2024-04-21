import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        int[] answer;
        List<Integer> list = new ArrayList<>();
        if(arr.length <= 1){
            answer = new int[arr.length];
            answer[0] = -1;
            return answer;
        }
        
        int min = arr[0];
        for(int i = 0; i < arr.length; i++){
            if(arr[i] < min){
                min = arr[i];
            }
        }
        
        answer = new int[arr.length - 1];
        int n = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != min){
                answer[n] = arr[i];
                n++;
            }
        }

        return answer;

    }
}
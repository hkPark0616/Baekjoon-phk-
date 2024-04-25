class Solution {
    public String solution(int[] food) {
        String answer = "";
        
        for(int i = 1; i < food.length; i++){
            int n = food[i] / 2;
            for(int j = 0; j < n; j++){
                answer += i;
            }
        }
        answer += "0";
        
        for(int i = food.length - 1; i > 0; i--){
            int n = food[i] / 2;
            for(int j = 0; j < n; j++){
                answer += i;
            }
        }
        return answer;
    }
}
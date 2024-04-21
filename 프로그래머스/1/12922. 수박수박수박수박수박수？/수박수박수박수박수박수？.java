class Solution {
    public String solution(int n) {
        String answer = "";
        String s1 = "수";
        String s2 = "박";
        
        for(int i = 0; i < n; i++){
            if((i % 2) == 0){
                answer += s1;
            }else{
                answer += s2;
            }
        }
        return answer;
    }
}
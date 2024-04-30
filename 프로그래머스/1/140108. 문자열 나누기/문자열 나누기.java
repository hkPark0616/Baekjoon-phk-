class Solution {
    public int solution(String s) {
        int answer = 0;
        int same = 0;
        int diffrent = 0;

        char first = s.charAt(0);
        for(int i = 0; i < s.length(); i++){
            
            if(same == diffrent){
                same = 1;
                diffrent = 0;
                first = s.charAt(i);
                answer += 1;
            }else{
                if(first == s.charAt(i)){
                    same++;
                }else{
                    diffrent++;
                }
            }
        }
        
        return answer;
    }
}
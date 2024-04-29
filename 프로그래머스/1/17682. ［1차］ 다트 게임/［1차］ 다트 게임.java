import java.lang.*;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;

        int[] scoreList = new int[3];
        int index = 0;
        int n = 0;
        String num = "";
        
        for(int i = 0; i < dartResult.length(); i++){
            char c = dartResult.charAt(i);
            
            if(c >= '0' && c <= '9'){
                num += String.valueOf(c);
            }else if(c == 'S' || c == 'D' || c == 'T'){
                n = Integer.valueOf(num);
                if(c == 'S'){
                    scoreList[index++] = (int)Math.pow(n, 1);
                }else if(c == 'D'){
                    scoreList[index++] = (int)Math.pow(n, 2);
                }else{
                    scoreList[index++] = (int)Math.pow(n, 3);
                }
                num = "";
            }else{
                if(c == '*'){
                    scoreList[index - 1] *= 2;
                    if(index >= 2) scoreList[index - 2]*=2; 
                }else{
                    scoreList[index - 1] *= -1;
                }
            }
                
        }
        
        for(int i = 0; i < scoreList.length; i++){
            answer += scoreList[i];   
        }

        return answer;
    }
}
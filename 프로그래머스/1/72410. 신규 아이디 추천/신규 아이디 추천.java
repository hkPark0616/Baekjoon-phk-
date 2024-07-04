import java.util.*;

class Solution {
    public String solution(String new_id) {
        String answer = new_id;
        String s = "~!@#$%^&*()=+[{]}:?,<>/";  
        
        // 1
        answer = answer.toLowerCase();
        //2
        for(int i = 0; i < answer.length(); i++){
            if(s.contains(String.valueOf(answer.charAt(i)))){
                answer = answer.replace(String.valueOf(answer.charAt(i)), "");
                i--;
            }
        }
        //answer = answer.replaceAll("[~!@#$%^&*()=+[{]}:?,<>/]", "");

        //3
        answer = answer.replaceAll("\\.{2,}", ".");
        
        //4
        if(answer.charAt(0) == '.' && answer.length() > 1){
            answer = answer.substring(1);
        }
        if(answer.charAt(answer.length() - 1) == '.' && answer.length() != 0){
            answer = answer.substring(0, answer.length() - 1);
        }
        // 5
        if(answer.length() == 0) answer += "a";

        // 6
        if(answer.length() >= 16){
            answer = answer.substring(0, 15);
            if(answer.charAt(0) == '.' && answer.length() > 1){
            answer = answer.substring(1);
            }
            if(answer.charAt(answer.length() - 1) == '.' && answer.length() > 1){
                answer = answer.substring(0, answer.length() - 1);
            }
        }
        
        //7
        if(answer.length() <= 2){
            char last = answer.charAt(answer.length() - 1);
            while(answer.length() < 3){
                answer += last;
            }
        }
        return answer;
    }
}
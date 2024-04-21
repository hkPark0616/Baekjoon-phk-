import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        String[] list = s.split(" ", -1);

        for(int i = 0; i < list.length; i++){
            String word = list[i];
            String newStr = "";
            for(int j = 0; j < word.length(); j++){
                char temp = word.charAt(j);

                if((j % 2) == 0){
                    newStr += Character.toUpperCase(temp);
                }else{
                    newStr += Character.toLowerCase(temp);;
                }
            }
            newStr = newStr + " ";
            if(i == list.length-1){
                newStr = newStr.trim();
                answer += newStr;
            }else{
                answer += newStr;
            }
    
        }
                
        return answer;
    }
}
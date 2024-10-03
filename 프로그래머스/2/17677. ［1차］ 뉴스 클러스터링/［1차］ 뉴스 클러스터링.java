import java.util.*;
import java.lang.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        sub(list1, str1);
        sub(list2, str2);
        
        double union = 0;
        double inter = 0;
        
        for(int i = 0; i < list1.size(); i++){
            String s = list1.get(i);
            
            if(list2.contains(s)){
                inter++;
                list2.remove(s);
            }
            union++;
        } 
        
        if(list1.size() == 0 && list2.size() == 0){
            answer = 65536;
            return answer;
        }
        
        answer = (int)(inter / (union + list2.size()) * 65536);
        
        return answer;
    }
    
    static void sub(List<String> list, String str){
        for(int i = 0; i < str.length() - 1; i++){
            String temp = str.substring(i, i + 2);
            temp = temp.replaceAll("[^a-zA-Z]", "");
            if(temp.length() == 2){
                list.add(temp);
            }
        }
    }
}


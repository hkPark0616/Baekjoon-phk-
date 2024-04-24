import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        String[] answer = new String[strings.length];
        String s = "";
        
        Arrays.sort(strings);
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                char a1 = s1.charAt(n);
                char a2 = s2.charAt(n);
                
                if(a1 > a2){
                    return 1;
                }else if(a1 < a2){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        
        for(int i = 0; i < strings.length; i++){
            answer[i] = strings[i];
        }


        return answer;
    }
}
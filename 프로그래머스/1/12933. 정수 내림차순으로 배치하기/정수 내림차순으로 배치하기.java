import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public long solution(long n) {
        long answer = 0;
        String s = String.valueOf(n);
        String s2 = "";
        
        ArrayList<Character> list = new ArrayList<>();
        
        for(int i = 0; i < s.length(); i++){
            list.add(s.charAt(i));
        }
        
        Collections.sort(list, Collections.reverseOrder());
        
        for(int i = 0; i < s.length(); i++){
            s2 += list.get(i);
        }
        
        answer = Long.valueOf(s2);
        
        return answer;
    }
}
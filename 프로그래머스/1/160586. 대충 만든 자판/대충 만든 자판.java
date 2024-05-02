import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        HashMap<Character, Integer> map = new HashMap<>();
        
        for(int i = 0; i < keymap.length; i++){
            for(int j = 0; j < keymap[i].length(); j++){
                char c = keymap[i].charAt(j);
                if(!map.containsKey(c) || j < map.get(c)){
                    map.put(c, j + 1);
                }
            }
        }
        
        for(int i = 0; i < targets.length; i++){
            int cnt = 0;
            for(int j = 0; j < targets[i].length(); j++){
                char c = targets[i].charAt(j);
                
                if(map.containsKey(c)){
                    cnt += map.get(c);
                }else{
                    cnt = 0;
                    break;
                }
            }
            
            //answer[i] = cnt;
            if(cnt == 0){
                answer[i] = -1;
            }else{
                answer[i] = cnt;
            }

        }
        

        return answer;
    }
}
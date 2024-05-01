import java.util.*;
import java.lang.*;

class Solution {
    public String solution(String X, String Y) {
        String answer = "";
        
        HashMap<String, Integer> xmap = new HashMap<>();
        HashMap<String, Integer> ymap = new HashMap<>();
        
        ArrayList<String> list = new ArrayList<>();
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < X.length(); i++){
            xmap.put(String.valueOf(X.charAt(i)), xmap.getOrDefault(String.valueOf(X.charAt(i)), 0) + 1);
        }
        
        for(int i = 0; i < Y.length(); i++){
            ymap.put(String.valueOf(Y.charAt(i)), ymap.getOrDefault(String.valueOf(Y.charAt(i)), 0) + 1);
        }
        
        for(String key: xmap.keySet()){
            
            if(!ymap.containsKey(key)) continue;
            
            int size = Math.min(xmap.get(key), ymap.get(key));
            for(int i = 0; i < size; i++){
                list.add(key);
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        if(list.size() == 0) {
            return "-1";
        }

        for(int i = 0; i < list.size(); i++){
            sb.append(list.get(i));
        }
        
        if(sb.toString().replace("0", "").isEmpty()){
            return "0";
        }else{
            answer = sb.toString();
        }

        return answer;
    }
}
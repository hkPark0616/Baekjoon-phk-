import java.util.*;
import java.util.ArrayList;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer;
        List<Integer> list = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        
        int year = Integer.parseInt(today.substring(0, 4));
        int month = Integer.parseInt(today.substring(5, 7));
        int day = Integer.parseInt(today.substring(8, 10));
        
        int cnt = (year * 12 * 28) + (month * 28) + day;
        
        for(int i = 0;i < terms.length;i++){
            map.put(terms[i].split(" ")[0],terms[i].split(" ")[1]);
        }
        
        
        
        for(int i = 0;i < privacies.length;i++){
            String date = privacies[i].split(" ")[0];
            int pyear = Integer.parseInt(date.substring(0, 4));
            int pmonth = Integer.parseInt(date.substring(5, 7));
            int pday = Integer.parseInt(date.substring(8, 10));
            
            
            int type = Integer.parseInt(map.get(privacies[i].split(" ")[1])) * 28;
            
            int val = (year - pyear) * 12 * 28 + (month - pmonth) * 28 + (day - pday);
            
            if(val >= type){
                list.add(i+1);
                
            }
            
        }
        
        answer = new int[list.size()];
        for(int i = 0;i < list.size();i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
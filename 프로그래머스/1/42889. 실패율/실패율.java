import java.util.*;
import java.lang.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
         HashMap<Integer, Double> map = new HashMap<Integer, Double>();

        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Double> stageFail = new ArrayList<>();
        
        for(int i = 0; i < stages.length; i++){
            list.add(stages[i]);
        }
        
        Collections.sort(list);
        
        for(int i = 0; i < N; i++){
            double challenger = 0;
            double failUser = 0;
            int stage = i + 1;
            for(int j = 0; j < list.size(); j++){
                if(stage<=list.get(j)){
                    challenger++;
                }
                
                if(stage == list.get(j)){
                    failUser++;
                }
            }
            
            
            double fail;
            if(challenger == 0){
                fail = 0;
            }
            else{
                fail = failUser / challenger;
            }
            System.out.println(stage + " / " + fail);
            //double fail = failUser / challenger;
            // if(Double.isNaN(fail)){
            //     fail = 0;
            // }

            map.put(stage, fail);
        }
        
        List<Integer> sortStages = new ArrayList<>(map.keySet());

        Collections.sort(sortStages, (s1, s2) -> map.get(s2).compareTo(map.get(s1)));

        for(int i = 0; i < N; i++){
            answer[i] = sortStages.get(i);
        }


        return answer;
    }
}
import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        
        String[] str = s.split("\\},\\{");
        str[0] = str[0].replace("{", "");
        str[str.length - 1] = str[str.length - 1].replace("}", "");
        
        Arrays.sort(str, (o1, o2) -> {
            return o1.length() - o2.length();
        });
        
        String[][] tuples = new String[str.length][];
        for(int i = 0; i < tuples.length; i++){
            String[] t = str[i].split(",");
            tuples[i] = t;
        }
        
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < tuples.length; i++){
            for(int j = 0; j < tuples[i].length; j++){
               int n = Integer.parseInt(tuples[i][j]);
                
               if(!list.contains(n)){
                   list.add(n);
               }
            }
        }

        return list.stream().mapToInt(i -> i).toArray();
    }
}
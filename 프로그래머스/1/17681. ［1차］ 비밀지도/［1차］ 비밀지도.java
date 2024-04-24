class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        String[] map1 = new String[n];
        String[] map2 = new String[n];
        
        for(int i = 0; i < arr1.length; i++){
            String num1 = String.format("%0"+n+"d", Long.parseLong(Long.toBinaryString(arr1[i])));
            for(int j = 0; j < num1.length(); j++){
                
                if(map1[i] == null) map1[i] = "";
                
                if(num1.charAt(j) == '1'){
                    map1[i] += "#";
                }else{
                    map1[i] += " ";
                }
            }
        }
        
        for(int i = 0; i < arr2.length; i++){
            String num2 = String.format("%0"+n+"d", Long.parseLong(Integer.toBinaryString(arr2[i])));
            for(int j = 0; j < num2.length(); j++){
                
                if(map2[i] == null) map2[i] = "";
                
                if(num2.charAt(j) == '1'){
                    map2[i] += "#";
                }else{
                    map2[i] += " ";
                }
            }
        }
        
        
        for(int i = 0; i < map1.length; i++){
            String str1 = map1[i];
            String str2 = map2[i];
            for(int j = 0; j < str2.length(); j++){
                if(answer[i] == null) answer[i] = "";
                
                if(str1.charAt(j) != ' ' || str2.charAt(j) != ' '){
                    answer[i] += "#";
                }else{
                    answer[i] += " ";
                }
            }

        }
        
          
        
        return answer;
    }
}
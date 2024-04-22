class Solution {
    public String solution(String s, int n) {
        String answer = "";
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            int ascii = Integer.valueOf(c);
            if(ascii == 32){
                answer += " ";
            }else{
                
                
                int v = ascii + n;
                if(Character.isUpperCase(c) && (v > 90)){
                    v = 65 + (v - 91); // UpperCase: 65 ~ 90
                }
                
                if(Character.isLowerCase(c) && (v > 122)){
                    v = 97 + (v - 123); // LowerCase: 97 ~ 122 소
                }
                char ch = (char) v;
                answer  = answer + ch;
            }
            
            
        }
        return answer;
    }
}
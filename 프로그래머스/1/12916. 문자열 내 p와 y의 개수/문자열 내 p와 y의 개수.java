class Solution {
    boolean solution(String s) {
        
        boolean answer = true;
        int p = 0;
        int y = 0;
        
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'p' || s.charAt(i) == 'P'){
                p++;
            }else if(s.charAt(i) == 'y' || s.charAt(i) == 'Y'){
                y++;
            }else{
                continue;
            }
        }
        
        if(p != y) answer = false;

        System.out.println(answer);

        return answer;
    }
}
class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        int len = p.length();
        String s = "";
        
        for(int i = 0; i <= t.length() - len; i++){

            for(int j = i; j < i + len; j++){
                s += t.charAt(j);
            }

            if(Long.valueOf(s) <= Long.valueOf(p)) {
                answer++;
            }
            s = "";
        }
        return answer;
    }
}
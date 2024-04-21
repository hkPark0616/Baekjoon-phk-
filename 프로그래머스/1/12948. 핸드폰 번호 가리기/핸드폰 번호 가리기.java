class Solution {
    public String solution(String phone_number) {
        String answer = "";
        int leng = phone_number.length() - 4;

        for(int i = 0; i < phone_number.length(); i++){
            if(i >= leng){
                answer += phone_number.charAt(i);
            }
            else{
                answer += "*";
            }            
        }
        return answer;
    }
}
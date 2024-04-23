class Solution {
    public int solution(String s) {
        int answer = 0;
        String numString = "";
        String num = "";
        String[] engNum = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        
        for(int i = 0; i < s.length(); i++){
            if(Character.isDigit(s.charAt(i))){
                numString += s.charAt(i);
            }else{
                num += s.charAt(i);
                for(int j = 0; j < engNum.length; j++){
                    if(engNum[j].equals(num)){
                        numString += String.valueOf(j);
                        num = "";
                    }
                }
            }
        }
        answer = Integer.valueOf(numString);
        return answer;
    }
}
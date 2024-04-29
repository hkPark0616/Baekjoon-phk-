class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int best = 0;
        int worst = 0;
        
        int[] win = {6, 6, 5, 4, 3, 2, 1};
        
        for(int i = 0; i < lottos.length; i++){
            if(lottos[i] == 0){
                    best++;
            }
            for(int j = 0; j < win_nums.length; j++){
                         
                if(lottos[i] == win_nums[j]){
                    best++;
                    worst++;
                }
            }
        }
        
        answer[0] = win[best];
        answer[1] = win[worst];
        

        return answer;
    }
}
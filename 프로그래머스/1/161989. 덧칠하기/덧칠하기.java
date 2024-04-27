class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        boolean[] wall = new boolean[n];
        for(int i = 0; i < n; i++){
            wall[i] = true;
        }
        
        for(int i = 0; i < section.length; i++){
            wall[section[i] - 1] = false;
        }
        
        int index = 0;
        while(index < wall.length){
            if(!wall[index]){
                for(int j = index; j < m; j++){
                    wall[j] = true;
                }
                index += m;
                answer++;
            }else{
                index++;
            }  
        }
        
        
        // for(int i = index; i < wall.length; i++){
        //     if(!wall[i]){
        //         for(int j = i; j < m; j++){
        //             wall[j] = true;
        //         }
        //         index += m;
        //         answer++;
        //     }else{
        //         index++;
        //     }            
        // }
        
        
        return answer;
    }
}
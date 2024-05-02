class Solution {
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        String color = board[h][w];
        
        int[][] direction = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        
        for(int[] dir : direction){
            int mh = h + dir[0];
            int mw = w + dir[1];
            
            if((mh >= 0) && (mh < board.length) && (mw >= 0) && (mw < board.length)){
                if(color.equals(board[mh][mw])){
                    answer++;
                }
            }
        }
        
        return answer;
    }
}
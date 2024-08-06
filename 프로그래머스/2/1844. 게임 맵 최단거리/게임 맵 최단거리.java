import java.util.*;

class Solution {
    public static boolean visited[][];
    public static int min = -1;
    public static int answer = -1;
    public static int[] delX = {-1, 1, 0, 0};
    public static int[] delY = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        visited = new boolean[maps.length][maps[0].length];
        
        answer = bfs(0, 0, maps);

        return answer;
    }
    
    public static int bfs(int x, int y, int[][] maps){
        
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{x, y, 1});
        visited[0][0] = true;
        
        while(!que.isEmpty()){
            int[] tmp = que.poll();
            x = tmp[0];
            y = tmp[1];
            int count = tmp[2];
            
            if(x == maps.length - 1 && y == maps[0].length - 1){ // 우측 하단 도착
                answer = count;
                break;
            }
            
            for(int i = 0; i < 4; i++){ // 사방탐색
                int dx = x + delX[i];
                int dy = y + delY[i];
                
                // 맵 내에 있을경우
                if(dx >= 0 && dx < maps.length && dy >= 0 && dy < maps[0].length){
                    if(!visited[dx][dy] && maps[dx][dy] == 1){
                        visited[dx][dy] = true;
                        que.add(new int[]{dx, dy, count + 1});
                    }    
                }
            }
        }
        return answer;
    }
}
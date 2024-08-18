import java.util.*;

public class Main {
	public static int R, C, T, cleaner;
	public static int room[][];
	public static Queue<int[]> que;
	public static int delX[] = {0, -1, 0, 1}, delY[] = {1, 0, -1, 0};
	
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       
       R = sc.nextInt();
       C = sc.nextInt();
       T = sc.nextInt();
       
       room = new int[R][C];
       int sum = 0;
       
       for(int i = 0; i < R; i++) {
    	   for(int j = 0; j < C; j++) {
    		   room[i][j] = sc.nextInt();
    		   if(room[i][j] == -1) cleaner = i;
    	   }
       }
       
       for(int t = 0; t < T; t++) {
    	   setting();
    	   spread();
    	   clean();
       }
       
       for(int i = 0; i < R; i++) {
    	   for(int j = 0; j < C; j++) {
    		   int n = room[i][j];
    		   if(n != -1)
    			   sum += room[i][j];
    	   }
       }
       
       System.out.println(sum);
       
    }
    
    // 미세먼지 큐 세팅
    public static void setting() {
    	que = new LinkedList<>();
    	
    	for(int i = 0; i < R; i++) {
    		for(int j = 0; j < C; j++) {
    			if(room[i][j] == -1 || room[i][j] == 0) continue;
    			
    			// 먼지가 있는 좌표와 해당 좌표 먼지 양 큐에 넣음
    			que.add(new int[] {i, j, room[i][j]});
    		}
    	}
    }
    
    // 미세먼지 확산
    public static void spread() {
    	
    	while(!que.isEmpty()) {
    		
    		int[] tmp = que.poll();
    		int x = tmp[0];
    		int y = tmp[1];
    		int dust = tmp[2];

        	int cnt = 0;
        	int spreadAmount = dust / 5;
        	for(int d = 0; d < 4; d++) {
        		int nx = x + delX[d];
        		int ny = y + delY[d];
        		
        		if(nx < 0 || nx >= R || ny < 0 || ny >= C || room[nx][ny] == -1) continue;
        		if(room[nx][ny] == -1) continue;
    
    			cnt++;
        		room[nx][ny] += spreadAmount;
        	}
        	
        	room[x][y] -= (spreadAmount * cnt);
    	}
    }
    
    //공기청정기 작동
    public static void clean() {
    	// 공기청정기 위치
    	// 마지막 x 값이 3이므로 청정기 위쪽은 -1
    	int top = cleaner - 1;
    	int bottom = cleaner;
    	
    	// top
    	// top - bottom
    	for(int i = top - 1; i > 0; i--) {
    		room[i][0] = room[i - 1][0];
    	}
    	// right - left
    	for(int i = 0; i < C - 1; i++) {
    		room[0][i] = room[0][i + 1];
    	}
    	// bottom - top
    	for(int i = 0; i < top; i++) {
    		room[i][C - 1] = room[i + 1][C - 1];
    	}
    	// left - right
    	for(int i = C - 1; i > 1; i--) {
    		room[top][i] = room[top][i - 1];
    	}
    	room[top][1] = 0;
    	
    	// bottom
    	// bottom - top
    	for(int i = bottom + 1; i < R - 1; i++) {
    		room[i][0] = room[i + 1][0];
    	}
    	// right - left
    	for(int i = 0; i < C - 1; i++) {
    		room[R - 1][i] = room[R - 1][i + 1];
    	}
    	// top - bottom
    	for(int i = R - 1; i > bottom; i--) {
    		room[i][C - 1] = room[i - 1][C - 1];
    	}
    	// left - right
    	for(int i = C - 1; i > 1; i--) {
    		room[bottom][i] = room[bottom][i - 1];
    	}
    	room[bottom][1] = 0;
    }
    
}
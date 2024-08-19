import java.util.*;

public class Main {
	static int N, max, arr[][], player[];
	static boolean visit[];
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	N = sc.nextInt();
    	max = 0;
    	arr = new int[N + 1][10];
    	visit = new boolean[10];
    	player = new int[10];
    	
    	for(int i = 1; i <= N; i++) {
    		for(int j = 1; j <= 9; j++) {
    			arr[i][j] = sc.nextInt();
    		}
    	}
    	
    	// 4번 타자는 고정이므로 true
    	visit[4] = true;
    	// 4번 타자는 1번임
    	player[4] = 1;
    	
    	permutation(2);
    	System.out.println(max);
    	
    }
    
    static void permutation(int cnt) {
    	if(cnt == 10) {
    		play();
    		return;
    	}
    	
    	for(int i = 1; i <= 9; i++) {
    		if(visit[i]) continue;
    		
    		visit[i] = true;
    		player[i] = cnt;
    		permutation(cnt + 1);
    		visit[i] = false;
    		
    	}
    }
    
    static void play() {
    	int score = 0;
    	int start = 1;
    	boolean[] position; //주자 위치 저장
    	
    	for(int i = 1; i <= N; i++) {
    		position = new boolean[4];
    		int out = 0;
    		boolean isOut = false;
    		
    		while(true) {
    			
    			for(int j = start; j <= 9; j++) {
    				int hitPlayer = arr[i][player[j]]; // 타자
    				switch(hitPlayer) {
	    				case 0:
	    					out++;
	    					break;
	    				case 1: // 1루타
		    				for(int k = 3; k >= 1; k--) {
		    					if(position[k]) {
		    						// 3루에 주자가 있을때 1루타 나오면 점수 + 1 해주고, 주자 이동
		    						if(k == 3) { 
		    							score++;
		    							position[k] = false;
		    							continue;
		    						}
		    						
		    						position[k] = false;
		    						position[k + 1] = true;
		    					}
		    				}
		    				
		    				// base에 나가 있는 선수 없으면 1루타이므로 1루 true
		    				position[1] = true;
		    				break;
	    				case 2:
	    					for(int k = 3; k >= 1; k--) {
	    						if(position[k]) {
	    							// 주자가 3루 또는 2루에 있는 상황에서 2루타가 나오면 점수 + 1 주고 주자 이동
	    							if(k == 3 || k == 2) {
	    								score++;
	    								position[k] = false;
	    								continue;
	    							}
	    							
	    							position[k] = false;
	    							position[k + 2] = true;
	    						}
	    					}
	    					
	    					// base에 나가 있는 선수 없으면 2루타이므로 2루 true
	    					position[2] = true;
	    					break;
	    				case 3:
	    					// 3루타 이면 1~3루 어디에 선수가 있어도 점수가 + 됨
	    					for(int k = 3; k >= 1; k--) {
	    						if(position[k]) {
	    							score++;
	    							position[k] = false;
	    						}
	    					}
	    					
	    					// base에 나가 있는 선수 없으면 3루타이므로 3루 true
	    					position[3] = true;	    					
	    					break;
	    				case 4:
	    					// 홈런이면 타자 포함해서 나가있는 주자별로 점수 + 1
	    					for(int k = 1; k <= 3; k++) {
	    						if(position[k]) {
	    							score++;
	    							position[k] = false;
	    						}
	    					}
	    					// 타자 포함
	    					score++;
	    					break;
    				}
    				
    				if(out == 3) {
    					start = j + 1;
    					if(start == 10) {
    						start = 1;
    					}
    					isOut = true;
    					break;
    					
    				}
    			}
    			
    			// 아웃 3회 이닝 종료
    			if(isOut) break;
                // 이닝 끝나면 1번부터 다시 시작
    			start = 1;
    		}
    	}	
    	max =  Math.max(max, score);
    }
}
    
    
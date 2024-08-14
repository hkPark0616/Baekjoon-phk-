import java.util.*;

public class Main {
    static int k, num, direction;
    static List<Integer>[] gear = new ArrayList[4];
    static int[] gear_dir = new int[4];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int sum = 0;
        
        for(int i = 0; i < 4; i++) {
            gear[i] = new ArrayList<>();
            String s = sc.next();
            for(int j = 0; j < 8; j++) {
                char c = s.charAt(j);
                gear[i].add(c - '0');
            }
        }
        
        k = sc.nextInt(); // 회전 횟수
        for(int i = 0; i < k; i++) {
            num = sc.nextInt() - 1; // 톱니바퀴 번호
            direction = sc.nextInt(); // 1 시계, -1 반시계
            
            Arrays.fill(gear_dir, 0);
            
            gear_dir[num] = direction;
            right(num);
            left(num);
            
            for(int j = 0; j < 4; j++) {
            	if(gear_dir[j] == -1) {
            		turnLeft(j);
            	}else if(gear_dir[j] == 1) {
            		turnRight(j);
            	}else {
            		continue;
            	}
            }
        }
        
        // sum
        for(int i = 0; i < 4; i++) {        	
        	sum += gear[i].get(0) * (int)Math.pow(2, i);
        }
        
        System.out.println(sum);
    
    }
    
    // 현재 기어 위치에서 오른쪽으로 기어가 끝날때까지
    // 즉, 기어 번호가 3이 될때까지
    // num애 햐덩허눈 기어와 num + 1기어의 극이 다르면
    // 현재 기어 방향에 따라 다음 기어 방향 설정
    public static void right(int num) {
        if(num == 3) return;
        
    	if(gear[num].get(2) != gear[num + 1].get(6)) {
    		if(gear_dir[num] == 1) {
    			gear_dir[num + 1] = -1;
    		}else if(gear_dir[num] == -1){
    			gear_dir[num + 1] = 1;
    		}
    	}
    	
    	right(num + 1);
    }
    
    // right와 같은 구조
    public static void left(int num){
        if(num == 0) return;
        
        if(gear[num].get(6) != gear[num - 1].get(2)) {
        	if(gear_dir[num] == 1) {
        		gear_dir[num - 1] = -1;
        	}else if(gear_dir[num] == -1) {
        		gear_dir[num - 1] = 1;
        	}
        }
        
        left(num - 1);
    }
    
    // 기어 오른쪽 회전
    //리스트 왼쪽. 즉, 처음값에 리스트 끝값 삽입 후, 리스트 마지막 값 삭제
    public static void turnRight(int num) {
        gear[num].add(0, gear[num].get(7));
        gear[num].remove(8);
    }
    
    // 기어 왼쪽 회전
    // 오른쪽 회전과 반대
    public static void turnLeft(int num) {
        gear[num].add(gear[num].get(0));
        gear[num].remove(0);
    }
}
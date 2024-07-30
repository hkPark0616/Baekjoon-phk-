import java.util.*;

public class Main{
	public static void main(String[] args){        
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < T; t++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			List<Integer> list = new ArrayList<>();
			for(int i = 0; i < N; i++) {
				list.add(sc.nextInt());
			}
			
			int answer = 0;
			while(!list.isEmpty()) {
				boolean check = false;
				for(int i = 1; i < list.size(); i++) {
					if(list.get(0) < list.get(i)) {
						check = true;
					}
				}
				
				if(check) {
					int temp = list.get(0);
					list.remove(0);
					list.add(temp);
					
					M--;
					
					if(M < 0) {
						M = list.size() - 1;
					}
				}else {
					answer++;
					
					if(M == 0) {
						break;
					}
				
					M--;
					list.remove(0);
					
					if(M < 0) {
						M = list.size() - 1;
					}
					
				}
			}	
			sb.append(answer + "\n");		
		}
		System.out.println(sb);	
	}
}
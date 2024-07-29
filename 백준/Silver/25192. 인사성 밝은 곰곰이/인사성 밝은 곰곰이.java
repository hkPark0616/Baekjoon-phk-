import java.util.*;

public class Main{
	
	public static void main(String[] args){        
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int answer = 0;
		Set<String> set = new HashSet<>();
		
		for(int i = 0; i < N; i++) {
			String s = sc.next();
			
			if(s.equals("ENTER")) {
				set.clear();
			}
			else {
				if(!set.contains(s)) answer++;
				set.add(s);
			}
			
		}
		
		System.out.println(answer);
	}

}
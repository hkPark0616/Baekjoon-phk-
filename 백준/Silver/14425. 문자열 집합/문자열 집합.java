import java.util.*;

public class Main{
	public static void main(String[] args) {        
		Scanner sc = new Scanner(System.in);        
		
		int answer = 0;
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		Set<String> set = new HashSet<>();
		
		for(int i = 0; i < N; i++) {
			set.add(sc.next());
		}
		
		for(int i = 0; i < M; i++) {
			String s = sc.next();
			if(set.contains(s)) answer++;
		}
		
		System.out.println(answer);
	}
	
}
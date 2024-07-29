import java.util.*;

public class Main{
	public static void main(String[] args) {        
		Scanner sc = new Scanner(System.in);        
		
		int answer = 0;
		int N = sc.nextInt();
		
		for(int i = 0; i < N; i++) {
			String word = sc.next();
			
			Stack<Character> stack = new Stack<Character>();
			char temp = word.charAt(0);
			
			stack.add(temp);
			
			for(int j = 1; j < word.length(); j++) {
				char c = word.charAt(j);
				
				if(!stack.isEmpty() && stack.peek() == c) {
					stack.pop();
				}else {
					stack.push(c);
				}
				
			}
			
			if(stack.size() == 0) answer++;
		}
		
		System.out.println(answer);
	}
}
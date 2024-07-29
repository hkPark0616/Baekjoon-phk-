import java.util.*;

public class Main{
	public static void main(String[] args) {        
		Scanner sc = new Scanner(System.in);        
		
		int n = sc.nextInt();
		int last = 0;
		Queue<Integer> queue = new LinkedList<>();
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < n; i++) {
			String s = sc.next();
			
			switch(s) {
				case "push":
					int num = sc.nextInt();
					queue.add(num);
					last = num;
					break;
					
				case "pop":
					if(queue.isEmpty()) {
						sb.append(-1 + "\n");
					}else {
						sb.append(queue.poll() + "\n");
					}
					break;
					
				case "size":
					sb.append(queue.size() + "\n");
					break;
					
				case "empty":
					if(queue.isEmpty()) {
						sb.append(1 + "\n");
					}else {
						sb.append(0 + "\n");
					}
					break;
					
				case "front":
					if(queue.isEmpty()) {
						sb.append(-1 + "\n");
					}else {
						sb.append(queue.peek() + "\n");
					}
					break;
					
				case "back":
					if(queue.isEmpty()) {
						sb.append(-1 + "\n");
					}else {
						sb.append(last + "\n");
					}
					break;
			}
		}
		
		System.out.println(sb);
			
	}
	
}
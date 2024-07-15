import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		Queue<Integer> que = new LinkedList<>();
		for(int i = 0; i < n;i++) {
			que.add(i + 1);
		}
		
		sb.append("<");
		
		while(que.size() > 1) {
			for(int i = 0; i < k - 1; i++) {
				que.add(que.poll());
			}
			
			sb.append(que.poll() + ", ");
		}
		
		sb.append(que.poll() + ">");
		
		System.out.println(sb);

	}

}
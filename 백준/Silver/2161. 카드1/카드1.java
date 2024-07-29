import java.util.*;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		Queue<Integer> queue = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= N; i++) {
			queue.add(i);
		}
		
		for(int i = 0; i < N - 1; i++) {
			int rm = queue.remove();
			sb.append(rm + " ");
			int move = queue.poll();
			queue.add(move);
		}
		
		sb.append(queue.peek());
		
		System.out.println(sb);
	}
}
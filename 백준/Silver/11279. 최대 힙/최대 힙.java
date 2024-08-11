import java.util.*;

public class Main {
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
		int N = sc.nextInt();
		
		for(int i = 0; i < N; i++) {
			long num = sc.nextInt();
			if(num == 0) {
				if(pq.size() != 0) {
					sb.append(pq.poll() + "\n");
				}else {
					sb.append(0 + "\n");
				}
			}else {
				pq.offer(num);
			}
		}
		
		System.out.println(sb);
	}
	
}
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		int N = sc.nextInt();
		
		for(int i = 0; i < N; i++) {
			int input = sc.nextInt();
			if(input != 0) {
				pq.add(input);
			}else {
				if(pq.size() == 0) {
					sb.append(0 + "\n");
				}else {
					sb.append(pq.poll() + "\n");
				}
			}
		}
		
		System.out.println(sb);
	}

}
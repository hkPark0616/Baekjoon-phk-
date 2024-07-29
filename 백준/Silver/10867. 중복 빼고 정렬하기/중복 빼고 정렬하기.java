import java.util.*;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		Set<Integer> set = new HashSet<>();
		
		for(int i = 0; i < N; i++) {
			set.add(sc.nextInt());
		}
		
		List<Integer> list = new ArrayList<>(set);
		
		Collections.sort(list);
		
		for(int i: list) {
			System.out.print(i + " ");
		}
	}
}
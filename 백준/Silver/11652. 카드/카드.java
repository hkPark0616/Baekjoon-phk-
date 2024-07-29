import java.util.*;

public class Main{
	public static void main(String[] args) {        
		Scanner sc = new Scanner(System.in);        
		
		long answer = 0;
		int N = sc.nextInt();
		int max = 0;
		Map<Long, Integer> map = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			long num = sc.nextLong();
			
			if(map.containsKey(num)) {
				map.put(num, map.get(num) + 1);
				
			}else {
				map.put(num, 1);
			}
			max = Math.max(max, map.get(num));
		}
		
		List<Long> list = new ArrayList<>();
		for(long key: map.keySet()) {
			if(map.get(key) == max) list.add(key);
		}

		Collections.sort(list);
		answer = list.get(0);

		System.out.println(answer);
		
	}
}
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		Map<String, String> map = new HashMap<>();
		
		for(int i = 0; i < n; i++) {
			String name = sc.next();
			String state = sc.next();
			
			if(!map.containsKey(name) && state.equals("enter")) {
				map.put(name, state);
			}else {
				map.remove(name);
			}
		}
		
		Set<String> keySet = map.keySet();
		List<String> list = new ArrayList<>();
		
		for(String key: keySet) {
			list.add(key);
		}
		
		Collections.sort(list, Collections.reverseOrder());
		
		for(String s: list) {
			System.out.println(s);
		}	
	}
}
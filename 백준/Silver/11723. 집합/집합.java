import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		Set<String> set = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			String s = sc.next();
			String num = "";
			switch(s) {
				case "add":
					num = sc.next();
					set.add(num);
					break;
				case "remove":
					num = sc.next();
					set.remove(num);
					break;
				case "check":
					num = sc.next();
					if(set.contains(num)) {
						sb.append(1);
					}else {
						sb.append(0);
					}
					sb.append("\n");
					break;
					
				case "toggle":
					num = sc.next();
					if(set.contains(num)) {
						set.remove(num);
					}else {
						set.add(num);
					}
					break;
				case "all":
					set = new HashSet<>();
					for(int j = 1; j < 21; j++) {
						set.add(String.valueOf(j));
					}
					break;
	
				case "empty":
					set.clear();
					break;
			}
		}
		
		System.out.println(sb.toString());
	}

}

import java.util.*;

public class Main{
	
	public static void main(String[] args){        
		Scanner sc = new Scanner(System.in);
		
		String A = sc.next();
		int P = sc.nextInt();
		
		List<Integer> list = new ArrayList<>();
		list.add(Integer.valueOf(A));

		int temp = 0;
		String s = A;
		while(true) {
			int sum = 0;
			
			for(int i = 0; i < s.length(); i++) {
				int n = Character.getNumericValue(s.charAt(i));
				sum += Math.pow(n, P);
			}
			
			s = String.valueOf(sum);
			
			if(list.contains(sum)) {
				temp = sum;
				break;
			}else {
				list.add(sum);
			}

		}
		
		int find = list.indexOf(temp);
		
		System.out.println(find);
		
	}

}
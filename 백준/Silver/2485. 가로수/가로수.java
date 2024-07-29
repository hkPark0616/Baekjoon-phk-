import java.util.*;

public class Main{
	public static void main(String[] args) {        
		Scanner sc = new Scanner(System.in);        
		
		int N = sc.nextInt();
		List<Integer> inputList = new ArrayList<>();
		int answer = 0;
		
		for(int i = 0; i < N; i++) {
			int num = sc.nextInt();	
			inputList.add(num);
		}

		int space = 0;
		for(int i = 0; i < inputList.size() - 1; i++) {
			int dis = inputList.get(i + 1) - inputList.get(i);
			space = GCD(dis, space);
		}
		
		answer = (inputList.get(N - 1) - inputList.get(0)) / space + 1 - inputList.size();
		
		System.out.println(answer);
	}
	
	public static int GCD(int a, int b) {
		while(b > 0) {
			int mod = a;
			a = b;
			b = mod % a;
		}
		return a;
	}
}
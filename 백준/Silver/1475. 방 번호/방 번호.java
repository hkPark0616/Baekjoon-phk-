import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String n = sc.next();
		int[] arr = new int[9];
		
		for(int i = 0; i < n.length(); i++) {
			int num = n.charAt(i) - '0';
			
			if(num == 9) {
				num = 6;
			}
			
			arr[num]++;
		}
		
		arr[6] = arr[6] / 2 + arr[6] % 2;
		
		Arrays.sort(arr);
		
		System.out.println(arr[arr.length - 1]);
		
	}
}
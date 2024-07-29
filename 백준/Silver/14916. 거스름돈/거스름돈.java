import java.util.*;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] arr  = new int[n + 7];

		arr[0] = -1;
		arr[1] = -1;
		arr[2] = 1;
		arr[3] = -1;
		arr[4] = 2;
		arr[5] = 1;
		
		for(int i = 6; i < arr.length; i++) {
			if(arr[i - 2] == -1) {
				arr[i] = arr[i - 5] + 1;
			}else if(arr[i - 5] == -1) {
				arr[i] = arr[i - 2] + 1;
			}else {
				arr[i] = Math.min(arr[i - 2], arr[i - 5]) + 1;
			}
		}
		
		System.out.println(arr[n]);
	}
}
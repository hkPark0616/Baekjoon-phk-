import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String s = sc.next();
		String arr[] = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

		int cnt = 0;
		
		for(int i = 0; i < arr.length; i++) {
//			if(s.contains(arr[i])) {
//				s = s.replaceFirst(arr[i], " ");
//				//s = s.replace(" ", "");
//				cnt++;
//				i--;
//			}
			if(s.contains(arr[i])) {
				s = s.replace(arr[i], "@");
			}
			
		}
//		s = s.replace(" ", "");
//		cnt += s.length();
		cnt = s.length();

		
		System.out.println(cnt);
		
	}

}

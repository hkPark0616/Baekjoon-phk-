import java.util.*;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int answer = 0;
		
		String doc = sc.nextLine();
		String find = sc.nextLine();

		while(true) {
			if(!doc.contains(find)) break;
			doc = doc.replaceFirst(find, "#");
			answer++;
		}

		System.out.println(answer);
		
	}
}
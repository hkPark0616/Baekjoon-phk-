import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int answer = 0;
		
		while(true) {
			
			if(N % 5 == 0) {
				answer = answer + N / 5;
				break;
			}else if(N < 0){
				answer = -1;
				break;
			}
			
			N -= 3;
			answer++;
		}
		
		System.out.println(answer);
	}

}
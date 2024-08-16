import java.util.Scanner;

public class Main {
	public static int minCnt[] = new int[4]; // DNA 문자 최소 개수 조건
	public static int scnt[] = new int[4]; // 내 문자열 내 DNA 문자 개수 카운트
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int dnaS = sc.nextInt();
		int subS = sc.nextInt();
		int cnt = 0;

		String s = sc.next();
		char str[] = s.toCharArray(); // DNA 문자열
		
		for(int i = 0; i < 4; i++) {
			minCnt[i] = sc.nextInt();
		}
		
		for(int i = 0; i < subS; i++) {
			if(str[i] == 'A') scnt[0]++;
			
			if(str[i] == 'C') scnt[1]++;
			
			if(str[i] == 'G') scnt[2]++;
			
			if(str[i] == 'T') scnt[3]++;
		}
		

		if(check()) {
			cnt++;
		}
		
		for(int i = subS; i < dnaS; i++) { // 부분 문자열의 끝 위치
			int idx = i - subS; // 이전 부분 분자열의 시작 위치
			
			// 이전 부분 문자열의 시작 문자 제거
			if(str[idx] == 'A') scnt[0]--;
			if(str[idx] == 'C') scnt[1]--;
			if(str[idx] == 'G') scnt[2]--;
			if(str[idx] == 'T') scnt[3]--;
			
			// 이번 부분 문자열의 끝에 문자 추가
			if(str[i] == 'A') scnt[0]++;
			if(str[i] == 'C') scnt[1]++;
			if(str[i] == 'G') scnt[2]++;
			if(str[i] == 'T') scnt[3]++;
			
			
			if(check()) {
				cnt++;
			}

		}
		
		System.out.println(cnt);
	}
	
	public static boolean check() {
		for(int j = 0; j < 4; j++) {
			if(minCnt[j] > scnt[j]) {
				return false;
			}
		}
		return true;
	}
}

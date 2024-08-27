import java.io.*;
import java.util.*;

public class Main {
	static int L, C;
	static char[] input;
	static char[] arr;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		input = new char[C];
		arr = new char[L];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(input);

		comb(0, 0);

	}
	
	static void comb(int cnt, int start) {
		if(cnt == L) {		
			if(check(arr)) {
				System.out.println(arr);
			}
			return;
		}
		
		for(int i = start; i < C; i++) {
			arr[cnt] = input[i];
			comb(cnt + 1, i + 1);
		}
	}
	
	static boolean check(char[] arr) {
		int vowelCnt = 0, consonantCnt = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
				vowelCnt++;
			}else {
				consonantCnt++;
			}
		}
		
		if(vowelCnt >= 1 && consonantCnt >= 2) {
			return true;
		}else {
			return false;
		}
	}
	
}

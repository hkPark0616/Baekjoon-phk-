import java.io.*;
import java.util.*;


public class Main {
	static int N, R, C, answer, S, n;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		S = (int)Math.pow(2, N);
		
		answer = 0;

		divide(0, 0, S);
		
		System.out.println(answer);		
	}
	
	
	static void divide(int r, int c, int size) {
		
		if(size == 1) {
			return;
		}
		
		int tmpSize = size / 2;
		
		if(R < r + tmpSize && C < c + tmpSize) { // 1사분면
			divide(r, c, tmpSize);
		}
		else if(R < r + tmpSize && c + tmpSize <= C) { // 2사분면			
			answer += (size * size) / 4;
			divide(r, c  + tmpSize, tmpSize);			
		}
		else if(r + tmpSize <= R && C < c + tmpSize) { // 3사분면
			answer += ((size * size) / 4) * 2;
			divide(r + tmpSize, c, tmpSize);
		}
		else if(r + tmpSize <= R && c + tmpSize <= C) { // 4사분면			
			answer += ((size * size) / 4) * 3;
			divide(r + tmpSize, c + tmpSize, tmpSize);			
		}
		
	}

}

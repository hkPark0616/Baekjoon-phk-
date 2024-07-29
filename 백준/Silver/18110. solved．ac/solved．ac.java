import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws Exception{        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));     
        
		int answer = 0;
		int n = Integer.parseInt(br.readLine());
		
		List<Integer> list = new ArrayList<>();
		double sum = 0;
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			list.add(num);
			sum += num;
		}
		
		Collections.sort(list);
		
		int tm =  (int)Math.round(n * 0.15);
		double front = 0, back = 0;
		for(int i = 0; i < tm; i++) {
			front += list.get(i);
			back += list.get(list.size() - i - 1);
		}

		answer = (int)Math.round( (sum - front - back) / (list.size() - 2*tm));
		
		System.out.println(answer);
	}

}
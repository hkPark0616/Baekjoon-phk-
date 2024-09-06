import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			Set<String> set = new HashSet<>();
			List<Integer> result = new ArrayList<>();
			
			String str = br.readLine();
			List<Character> list = new ArrayList<>();
			for(int i = 0; i < str.length(); i++) {
				list.add(str.charAt(i));
			}
			
			for(int k = 0; k < N / 4; k++) {
				String temp = "";
				for(int i = 0; i < list.size(); i++) {
					temp += list.get(i);
					
					if(temp.length() == N / 4) {
						int val = Integer.parseInt(temp, 16);
						if(!result.contains(val)) {
							result.add(val);
						}
						temp = "";
					}
				}
				
				char tmp = list.get(list.size() - 1);
				list.remove(list.size() - 1);
				list.add(0, tmp);
			}
			
			Collections.sort(result, Collections.reverseOrder());
			
			System.out.println("#" + t + " " + result.get(K - 1));
		}
	}
}

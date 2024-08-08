import java.util.*;

public class Main {
	public static int N, M, answer;
	public static int arr[][];
	public static List<int[]> home, chicken;
	public static boolean visit[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		answer = Integer.MAX_VALUE;
		arr = new int[N][N];
		
		home = new ArrayList<>();
		chicken = new ArrayList<>();	
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int v = sc.nextInt();
				arr[i][j] = v;
				if(v == 1) {
					home.add(new int[] {i + 1, j + 1});
				}
				if(v == 2) {
					chicken.add(new int[] {i + 1, j + 1});
				}
			}
		}
		
		visit = new boolean[chicken.size()];
		
		back(0, 0);
		
		System.out.println(answer);
		
	}
	
	public static void back(int start, int depth) {
		if(depth == M) {
			int result = 0;
			
			for(int i = 0; i < home.size(); i++) {
				int temp = Integer.MAX_VALUE;
				int houseX = home.get(i)[0];
				int houseY = home.get(i)[1];
				
				for(int j = 0; j < chicken.size(); j++) {
					if(visit[j]) {
						int dis = Math.abs(houseX - chicken.get(j)[0]) + Math.abs(houseY - chicken.get(j)[1]);
						temp = Math.min(temp, dis);
					}
				}	
				result += temp;
			}	
            
			answer = Math.min(answer, result);
			return;	
		}
		
		for(int i = start; i < chicken.size(); i++) {
			visit[i] = true;
			back(i + 1, depth + 1);
			visit[i] = false;
		}
	}
}
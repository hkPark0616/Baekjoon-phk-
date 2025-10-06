import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static Set<Long> numbers = new HashSet<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= 9; i++) { // 0 ~ 9 한자릿수 시작
            dfs(1, i);
        }

        List<Long> results = new ArrayList<>(numbers);
        Collections.sort(results);

        if (N == 0) {
            System.out.println(0);
            return;
        }
        
        if(N > results.size()) System.out.println(-1);
        else System.out.println(results.get(N - 1));
    }

    // idx: 자릿수, num: 현재 수
    // 9876543210이 가장 큰 수 -> 최대 10자리
    static void dfs(int idx, long num) {
        if(idx > 10) return;

        numbers.add(num);
        
        for(int i = 0; i < 10; i++) {
            // num의 마지막 자리가 작은 숫자만 붙이기
            if(num % 10 > i) dfs(idx + 1, num * 10 + i);
        }
    }
}
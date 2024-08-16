import java.util.*;

public class Main {
    public static int n;
    static StringBuilder sb;
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();
        
        n = sc.nextInt();


        dfs(0, n);
        
        System.out.println(sb);
    }
    
    // 소수 판정
    public static boolean isPrime(int n) {
        if(n < 2) return false;
        
        for(int i = 2; i <= Math.sqrt(n); i++){
                if(n % i == 0) return false;
        }
    
        return true;
    }
    
    public static void dfs(int num, int cnt){
        if(cnt == 0) {
            sb.append(num + "\n");
        }
        
        for(int i = 1; i < 10; i++) {
            int result = num * 10 + i;
            
            if(n > 0 && isPrime(result)) {
                dfs(result, cnt - 1);
            }
        }
    }
}

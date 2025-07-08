import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String strA = br.readLine();
        String strB = br.readLine();

        char[] A = new char[strA.length() + 1];
        char[] B = new char[strB.length() + 1];
        int[][] dp = new int[strA.length() + 1][strB.length() + 1];

        for(int i = 1; i <= strA.length(); i++) A[i] = strA.charAt(i - 1);
        for(int i = 1; i <= strB.length(); i++) B[i] = strB.charAt(i - 1);

        List<Character> list = new ArrayList<>();
        
        
        for(int i = 1; i <= strA.length(); i++) {
            for(int j = 1; j <= strB.length(); j++) {
                if(A[i] == B[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    list.add(B[j]);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[strA.length()][strB.length()]);

        if(dp[strA.length()][strB.length()] > 0) {
            int i = strA.length();
            int j = strB.length();
            StringBuilder sb = new StringBuilder();
    
            while(i > 0 && j > 0) {
                if(A[i] == B[j]) {
                    sb.append(A[i]);
                    i--;
                    j--;
                } else if(dp[i - 1][j] > dp[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
    
            System.out.println(sb.reverse().toString());   
        }
    }
}
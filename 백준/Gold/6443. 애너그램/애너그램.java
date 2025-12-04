import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, len;
    static int[] cnt;
    static char[] result;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            String word = br.readLine();
            len = word.length();
            cnt = new int[26];
            result = new char[len];

            for(char c: word.toCharArray()) cnt[c - 'a']++;
            
            backtrack(0);
        }

        System.out.println(sb);
    }

    static void backtrack(int depth) {
        if(depth == len) {
            sb.append(result).append("\n");
            return;
        }

        for(int i = 0; i < 26; i++) {
            if(cnt[i] > 0) {
                cnt[i]--;
                result[depth] = (char)(i + 'a');
                backtrack(depth + 1);
                cnt[i]++;
            }
        }
    }
}
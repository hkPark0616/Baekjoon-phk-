import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static List<String> results;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            results = new ArrayList<>();

            dfs(1, "1");

            Collections.sort(results);

            for(String s: results) System.out.println(s);
            System.out.println();
        }
    }

    static void dfs(int idx, String str) {
        if(idx == N) {
            if(calc(str) == 0) results.add(str);
            return;
        }

        // '+'
        dfs(idx + 1, str + "+" + (idx + 1));
        // '-'
        dfs(idx + 1, str + "-" + (idx + 1));
        // ' '
        dfs(idx + 1, str + " " + (idx + 1));
    }

    static int calc(String str) {
        str = str.replaceAll(" ", "");

        int sum = 0; // 합
        String temp = ""; // 임시 숫자 문자열
        char op = '+';

        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if(Character.isDigit(c)) { // 숫자 처리
                temp += c;
            }

            if (!Character.isDigit(c) || i == str.length() - 1) { // 연산자
                int num = Integer.parseInt(temp);
                
                if(op == '+') sum += num;
                else if(op == '-') sum -= num;

                op = c;
                temp = "";
            }
        }

        return sum;
    }
}
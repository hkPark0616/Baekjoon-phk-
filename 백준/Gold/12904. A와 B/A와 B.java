import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();

        // 문자열 T를 S의 길이까지 줄여가며 진행(정방향은 너무 경우의 수가 많어..)
        while(T.length() > S.length()) {
            if(T.charAt(T.length() - 1) == 'A') {  // 마지막 문자가 A라면 A를 제거 (첫번째 연산 역으로)
                T = T.substring(0, T.length() - 1);
            } else { // 마지막 문자가 B라면 B를 제거 후 뒤집기(두번째 연산 역으로)
                T = T.substring(0, T.length() - 1);
                T = reverse(T);
            }
        }

        if(T.equals(S)) System.out.println(1);
        else System.out.println(0);
    }

    static String reverse(String s) {
        String rs = "";
        for(int i = s.length() - 1; i >= 0; i--) {
            rs += s.charAt(i);
        }
        return rs;
    }
}
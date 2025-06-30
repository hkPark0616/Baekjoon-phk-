import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
         StringBuilder T = new StringBuilder(br.readLine());

        // 문자열 T를 S의 길이까지 줄여가며 진행
        while (T.length() > S.length()) {
            // 마지막 문자가 'A'이면 제거만
            if (T.charAt(T.length() - 1) == 'A') {
                T.deleteCharAt(T.length() - 1);
            } else {
                // 마지막 문자가 'B'이면 제거 후 뒤집기
                T.deleteCharAt(T.length() - 1);
                T.reverse();
            }
        }

        if (T.toString().equals(S)) System.out.println(1);
        else System.out.println(0);
    }
}
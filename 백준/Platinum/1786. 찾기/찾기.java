import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static ArrayList<Integer> answer = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();  // 본문 문자열
        String p = br.readLine();  // 찾을 패턴

        KMP(s, p);  // KMP 알고리즘 수행

        // 결과 출력
        System.out.println(answer.size());
        for (int index : answer) {
            sb.append(index).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    // KMP 알고리즘 함수
    static void KMP(String str, String pattern) {
        int[] pi = getPi(pattern);  // 패턴의 접두사 테이블 생성
        int strLen = str.length();
        int patternLen = pattern.length();
        int j = 0;

        // 본문 문자열 탐색
        for (int i = 0; i < strLen; i++) {
            // 일치하지 않을 경우, pi 배열을 참조하여 j 위치 이동
            while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            // 일치할 경우
            if (str.charAt(i) == pattern.charAt(j)) {
                if (j == patternLen - 1) {
                    // 패턴 일치
                    answer.add(i - patternLen + 2);  // 1-based index
                    j = pi[j];  // j 값을 패턴 내부에서 이동
                } else {
                    j++;
                }
            }
        }
    }

    // 패턴의 접두사 테이블 (pi 배열) 생성 함수
    static int[] getPi(String pattern) {
        int patternLen = pattern.length();
        int[] pi = new int[patternLen];
        int j = 0;

        // 패턴을 탐색하여 pi 배열 작성
        for (int i = 1; i < patternLen; i++) {
            // 일치하지 않을 경우, j 값을 pi[j-1]로 이동
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            // 일치할 경우
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                pi[i] = j;
            }
        }

        return pi;
    }
}

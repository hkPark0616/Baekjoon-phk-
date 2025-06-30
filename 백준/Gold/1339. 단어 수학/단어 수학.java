import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        String[] words = new String[N];
        int[] weight = new int[26]; // 자릿수 별로 알파벳의 가중치를 저장할 배열 

        for(int i = 0; i < N; i++) {
            String word = br.readLine();
            words[i] = word;
        }

        for(String word: words) {
            int len = word.length();
            for(int i = 0; i < len; i++) {
                char c = word.charAt(i);
                weight[c - 'A'] += Math.pow(10, len - i - 1); // 자릿수가 높은게 가중치가 높음.
            }
        }

        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < 26; i++) {
            if(weight[i] > 0) {
                list.add(new int[] {i, weight[i]}); // 각 알파벳 A ~ Z의 인덱스와 가중치를 저장
            }
        }
        list.sort((a, b) -> b[1] - a[1]); // 가중치 내림차순

        int num = 9;
        int[] alphaNum = new int[26]; // 각 알파벳이 어떤 숫자를 지정 받았는지 저장
        for(int[] alpha: list) {
            int index = alpha[0];
            alphaNum[index] = num--; // 알파벳 index에 따라 숫자(num) 배정하고 줄임
        }

        int sum = 0;
        for(String word: words) {
            int cur = 0; // 누적
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                cur = cur * 10 + alphaNum[c - 'A'];
            }
            sum += cur;
        }

        System.out.println(sum);
    }
}
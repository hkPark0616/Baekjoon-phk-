import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int G = Integer.parseInt(br.readLine());

        // G : 성원이의 현재 몸무게의 제곱에서 성원이가 기억하고 있던 몸무게의 제곱을 뺀 것
        // a² - b² = G
        int left = 1;
        int right = 1;
        boolean answer = false;
        while(true) {
            long diff = (long) right * right - (long) left * left;

            if(diff == G) {
                System.out.println(right);
                answer = true;
            }

            if(diff < G) right++;
            else left++;

            if(left == right) break;
        }

        if(!answer) System.out.println(-1);
    }
}
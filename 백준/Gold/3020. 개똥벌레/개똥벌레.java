import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, H, cnt;
    static int min = Integer.MAX_VALUE;
    static int[] top, bottom;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        cnt = 0;
        
        top = new int[H + 1]; // 종유석
        bottom = new int[H + 1]; // 석순
        
        for(int n = 0; n < N/2; n++) {
            int b = Integer.parseInt(br.readLine());
            int t = Integer.parseInt(br.readLine());
            
            // 높이에 따른 종유석과 석순의 개수 저장
            top[t] += 1;
            bottom[b] += 1;
        }

        // 높이가 h 이상인 종유석, 석순의 개수를 세기 위해 역순으로 개수 누적
        for(int h = H - 1; h > 0; h--) {
            top[h] += top[h + 1];
            bottom[h] += bottom[h + 1];
        }

        // 1 ~ H 까지의 높이 별 충돌 수 계산
        int crash = 0;
        for(int h =1; h <= H; h++) {
            crash = bottom[h] + top[H - h + 1];
            if (crash < min) { // 최솟값 갱신이 되면, 개수는 다시 1개로 초기화
                min = crash;
                cnt = 1;
            } else if (crash == min) { // 최솟값과 동일한 구간 개수 증가
                cnt++;
            }
        }

        System.out.println(min + " " + cnt);
    }
}
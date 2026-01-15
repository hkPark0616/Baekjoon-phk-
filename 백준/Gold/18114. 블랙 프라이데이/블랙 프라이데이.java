import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // C 에 딱 맞게 물건들을 가져오면 전부 만 원에 판매하는 이벤트
        // 최대 3개 선택, 중복 X
        Arrays.sort(arr);

        // 1개 선택
        for(int i = 0; i < N; i++) {
            if(arr[i] == C) {
                System.out.println(1);
                return;
            }
        }

        // 2개 선택
        int l = 0, r = N - 1;
        while(l < r) {
            int sum = arr[l] + arr[r];

            if(sum == C) {
                System.out.println(1);
                return;
            } else if(sum < C) {
                l++;
            } else {
                r--;
            }
        }
        
        // 3개 선택(1개 고정하고 나머지 선택)
        for(int i = 0; i < N; i++) {
            int remain = C - arr[i];
            int left = i + 1, right = N - 1;

            // 나머지 선택
            while(left < right) {
                int sum = arr[left] + arr[right];

                if(sum == remain) {
                    System.out.println(1);
                    return;
                } else if(sum < remain) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        System.out.println(0);
    }
}
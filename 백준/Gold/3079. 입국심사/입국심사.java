import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static long M, max;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        Arrays.sort(arr);

        System.out.println(paramatricSearch());
    }

    static long paramatricSearch() {
        long left = 0;
        long right = M * max;
        long answer = Long.MAX_VALUE;
        
        while(left <= right) {
            long mid = (left + right) / 2;

            // 모든 심사대에 대해서 mid 시간에 처리할 수 있는 인원 수
            long sum = 0;
            for(int i = 0; i < N; i++) {
                long cnt = mid / arr[i];

                if(sum >= M) break;
                
                sum += cnt;
            }

            if(sum >= M) { // 처리할 수 있는 시간은 충분함. 줄여봄
                answer = Math.min(mid, answer);
                right = mid - 1;
            } else { // 부족함. 늘림
                left = mid + 1;
            }
        }

        return answer;
    }
}
import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        long count = 0;
        for(int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while(left < right) {
                int sum = arr[i] + arr[left] + arr[right];

                if(sum == 0) {
                    // left와 right가 같다는 것 -> 정렬되어 있으므로 left~right 사이의 모든 값이 전부 동일함
                    if(arr[left] == arr[right]) {
                        int cnt = right - left + 1;
                        count += (long) cnt * (cnt - 1) / 2; // 조합
                        break;
                    }

                    // left와 right가 다른 값일 경우, 중복된 값의 개수를 세서 경우의 수 계산
                    int leftNum = arr[left];
                    int rightNum = arr[right];
                    int leftCnt = 0, rightCnt = 0;
                    
                    while(left < right && arr[left] == leftNum) { // 왼쪽 값과 같은 개수
                        left++;
                        leftCnt++;
                    }

                    while(right >= left && arr[right] == rightNum) { // 오른쪽 값과 같은 개수
                        right--;
                        rightCnt++;
                    }

                    // 왼 * 오 => 가능한 모든 조합 개수
                    count += (long) leftCnt * rightCnt;
                } else if(sum < 0) left++;
                else right--;
            }
        }

        System.out.println(count);
    }
}
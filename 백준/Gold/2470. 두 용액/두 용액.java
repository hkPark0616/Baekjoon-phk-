import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int MIN = Integer.MAX_VALUE;
    static int[] solutions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        solutions = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(solutions);

        binarySearch();

    }

    static void binarySearch() {
        int start = 0;
        int end = N - 1;

        int ml = solutions[start];
        int mr = solutions[end];

        while(start < end) {
            int comb = solutions[start] + solutions[end];

            if(Math.abs(comb) < Math.abs(MIN)) {
                MIN = comb;
                ml = solutions[start];
                mr = solutions[end];
            }

            // 합이 음수라는 것은 값이 너무 작은 것이므로
            // 0에 가까워지려면 더 큰 수를 써야함 -> 오른쪽으로 이동
            if(comb < 0){
                start += 1;
            }
            // 합이 양수라는 것은 값이 너무 큰 것이므로
            // 0에 가까워지려면 더 작은 수를 써야함 -> 왼쪽으로 이동
            else{
                end -= 1;
            }
        }

        System.out.println(ml + " " + mr);
    }
}
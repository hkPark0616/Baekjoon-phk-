import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, C;
    static int[] houses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        houses = new int[N];

        for(int i = 0; i < N; i++){
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        int answer = binarySearch();

        System.out.println(answer);
    }

    static int binarySearch(){
        int start = 1; // 최소 1
        int end = houses[N - 1] - houses[0]; // 촤대
        int result = 0;
        while(start <= end){
            int mid = (start + end) / 2;

            if(canInstall(mid)){
                start = mid + 1;
                result = mid;
            }else{
                end = mid - 1;
            }
        }

        return result;
    }

    static boolean canInstall(int dist){
        int cnt = 1;
        int last = houses[0];

        for(int i = 1; i < N; i++){
            if(houses[i] - last >= dist){
                cnt++;
                last = houses[i];
            }
        }

        return cnt >= C;
    }
}
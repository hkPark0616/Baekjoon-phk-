import java.util.Arrays;
import java.util.Scanner;

public class Main {
        static int n;
        static int arr[];
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            StringBuilder sb = new StringBuilder();

            n = sc.nextInt();
            arr = new int[n];
            for(int i = 0;i < n;i++){
                arr[i] = sc.nextInt();
            }

            Arrays.sort(arr);
            int m = sc.nextInt();
            for(int i = 0;i < m;i++){
                int num = sc.nextInt();
                if(numSearch(num)) sb.append(1 + " ");
                else sb.append(0 + " ");
            }
            System.out.println(sb);
    }
        public static boolean numSearch(int num){

            int start = 0;
            int end = n - 1;
            while (start <= end){
                int mid = (start + end) / 2;
                if(arr[mid] == num){
                    return true;
                }

                if(arr[mid] < num){
                    start = mid + 1;
                }
                else{
                    end = mid - 1;
                }
            }
            return false;
        }
}

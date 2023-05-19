import java.util.Arrays;
import java.util.Scanner;

public class Main {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();

            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            Arrays.sort(a);

            int m = sc.nextInt();

            for(int i = 0;i < m;i++){
                int start = 0;
                int end = n-1;
                int b = sc.nextInt();
                int result = 0;
                while (start <= end){
                    int middle = (start + end) / 2;
                    if(a[middle] == b){
                        result = 1;
                        break;
                    }
                    if(a[middle] > b) end = middle - 1;
                    else start = middle + 1;
                }
                System.out.println(result);
            }

    }
}

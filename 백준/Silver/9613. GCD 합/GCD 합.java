import java.util.*;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for(int i = 0;i < n;i++){
            int m = sc.nextInt();
            long sum = 0;
            int arr[] = new int[m];

            for(int j = 0;j < m;j++){
                arr[j] = sc.nextInt();
            }

            for(int j = 0;j < arr.length;j++){
                for(int k = j+1;k < arr.length;k++){
                    sum += gcd(arr[j], arr[k]);
                }

            }
            System.out.println(sum);
        }
    }

    public static int gcd(int a, int b){
        while (b > 0){
            int mod = a;
            a = b;
            b = mod % b;
        }

        return a;
    }

}
import java.util.*;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int arr[] = new int[n];

        for(int i = 0;i < n;i++){
            arr[i] = sc.nextInt();
        }
        int count = 0;
        for(int i = n-2;i >=0;i--){
                if(arr[i] >= arr[i+1]){
                    int a = arr[i] - arr[i+1] + 1;
                    count += a;
                    arr[i] -= a;
                }
        }
        System.out.println(count);
    }
}

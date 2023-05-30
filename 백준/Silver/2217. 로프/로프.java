import java.util.*;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Integer arr[] = new Integer[n];
        for(int i = 0;i < n;i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);


        int max = 0;
        int sum = 0;
        for(int i = n-1;i >= 0;i--){
            sum = arr[i] * (n-i);
            if(sum > max) max = sum;
            //max = Math.max(max,arr[i] * (n-i));
        }

        System.out.println(max);
    }
}

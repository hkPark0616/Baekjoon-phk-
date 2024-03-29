import java.util.*;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr1[] = new int[n];
        Integer arr2[] = new Integer[n];

        for(int i = 0; i < n;i++){
            arr1[i] = sc.nextInt();
        }
        for(int i = 0; i < n;i++){
            arr2[i] = sc.nextInt();
        }

        Arrays.sort(arr1);

        Arrays.sort(arr2, Collections.reverseOrder());

        int result = 0;
        for(int i = 0;i < arr1.length;i++){
            result += arr1[i] * arr2[i];
        }

        System.out.println(result);

    }
}

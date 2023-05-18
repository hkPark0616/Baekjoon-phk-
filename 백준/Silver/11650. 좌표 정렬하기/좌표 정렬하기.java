import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[][] = new int[n][2];
        for(int i = 0;i < n;i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] s1, int[] s2) {
                if(s1[0] == s2[0]){
                    return s1[1] - s2[1];
                }
                else{
                    return s1[0] - s2[0];
                }

            }
        });

        for(int i = 0;i < arr.length;i++){
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
    }
}

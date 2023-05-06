import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long sum = 0;
        int arr[];

        arr = new int[sc.nextInt()];

        for(int i = 0;i<arr.length;i++){
            arr[i] = sc.nextInt();
        }

        int cluster = sc.nextInt();

        for(int j = 0;j < arr.length;j++){
            if(arr[j] >= cluster){
                if(arr[j] % cluster != 0){
                    sum += cluster * ((arr[j] / cluster) + 1);
                }
                else{
                    sum += cluster * (arr[j] / cluster);
                }
            } else if (arr[j] == 0) {
                continue;
            } else if (arr[j] < cluster) {
                sum += cluster;
            }
        }
        System.out.println(sum);

    }

}
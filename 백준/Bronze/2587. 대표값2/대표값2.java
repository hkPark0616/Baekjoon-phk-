
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[5];
        int sum = 0;
        int temp = 0;
        double middle = 5 / 2;
        for(int i = 0;i < arr.length;i++){
            arr[i] = sc.nextInt();
        }
        for(int i = 0;i < arr.length;i++){
            sum += arr[i];
        }
        for(int i = 0;i < arr.length;i++){
            for(int j = i+1;j < arr.length;j++){
                if(arr[i] > arr[j]){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }


        System.out.println(sum/arr.length);
        System.out.println(arr[2]);


    }
}
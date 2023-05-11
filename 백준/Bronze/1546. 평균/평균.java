import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int max = 0;
        double sum = 0;
        int arr[] = new int[N];
        for(int i = 0;i < N;i++){
            arr[i] = sc.nextInt();
        }
        for(int i = 0;i < arr.length;i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        for(int i = 0;i < arr.length;i++){
            sum += (double)arr[i] / max * 100;
        }

        System.out.println((double)sum / N);


    }
}
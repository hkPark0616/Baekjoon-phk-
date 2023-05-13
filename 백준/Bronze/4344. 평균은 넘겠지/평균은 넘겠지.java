import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int C = sc.nextInt();


        for(int i = 0;i < C;i++){
            double sum = 0;

            int N = sc.nextInt();
            int arr[] = new int[N];
            for(int j =0;j < N;j++){
                int score = sc.nextInt();
                arr[j] = score;
                sum += score;
            }
            double count = 0;
            double avg = sum / N;
            for(int j = 0;j < N;j++){

                if(arr[j] > avg){
                    count++;
                }
            }

            System.out.printf("%.3f%%\n", (count / N) * 100);
        }
    }
}
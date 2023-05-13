import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        for(int i = 0;i < N;i ++){
            int A = sc.nextInt();
            int B = sc.nextInt();

            int a = A;
            int b = B;

            while(b > 0){
                int mod = a;
                a = b;
                b = mod % b;
            }
            System.out.println(A * B / a);
        }
    }
}
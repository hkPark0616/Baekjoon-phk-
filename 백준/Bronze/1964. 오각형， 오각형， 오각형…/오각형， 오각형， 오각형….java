import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long sum = 0;
        int dot = 5;
        for(int i = 1; i <= N;i++){
            sum += dot;
            if(sum == 5){
                dot += 2;
            }
            else {
                dot +=3;
            }
        }
        System.out.println(sum % 45678);
    }
}
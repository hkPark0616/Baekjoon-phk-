import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 박스
        int M = sc.nextInt(); // 책
        int sum1 = 0;
        int sum2 = 0;
        for(int i = 0;i<N;i++){
            int box = sc.nextInt();
            sum1 += box;
        }
        for(int i = 0;i<M;i++){
            int book = sc.nextInt();
            sum2 += book;
        }

        System.out.println(sum1 -sum2);

    }

}
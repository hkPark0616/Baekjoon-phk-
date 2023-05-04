import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int sum = 0;
        int score = 0;
        for(int i = 0;i < N;i++){
            int j = sc.nextInt();
            if(j == 1){
                score++;
                sum +=score;
            }
            else{
                score=0;
            }
        }
        System.out.println(sum);


    }

}
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int score;
        int sum;
        for(int i = 0;i < N;i++){
            String quiz = sc.next();
            score = 0;
            sum = 0;
            for(int j = 0;j < quiz.length();j++){

                if(quiz.charAt(j) == 'O'){
                    score++;
                    sum = sum + score;
                }else {
                    score = 0;
                    sum = sum + score;

                }
            }

            System.out.println(sum);
        }


    }
}
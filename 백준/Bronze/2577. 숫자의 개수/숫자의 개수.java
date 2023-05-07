import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();

        sc.close();
        int sum = A * B * C;

        String count = Integer.toString(sum);

        for (int i = 0;i < 10;i++){
            int asd = 0;
            for(int j = 0;j < count.length();j++){
                if((count.charAt(j) - '0')==i){
                    asd += 1;
                }
            }
            System.out.println(asd);

        }


    }

}
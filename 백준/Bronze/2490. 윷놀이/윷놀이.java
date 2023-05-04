import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num[] = new int[4];
        int sum[] = new int[3];

        for(int i=0; i<3; i++){
            sum[i] = 0; // 초기화
            for(int j=0; j<4; j++){
                num[j] = sc.nextInt();
                sum[i] = sum[i] + num[j];
            }
        }
        for(int i=0; i<3; i++){
            if(sum[i] == 4){
                System.out.println("E");
            }
            else if(sum[i] == 3){
                System.out.println("A");
            }
            else if(sum[i] == 2){
                System.out.println("B");
            }
            else if(sum[i] == 1){
                System.out.println("C");
            }
            else{
                System.out.println("D");
            }
        }
    }
}
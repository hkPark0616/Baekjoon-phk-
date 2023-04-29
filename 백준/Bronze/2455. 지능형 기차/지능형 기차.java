import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int max = 0;
        int people = 0;
        int in = 0;
        int out = 0;

        for(int i=0;i<4;i++){
            out = sc.nextInt();
            in = sc.nextInt();
            people = people + in - out;
            if (max < people){
                max = people;
            }
        }
        System.out.println(max);
        
    }
}
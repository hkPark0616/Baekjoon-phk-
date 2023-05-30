import java.util.*;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int count = 0;
        if(n < 100){
            count = n;
        }
        else{
            count = 99;
            for(int i = 99;i <=n;i++){
                int one = i%10;
                int ten = (i/10) % 10;
                int hund = (i / 100);

                if((hund - ten) == (ten - one)){
                    count++;
                }
            }
        }

        System.out.println(count);

    }

}
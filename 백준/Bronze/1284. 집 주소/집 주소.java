import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while (true){
            int sum = 0;
            String N = sc.next();

            if(N.equals("0")) System.exit(0);
            for (int i=0;i<N.length();i++){
                String s = N.substring(i, i+1);
                if (s.equals("1")){
                    sum+=3;
                } else if (s.equals("0")) {
                    sum+=5;
                }else sum+=4;
            }
            System.out.println(sum+1);
        }
    }
}
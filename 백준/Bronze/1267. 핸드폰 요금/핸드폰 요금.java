import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();;
        int sum = 0;
        int youngsik = 0;
        int minsik = 0;
        for(int i=0;i<n;i++){
            int time = sc.nextInt();
            youngsik += ((time/30) + 1)*10;
            minsik += ((time/60) + 1)*15;
        }
        if (youngsik==minsik){
            System.out.println("Y M " + youngsik);
        } else if (youngsik > minsik) {
            System.out.println("M " + minsik);
        } else if (youngsik < minsik) {
            System.out.println("Y " + youngsik);
        }

    }
}
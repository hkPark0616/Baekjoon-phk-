import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int people = sc.nextInt();

        int max = 0;
        int money = 0;
        for(int i=0;i<people;i++){
            int first = sc.nextInt();
            int second = sc.nextInt();
            int third = sc.nextInt();

            if(first == second && second==third){
                money = 10000 + first * 1000;
            } else if ( first==second) {
                money = 1000 + first * 100;
            } else if (second==third) {
                money = 1000 + second * 100;
            } else if ( third == first) {
                money = 1000 + third * 100;
            }else {
                money = Math.max(first, Math.max(second, third)) * 100;
            }
            max = Math.max(max, money);
        }
        System.out.println(max);
    }
}
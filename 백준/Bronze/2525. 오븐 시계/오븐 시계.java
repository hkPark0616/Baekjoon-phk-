import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int hour = sc.nextInt();;
        int min = sc.nextInt();

        int time = sc.nextInt();
        
        hour = hour + time/60;
        min = min + time%60;

        if(min >= 60){
            hour++;
            min -= 60;
        }
        if (hour >= 24){
            hour -= 24;
        }

        System.out.println(hour + " " + min);
    }

}
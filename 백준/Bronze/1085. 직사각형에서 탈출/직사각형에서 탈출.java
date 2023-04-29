import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        int y = sc.nextInt();
        int w = sc.nextInt();
        int h = sc.nextInt();

        int x_2 = w - x;
        int y_2 = h - y;
        int min_x = 0;
        int min_y = 0;
        min_x = Math.min(x_2, x);
        min_y = Math.min(y_2, y);

        int min = 0;
        min = Math.min(min_x, min_y);
        System.out.println(min);
    }
}
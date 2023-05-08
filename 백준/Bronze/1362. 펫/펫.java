import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 1;

        while (true) {
            int o = sc.nextInt();
            int w = sc.nextInt();

            int rip = 0;

            if (o == 0 && w == 0) break;

            while (true) {
                String EF = sc.next();
                int n = sc.nextInt();

                if (EF.equals("#") && n == 0) break;

                if (EF.equals("F")) w += n;
                if (EF.equals("E")) w -= n;

                if (w <= 0) {
                    rip = 1;
                }

            }

            if (rip == 1) {
                System.out.println(count + " RIP");
            } else {
                if ((w > o / 2) && (w < o * 2)) {
                    System.out.println(count + " :-)");
                } else {
                    System.out.println(count + " :-(");
                }
            }
            count++;
        }
    }
}
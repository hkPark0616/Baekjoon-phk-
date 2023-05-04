import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.Math.sqrt;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine().trim();

        StringTokenizer st = new StringTokenizer(text, " ");

        System.out.println(st.countTokens());
    }

}
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        String B = sc.next();

        BigInteger a = new BigInteger(A, 2);
        BigInteger b = new BigInteger(B, 2);

        BigInteger sum = a.add(b);

        String result = sum.toString(2);

        System.out.println(result);
    }

}
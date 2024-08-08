
import java.io.*;


public class Main {
	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums =     br.readLine().split(" ");
        int a = Integer.parseInt(nums[0]);
        int b = Integer.parseInt(nums[1]);

        if (b < 45){
        	if (a == 0) {
        		a = 23;
        	} else {
        		a -= 1;
        	}
        	
            b = 60 - (45-b);
        } else {
            b -= 45;
        }
        System.out.printf("%s %s", a, b);
    }
}
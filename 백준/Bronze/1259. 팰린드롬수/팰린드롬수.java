import javax.swing.plaf.IconUIResource;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true){
            String text = sc.next();
            String test1[] = new String[text.length()];
            String test2[] = new String[text.length()];
            if(Integer.parseInt(text) == 0) break;
            boolean count = false;

            if(text.charAt(0) == '0') break;

            for(int i = 0;i < text.length();i++){
                test1[i] = String.valueOf(text.charAt(i));
            }
            int a = text.length()-1;
            for(int i = 0;i < text.length();i++){

                test2[i] = String.valueOf(text.charAt(a));
                a--;
            }

            for(int i = 0;i < text.length();i++){
                if(Integer.parseInt(test1[i]) == Integer.parseInt(test2[i])){
                    continue;
                }
                else{
                    count = true;
                }
            }

            if(count){
                System.out.println("no");
            }else{
                System.out.println("yes");
            }
        }




    }
}
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int count = 0;
        for(int i = 0;i < N;i++){
            String word = sc.next();
            boolean check[] = new boolean[26];
            boolean temp = true;

            for(int j = 0;j < word.length();j++){
                int index = word.charAt(j) -'a';
                if(check[index]){
                    if(word.charAt(j) != word.charAt(j-1)){
                        temp = false;
                        break;
                    }
                }
                else {
                    check[index] = true;
                }

            }
            if(temp)count++;

        }

        System.out.println(count);



    }

}

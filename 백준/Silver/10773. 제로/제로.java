import java.util.Scanner;
import java.util.Stack;

public class Main {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();

            Stack<Integer> stack = new Stack<>();

            for(int i = 0;i < n;i++){
                int a = sc.nextInt();
                if(a == 0){
                    stack.pop();
                }
                else{
                    stack.push(a);
                }

            }
            int sum = 0;
            for(int i = 0;i < stack.size();i++){
                sum += stack.get(i);
            }

            System.out.println(sum);

    }
}

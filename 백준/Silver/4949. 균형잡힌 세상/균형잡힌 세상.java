import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input_len, i;
        Stack<Character> stack = new Stack<>();
        char text;

        String input = br.readLine();
        while(!input.equals(".")) {
            stack.clear();

            input_len = input.length();
            for(i = 0; i < input_len; i++) {
                text = input.charAt(i);
                if(text == '(' || text == '[') {
                    stack.push(text);
                }
                else if(text == ')' || text == ']') {
                    if(stack.isEmpty() || (text == ')' && stack.peek() != '(') || (text == ']' && stack.peek() != '[')) {
                        stack.push(text);
                        break;
                    }

                    stack.pop();
                }
            }

            if(stack.isEmpty()) {
                System.out.println("yes");
            }
            else {
                System.out.println("no");
            }

            input = br.readLine();
        }
    }

}
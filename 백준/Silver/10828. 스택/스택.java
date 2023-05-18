import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Stack<Integer> li = new Stack<>();

        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < n;i++){

            String s = sc.next();

            switch(s){
                case "push":
                    int a = sc.nextInt();
                    li.push(a);
                    break;
                case "top":
                    if(li.isEmpty()){
                        sb.append(-1).append("\n");
                    }
                    else{
                        sb.append(li.peek()).append("\n");
                    }
                    break;

                case "size":
                    sb.append(li.size()).append("\n");
                    break;

                case "empty":
                    if(li.isEmpty()){
                        sb.append(1).append("\n");
                    }
                    else{
                        sb.append(0).append("\n");
                    }
                    break;
                case "pop":
                    if(li.isEmpty()){
                        sb.append(-1).append("\n");
                    }
                    else{
                        sb.append(li.pop()).append("\n");
                    }
                    break;
            }

        }
        System.out.println(sb);
    }
}

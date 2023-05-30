import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        Queue<Integer> que = new LinkedList<>();
        for(int i = 1;i <= num;i++){
            que.add(i);
        }

        while(true){
            if(que.size() == 1) break;
            que.remove();
            int value = que.remove();;
            que.add(value);
            
        }

        System.out.println(que.peek());
    }

}
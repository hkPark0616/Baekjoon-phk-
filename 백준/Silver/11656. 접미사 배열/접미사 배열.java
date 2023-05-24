import java.util.*;


public class Main {
    public static String temp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.next();

        ArrayList<String> list  = new ArrayList<>();
        
        for(int i = 0;i < text.length();i++){

            temp = text.substring(i, text.length());
            list.add(temp);
        }
        
        Collections.sort(list);

        for(int i = 0;i < list.size();i++){
            System.out.println(list.get(i));
        }
    }
}

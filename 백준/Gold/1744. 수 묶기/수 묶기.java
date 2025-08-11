import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Integer> list1 = new ArrayList<>(); // n > 0
        List<Integer> list2 = new ArrayList<>(); // n <= 0
        long result = 0; // n == 1

        for(int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            if(n == 1) {
                result++;
            } else if(n > 0) {
                list1.add(n);
            } else if(n <= 0) {
                list2.add(n);
            }
        }

        Collections.sort(list1, Collections.reverseOrder());
        Collections.sort(list2);

        for(int i = 0; i < list1.size(); i += 2) {
            if(i + 1 < list1.size()) {
                result += list1.get(i) * list1.get(i + 1);
            } else {
                result += list1.get(i);
            }
        }

        for(int i = 0; i < list2.size(); i += 2) {
            if(i + 1 < list2.size()) {
                result += list2.get(i) * list2.get(i + 1);
            } else {
                result += list2.get(i);
            }
        }

        System.out.println(result);
    }
}
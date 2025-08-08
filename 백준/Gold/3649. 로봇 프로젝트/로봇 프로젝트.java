import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static long x;
    static int n;
    static int[] lego;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = br.readLine();
            if (line == null) break;
            if (line.isEmpty()) break;
        
            x = Long.parseLong(line) * 10000000;
        
            n = Integer.parseInt(br.readLine());
            lego = new int[n];
        
            for (int i = 0; i < n; i++) {
                lego[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(lego);
            
            search();
        }

        System.out.println(sb);
    }

    static void search() {
        int start = 0;
        int end = n - 1;
        boolean found = false;

        while(start < end) {
            long sum = (long)lego[start] + lego[end];

            if(sum == x) {
                sb.append("yes ").append(lego[start] + " ").append(lego[end] + "\n");
                found = true;
                break;
            }else if(sum < x) {
                start++;
            } else {
                end--;
            }
        }

        if(!found) sb.append("danger").append("\n");
    }
}
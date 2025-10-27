import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static boolean[][] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        arr = new boolean[52][52];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char c1 = st.nextToken().charAt(0);
            st.nextToken();
            char c2 = st.nextToken().charAt(0);

            arr[getIdx(c1)][getIdx(c2)] = true;
        }

        floyd();
        
        List<String> results = new ArrayList<>();
        for(int i = 0; i < 52; i++) {
            for(int j = 0; j < 52; j++) {
                if(i == j) continue;
                if(arr[i][j]) {
                    results.add(getChar(i) + " => " + getChar(j));
                }
            }
        }

        // Collections.sort(results);
        
        sb.append(results.size()).append("\n");
        for(String result: results) sb.append(result).append("\n");

        System.out.println(sb);
    }

    static void floyd() {
        for(int k = 0; k < 52; k++) {
            for(int i = 0; i < 52; i++) {
                for(int j = 0; j < 52; j++) {
                    if(arr[i][k] && arr[k][j]) {
                        arr[i][j] = true;
                    }
                }
            }
        }
    }

    static int getIdx(char c) {
        if(Character.isUpperCase(c)) return c - 'A';
        else return (c - 'a') + 26;
    }

    static char getChar(int idx) {
        if (idx < 26) return (char) ('A' + idx);
        else return (char) ('a' + (idx - 26));
    }
}
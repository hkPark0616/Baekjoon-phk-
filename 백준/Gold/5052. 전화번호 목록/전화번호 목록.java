import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static class Node {
        Map<Character, Node> child;
        boolean endOfWord;

        public Node() {
            this.child = new HashMap<>();
            this.endOfWord = false;
        }
    }
    static class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
        }

        // 전화번호 삽입 중 접두어 관계 발견 시 false 반환
        public boolean insert(String str) {
            Node node = this.root;

            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if(node.endOfWord) return false; // 이미 끝난 단어를 거친다면 → 기존 번호가 접두어
                
                node.child.putIfAbsent(c, new Node());
                node = node.child.get(c);
            }

            // 마지막 글자 처리 후 자식이 있으면 → 현재 번호가 다른 번호의 접두어
            if(!node.child.isEmpty()) return false;
            
            node.endOfWord = true;

            return true;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            String[] nums = new String[N];

            for(int i = 0; i < N; i++) nums[i] = br.readLine();

            Arrays.sort(nums); // 짧은 번호를 먼저 삽입하기 위해서

            Trie trie = new Trie();
            boolean answer = true;

            for(String num: nums) {
                if(!trie.insert(num)) {
                    answer = false;
                    break;
                }
            }

            sb.append(answer ? "YES\n" : "NO\n");
        }

        System.out.println(sb);
        
    }
}
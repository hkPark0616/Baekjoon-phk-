import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static Node[] tree;
    static class Node{
        char value;
        Node left;
        Node right;

        public Node(char value){
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        tree = new Node[N + 1];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            char root = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            if(tree[root - 'A'] == null){
                tree[root -'A'] = new Node(root);
            }

            if(left != '.'){
                tree[left - 'A'] = new Node(left);
                tree[root -'A'].left = tree[left - 'A'];
            }

            if(right != '.'){
                tree[right - 'A'] = new Node(right);
                tree[root - 'A'].right = tree[right - 'A'];
            }
        }

        preorder(tree[0]);
        sb.append("\n");

        inorder(tree[0]);
        sb.append("\n");

        postorder(tree[0]);
        sb.append("\n");

        System.out.println(sb);
    }

    static void preorder(Node node){
        if(node == null) return;
        sb.append(node.value);
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(Node node){
        if(node == null) return;
        inorder(node.left);
        sb.append(node.value);
        inorder(node.right);
    }

    static void postorder(Node node){
        if(node == null) return;
        postorder(node.left);
        postorder(node.right);
        sb.append(node.value);
    }
}
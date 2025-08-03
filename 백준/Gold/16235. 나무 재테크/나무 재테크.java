import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, K;
    static int[][] add; // 겨울에 로봇이 추가할 양분
    static Cell[][] map;
    static List<DeadTree> deadTrees = new ArrayList<>();
    static class Cell {
        List<Tree> trees = new ArrayList<>(); // 각 칸 나무들
        int nutrition = 5; // 초기 영양 상태
    }
    static class Tree implements Comparable<Tree> {
        int age;
        
        public Tree(int age) {
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return Integer.compare(this.age, o.age);
        }
    }
    static class DeadTree {
        int x, y, age;

        public DeadTree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        add = new int[N][N];
        map = new Cell[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new Cell();
            }
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                add[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            map[x][y].trees.add(new Tree(z));
        }

        for(int k = 0; k < K; k++) {
            spring();
            summer();
            fall();
            winter();
        }

        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                cnt += map[i][j].trees.size();
            }
        }

        System.out.println(cnt);
    }

    static void spring() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                List<Tree> aliveTrees = new ArrayList<>();
                Collections.sort(map[i][j].trees);

                for(Tree tree: map[i][j].trees) {
                    int n = map[i][j].nutrition;
                    if(map[i][j].nutrition >= tree.age) {
                        map[i][j].nutrition -= tree.age;
                        tree.age++;
                        aliveTrees.add(tree);
                    } else {
                        deadTrees.add(new DeadTree(i, j, tree.age));
                    }
                }

                map[i][j].trees = aliveTrees;
            }            
        }
    }

    static void summer() {
        for(DeadTree tree: deadTrees) {
            map[tree.x][tree.y].nutrition += tree.age / 2;
        }

        deadTrees.clear();
    }

    static void fall() {
        int[][] deltas = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(Tree tree: map[i][j].trees) {
                    if(tree.age % 5 == 0) {
                        for(int[] delta: deltas) {
                            int ni = i + delta[0];
                            int nj = j + delta[1];

                            if(ni >= 0 && ni < N && nj >= 0 && nj < N) {
                                map[ni][nj].trees.add(new Tree(1));
                            }
                        }
                    }
                }
            }
        }
        
    }

    static void winter() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                map[i][j].nutrition += add[i][j];
            }
        }
    }
}
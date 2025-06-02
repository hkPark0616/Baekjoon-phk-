import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] seats;
    static int[][] students;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        seats = new int[N][N];
        students = new int[N * N][5];

        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                students[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N * N; i++) {
            seat(students[i]);
        }

        System.out.println(calculateSatisfaction());
    }

    static void seat(int[] fav) {
        int maxFav = -1;
        int maxEmpty = -1;
        int selX = -1, selY = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (seats[i][j] != 0) continue;

                int favCnt = 0;
                int emptyCnt = 0;

                for (int[] delta : deltas) {
                    int nx = i + delta[0];
                    int ny = j + delta[1];

                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                        if (check(fav, seats[nx][ny])) favCnt++;
                        if (seats[nx][ny] == 0) emptyCnt++;
                    }
                }

                if (favCnt > maxFav ||
                        (favCnt == maxFav && emptyCnt > maxEmpty) ||
                        (favCnt == maxFav && emptyCnt == maxEmpty && (i < selX || (i == selX && j < selY)))) {
                    maxFav = favCnt;
                    maxEmpty = emptyCnt;
                    selX = i;
                    selY = j;
                }
            }
        }

        seats[selX][selY] = fav[0];
    }

    static int calculateSatisfaction() {
        int total = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int student = seats[i][j];
                int[] fav = findFavorites(student);

                int cnt = 0;
                for (int[] delta : deltas) {
                    int nx = i + delta[0];
                    int ny = j + delta[1];

                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                        if (check(fav, seats[nx][ny])) cnt++;
                    }
                }

                if (cnt == 1) total += 1;
                else if (cnt == 2) total += 10;
                else if (cnt == 3) total += 100;
                else if (cnt == 4) total += 1000;
            }
        }

        return total;
    }

    static int[] findFavorites(int student) {
        for (int[] s : students) {
            if (s[0] == student) return s;
        }
        return null;
    }

    static boolean check(int[] fav, int n) {
        for (int i = 1; i < fav.length; i++) {
            if (fav[i] == n) return true;
        }
        return false;
    }
}
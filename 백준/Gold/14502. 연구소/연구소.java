
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;



public class Main {

	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<Node> newWall = new ArrayList<>();
	static List<Node> virus = new ArrayList<>();
	
	static final int[] dx = {-1,1,0,0};
	static final int [] dy = {0,0,-1,1};
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int n = read();
		int m = read();
		
		int [][] matrix = new int[n][m];
		for (int i=0;i<n;i++) {
			for (int j=0;j<m;j++) {
				matrix[i][j] = read(); // 공백 및 \n 기준으로 쭉 입력
				
				if (matrix[i][j]==0) {
					newWall.add(new Node(i,j));
					// 벽이 새롭게 깔릴 수 있는 곳
				}
				
				if (matrix[i][j]==2) {
					virus.add(new Node(i,j));
				}
			}
		}// end data input
		
		int max =0;
		
		// 모든 곳에 벽을 세우고 확산 시뮬레이션
		for (int i=0; i <newWall.size(); i++) { // 벽의 개수만큼
			for (int j =i+1; j<newWall.size(); j++) {
				for( int k=j+1; k<newWall.size();k++) {
					int[][] newMatrix = matrixClone(matrix, i,j,k);
					// 기존 빈구역의 갯수 - 새로생긴 벽 1,2,3 - 확산된 바이러스 개수
					max = Math.max(max,  newWall.size()-3-BFS(newMatrix));
				}
			}
			
		}
			
	
		System.out.println(max);
			
			
	} // end main
	
	//추가되는 바이러스의 수
	public static int BFS(int[][] matrix) {
		int count=0;
		Queue<Node> q = new ArrayDeque<>(virus);
		
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			
			for (int i=0; i<4;i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				
				
				if (nx <0 || ny < 0 || nx >= matrix.length || ny >= matrix[0].length) continue;
				if (matrix[nx][ny] != 0) continue;
				
				matrix[nx][ny] = 2;
				count ++;
				q.add(new Node(nx,ny)); 
				
			}
		} // end bfs
		
		return count;
	}  // end func
	
	
	
	public static int[][] matrixClone(int[][] matrix, int i, int j, int k) {
		int [][] newMatrix = new int[matrix.length][matrix[0].length];
		for (int row = 0; row < matrix.length;row++) {
			newMatrix[row] = matrix[row].clone();
		}
		
		newMatrix[newWall.get(i).x][newWall.get(i).y] =1;
		newMatrix[newWall.get(j).x][newWall.get(j).y] =1;
		newMatrix[newWall.get(k).x][newWall.get(k).y] =1;
		
		return newMatrix;
	}
	
	public static int read() throws IOException {
		int res = 0;
		while (true) {
			int r = br.read();
			
			if (r ==10 || r == 32) return res; // 10 공백 혹은 32 \n 인 경우 종료
			if (r==13) continue;
			res = 10 * res + r - 48; // 나온 수들을 순서대로 int로 바꿔서 정렬 // 당연히 0도 입력
		}
	}
	
	
	
	
	
	// 좌표를 받는 객체 생성
	public static class Node {
		int x,y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	

}

/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    static int N, arr[][];
	static String S;
	
	public static void main(String args[]) throws Exception
	{
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		//System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		/* Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt(); */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
			
        int T = Integer.parseInt(st.nextToken());
        
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
            st = new StringTokenizer(br.readLine());
				
            N = Integer.parseInt(st.nextToken());
            S = st.nextToken();

            arr = new int[N][N];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            switch(S) {
                case "right":
                    right();
                    break;
                case "left":
                    left();
                    break;
                case "up":
                    up();
                    break;
                case "down":
                    down();
                    break;
            }

            System.out.println("#" + test_case);
            for(int i = 0; i < N; i++) {					
                for(int j = 0; j < N; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }

		}
	}
    
    static void left() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N - 1; j++) {
				if(arr[i][j] != 0) continue;
				for(int k = j + 1; k < N; k++) {
					if(arr[i][k] != 0) {
						arr[i][j] = arr[i][k];
						arr[i][k] = 0;
						break;
					}
				}
			}
		}
		
		for(int i = 0; i < N ; i++) {
			for(int j = 1; j < N; j++) {
				if(arr[i][j - 1] == arr[i][j]) {
					arr[i][j - 1] *= 2;
					arr[i][j] = 0;
					j += 1;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N - 1; j++) {
				if(arr[i][j] != 0) continue;
				for(int k = j + 1; k < N; k++) {
					if(arr[i][k] != 0) {
						arr[i][j] = arr[i][k];
						arr[i][k] = 0;
						break;
					}
				}
			}
		}
	}
	
	static void right() {
		for(int i = 0; i < N; i++) {
			for(int j = N - 1; j > 0; j--) {
				if(arr[i][j] != 0) continue;
				for(int k = j - 1; k >= 0; k--) {
					if(arr[i][k] != 0) {
						arr[i][j] = arr[i][k];
						arr[i][k] = 0;
						break;
					}
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = N - 1; j > 0; j--) {
				if(arr[i][j - 1] == arr[i][j]) {
					arr[i][j] *= 2;
					arr[i][j - 1] = 0;
					j -= 1;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = N - 1; j > 0; j--) {
				if(arr[i][j] != 0) continue;
				for(int k = j - 1; k >= 0; k--) {
					if(arr[i][k] != 0) {
						arr[i][j] = arr[i][k];
						arr[i][k] = 0;
						break;
					}
				}
			}
		}
	}
	
	static void up() {
		// 먼저 위쪽 방향으로 비어있는 공간이 있으면 끌어올림
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N - 1; j++) {
				if(arr[j][i] != 0) continue;
				for(int k = j + 1; k < N; k++) {
					if(arr[k][i] != 0) {
						arr[j][i] = arr[k][i];
						arr[k][i] = 0;
						break;
					}
				}
			}
		}
		
		// 아래, 위 확인해서 같으면 합쳐주고, 옮긴 칸은 0으로 설정해줬으므로, 인덱스 추가로 +1 해줘야됨
		for(int i = 0; i < N; i++) {
			for(int j = 1; j < N; j++) {
				if(arr[j - 1][i] == arr[j][i]) {
					arr[j - 1][i] *= 2;
					arr[j][i] = 0;
					j += 1;
				}
			}
		}
		
		// 다시 0으로 바뀐 칸이 있을것이므로, 위로 땡겨줌
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N - 1; j++) {
				if(arr[j][i] != 0) continue;
				for(int k = j + 1; k < N; k++) {
					if(arr[k][i] != 0) {
						arr[j][i] = arr[k][i];
						arr[k][i] = 0;
						break;
					}
				}
			}
		}
	}
	
	static void down() {
		
		// 먼저 아래쪽 방향으로 비어있는 공간이 있으면 끌어내림
		for(int i = 0; i < N; i++) {
			for(int j = N - 1; j > 0; j--) {
				if(arr[j][i] != 0) continue;
				for(int k = j - 1; k >= 0; k--) {
					if(arr[k][i] != 0) {
						arr[j][i] = arr[k][i];
						arr[k][i] = 0;
						break;
					}
				}
			}
		}
		
		// 아래, 위 확인해서 같으면 합쳐주고, 옮긴 칸은 0으로 설정해줬으므로, 인덱스 추가로 -1 해줘야됨
		for(int i = 0; i < N; i++) {
			for(int j = N - 1; j > 0; j--) {
				if(arr[j - 1][i] == arr[j][i]) {
					arr[j - 1][i] = 0;
					arr[j][i] *= 2;
					j -= 1;
				}
			}
		}
		
		// 먼저 아래쪽 방향으로 비어있는 공간이 있으면 끌어내림
		for(int i = 0; i < N; i++) {
			for(int j = N - 1; j > 0; j--) {
				if(arr[j][i] != 0) continue;
				for(int k = j - 1; k >= 0; k--) {
					if(arr[k][i] != 0) {
						arr[j][i] = arr[k][i];
						arr[k][i] = 0;
						break;
					}
				}
			}
		}
	}
}
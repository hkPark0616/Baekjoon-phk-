import java.util.*;

public class Main{
	public static void main(String[] args) {        
		Scanner sc = new Scanner(System.in);        
		int[][] arr = new int [8][2];  
		int[] result = new int [5];        
		int sum = 0;         
		
		for(int i = 0; i < 8; i++){            
			arr[i][0] = sc.nextInt();            
			arr[i][1] = i + 1;        
			}         
		Arrays.sort(arr, (int[] a, int[] b) -> {            
			return b[0] - a[0];           
		});            
		
		for(int i = 0; i < 5; i++){            
			sum += arr[i][0];            
			result[i] = arr[i][1];        
		}         
		
		Arrays.sort(result);    
		
    
		System.out.println(sum);        
		for(int i = 0; i < 5; i++){            
			System.out.print(result[i] + " ");        
		}         

	}
	
}
package homework;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_d4_4366_정식이의은행업무_서울_06_신유진 {
	static char THREE[] = { '0', '1', '2' };
	static char two[];
	static char three[];
	static int ans;
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     
	     
	     int T = Integer.parseInt(br.readLine());
	      
	     for (int test_case = 1; test_case <= T; test_case++) {
	    	 two = br.readLine().toCharArray();
	    	 three = br.readLine().toCharArray();
	    	 
	    	 char[] res1, res2;
	    	 
	    	 for(int i=0; i<two.length; i++) {
	    		res1 = makeNum2(two, i);
	    		int ten1 = Integer.parseInt(String.valueOf(res1), 2);
	    		for (int j=0; j<three.length; j++) {
	    			for(int k=0; k<3; k++) {
	    				res2 = makeNum3(three, j, k);
	    				int ten2 = Integer.parseInt(String.valueOf(res2), 3);
	    				if(ten1==ten2) {
	    					ans = ten1;
	    				}
	    			}
	    			
	    		}
	    		 
	    	 }
	    	System.out.println("#"+test_case+" "+ans);
	     }
	}

	// 배열, 몇번째 자리 바꿀지
	static char[] makeNum2(char[] arr, int target) {
		char[] ret = arr.clone();
		if (ret[target] == '0')
			ret[target] = '1';
		else
			ret[target] = '0';
		return ret;
	}

	static char[] makeNum3(char[] arr, int target, int which) {
		char[] ret = arr.clone();
		ret[target] = THREE[which];
		return ret;
	}

}

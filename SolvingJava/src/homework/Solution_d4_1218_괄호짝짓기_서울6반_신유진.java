package homework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_d4_1218_괄호짝짓기_서울6반_신유진 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int tc=1; tc<=10; tc++) {
			int n = Integer.parseInt(br.readLine());
			char arr[] = br.readLine().toCharArray();
			Stack<Character> stack = new Stack<Character>();
			
			for(int i=0; i<n; i++) {
				if(stack.isEmpty()) stack.add(arr[i]);
				else if(arr[i]==')') {
					if(stack.peek()=='(') stack.pop(); 
				}
				else if(arr[i]-stack.peek()==2) {
					stack.pop();
				}
				else {
					stack.add(arr[i]);
				}
			}
			int ans = 0;
			if(stack.isEmpty()) ans = 1;
			System.out.println("#"+tc+" "+ans);
			
			
		}
		br.close();
	}

}

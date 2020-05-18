package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14405_피카츄 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String ans = "YES";
		int i = 0;
		while(i<s.length()) {
			if(i+1<s.length() && s.charAt(i)=='p' && s.charAt(i+1)=='i')
				i += 2;
			else if(i+1<s.length() && s.charAt(i)=='k' && s.charAt(i+1)=='a')
				i += 2;
			else if(i+2<s.length() && s.charAt(i)=='c' && s.charAt(i+1)=='h' && s.charAt(i+2)=='u')
				i += 3;
			else {
				ans = "NO";
				break;
			}
		}
		System.out.print(ans+"\n");
	}
	
}

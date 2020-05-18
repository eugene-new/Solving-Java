package homework;

import java.io.*;

public class Solution_d2_2007_패턴마디의길이_06반_신유진 {
	public static int min(int a, int b) {
		if(a<b) return a;
		else return b;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			char sample[] = br.readLine().toCharArray();
			String madi = "";
			madi += Character.toString(sample[0]);
			loop : for(int i=1; i<sample.length; i++) {
				if(madi.length()!=0 && madi.charAt(0)==sample[i]) {
					int cnt = 0;
					for(int j=0; j<min(madi.length(), sample.length-i); j++) {
						if(madi.charAt(j)==sample[i+j]) cnt++;
					}
					if(cnt==madi.length()) break loop;
				}
				madi+=Character.toString(sample[i]);
			}
			
			System.out.println("#"+tc+" "+madi.length());
		}
		br.close();
	}

}

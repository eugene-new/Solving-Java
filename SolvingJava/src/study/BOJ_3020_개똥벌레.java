package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_3020_개똥벌레 {
	static int n, h;
	static int[] top, bottom, totalBottom, totalTop, total;
	static int ans = Integer.MAX_VALUE;
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		top = new int[h+5];
		bottom = new int[h+5];
		totalBottom = new int[h+5];
		totalTop = new int[h+5];
		total = new int[h+5];
		for(int i=0; i<n/2; i++) {
			int b = Integer.parseInt(br.readLine());
			int t = Integer.parseInt(br.readLine());
			
			bottom[b]++;
			top[t]++;
		}
		// 구간합 구하기 
		for(int i=h; i>=1; i--) {
			totalBottom[i] = bottom[i] + totalBottom[i+1];
			totalTop[i] = top[i] + totalTop[i+1];
		}
		
		for(int i=1; i<=h; i++) {
	        total[i] = totalBottom[i] + totalTop[h - i + 1];
	 
	        if (total[i] <= ans) {
	        	if (total[i]==ans) {
	        		cnt++;
	        	}
	        	else {
	        		ans = total[i];
	        		cnt = 1;
	        	}
	        }
		}
		System.out.println(ans + " "+cnt);
	}

}

package homework;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_d3_2817_부분수열의합 {
	public static int arr[];
	//public static int sel[];
	public static int visit[];
	public static int n, k;
	public static int ans;
	public static void combi(int start, int depth, int r, int sum) {
		if(depth==r) {
			if(sum==k) ans++;
			return;
		}
		for(int i=start; i<n; i++) {
			if(visit[i]==1) continue;
			if(sum+arr[i]>k) continue;
			visit[i] = 1;
			combi(i, depth+1, r,sum+arr[i]);
			visit[i] = 0;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			arr = new int[n];
			visit = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			for(int i=1; i<=n; i++) {
				combi(0, 0, i, 0);
			}
			
			
			System.out.println("#"+tc+" "+ans);
			
			
			
			
		}
		

	}

}

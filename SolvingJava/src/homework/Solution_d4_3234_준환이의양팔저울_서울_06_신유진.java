package homework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_d4_3234_준환이의양팔저울_서울_06_신유진 {
	static int n;
	static int ans;
	static void init() {
		ans = 0;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			n = Integer.parseInt(br.readLine());
			int[] choo = new int[n];
			boolean[] visit = new boolean[n];
			init();
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				choo[i] = Integer.parseInt(st.nextToken());
			}
			dfs(0, 0, 0, choo, visit);
			bw.write("#"+tc+" "+ans+"\n");
		}
		bw.close();		
	}
	static void dfs(int depth, int left, int right, int[] choo, boolean[] visit) {
		if(depth==n) {
			ans++;
			return;
		}
		for(int i=0; i<n; i++) {
			if(visit[i]) continue;
			visit[i] = true;
			dfs(depth+1, left+choo[i], right, choo, visit);
			if(right+choo[i]<=left) {
				dfs(depth+1, left, right+choo[i], choo, visit);
			}
			
			visit[i] = false;
		}
	}
}

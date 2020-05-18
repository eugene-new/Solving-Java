package homework;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1681_해밀턴순환회로_서울_06_신유진 {
	
	static int n;
	static int cost[][];
	static int ans = Integer.MAX_VALUE;
	static void init() {
		visit = new boolean[n];
		cost = new int[n][n];
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		init();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				int weight = Integer.parseInt(st.nextToken());
				cost[i][j] = weight;
			}
		}
		
		dfs(1, 0, 0);
		System.out.println(ans);
	}
	static boolean visit[];
	static ArrayList<Integer> pick = new ArrayList<Integer>();
	static void dfs(int depth, int now, int total) {
		if(depth==n) {
			if(cost[now][0]!=0) {
				ans = Math.min(total+cost[now][0], ans);
			}
//			System.out.print(1+" ");
//			for(int i=0; i<pick.size(); i++) {
//				System.out.print((pick.get(i)+1)+" ");
//			}
//			System.out.println(" : "+total);
			return;
		}
		
		for(int i=1; i<n; i++) {
			if(visit[i]) continue;
			if(cost[now][i]==0) continue;
			if(total+cost[now][i]>=ans) continue;
			
			visit[i] = true;
			pick.add(i);
			dfs(depth+1, i, total+cost[now][i]);
			visit[i] = false;
			pick.remove(pick.size()-1);
		}

	}
}

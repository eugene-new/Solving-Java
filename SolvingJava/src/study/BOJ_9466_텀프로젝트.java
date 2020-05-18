package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9466_텀프로젝트 {
	static int student[];
	static boolean visit[];
	static boolean finish[];
	static int n, cnt, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			n = Integer.parseInt(br.readLine());
			visit = new boolean[n+1];
			finish = new boolean[n+1];
			student = new int[n+1];
			ans = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=n; i++) {
				student[i] = Integer.parseInt(st.nextToken());
			}
			
			
			for(int i=1; i<=n; i++) {
				dfs(i);
			}
			
			System.out.println(n-ans);
			
		}
	}
	
	static void dfs(int now) {
		visit[now] = true;
		int next = student[now];
		
		if(!visit[next]) {
			dfs(next);
		}
		else if(!finish[next]) {
			ans++;
			for(int i=next; i!=now; i=student[i]) {
				ans++;
			}
		}
		
		finish[now] = true;
	}
}

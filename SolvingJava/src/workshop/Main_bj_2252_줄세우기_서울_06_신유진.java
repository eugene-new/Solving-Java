package workshop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_bj_2252_줄세우기_서울_06_신유진 {
	static int n, m;
	static List<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static Stack<Integer> stack = new Stack<Integer>();
	static boolean[] visit;
	static void init() {
		visit = new boolean[n+1];
		for(int i=0; i<n+1; i++) {
			adj.add(new ArrayList<Integer>());
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<m; i++) {
			// A가 B의 앞에 선다 : A->B의 간선
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj.get(a).add(b);
		}
		
		for(int i=1; i<=n; i++) {
			if(visit[i]) continue;
			dfs(i);
		}
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+" ");
		}
		
	}
	static void dfs(int now) {
		visit[now] = true;
		for(int next : adj.get(now)) {
			if(visit[next]) continue;
			dfs(next);
		}
		stack.push(now);
		
	}
}

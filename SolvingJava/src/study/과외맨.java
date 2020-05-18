package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class 과외맨 {
	static int n, m, ans;
	static int path[]; // 경로 저장
	static int a[][], b[][];
	static boolean visit[];
	static ArrayList<ArrayList<Integer>> adj; // 인접 리스트
	static final int dy[] = { -1, 0, 1, 0 };
	static final int dx[] = { 0, 1, 0, -1 };

	static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(1);
		visit[1] = true;

		while (!q.isEmpty()) {
			int now = q.poll();
			for (int next : adj.get(now)) {
				if (visit[next])
					continue;
				ans = Math.max(ans, next);
				visit[next] = true;
				path[next] = now;
				q.offer(next);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = n * 2;
		path = new int[n * n + 5];
		visit = new boolean[n * n + 5];
		adj = new ArrayList<>();
		for (int i = 1; i <= n * n + 5; i++) {
			adj.add(new ArrayList<Integer>());
		}
		a = new int[n + 5][n * 2 + 5];
		b = new int[n + 5][n * 2 + 5];

		// 인접행렬 만들기
		int idx = 1;
		for (int i = 0; i < n; i++) {
			int k = i & 1;
			for (int j = k; j < m - k; j++) {
				a[i][j] = sc.nextInt();
				b[i][j] = idx;
				idx += ((j + k) & 1);
			}
		}

		// 인접리스트 만들기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int now = b[i][j];
				for (int k = 0; k < 4; k++) {
					int ny = i + dy[k];
					int nx = j + dx[k];
					if (ny < 0 || ny >= n || nx < 0 || nx >= m)
						continue;
					int next = b[ny][nx];
					// 건너갈 수 있는 타일을 넣어준다
					if (now != next && a[i][j] == a[ny][nx]) {
						adj.get(now).add(next);
					}
				}
			}
		}
		bfs();

		Stack<Integer> stack = new Stack<Integer>();
		int now = ans;
		stack.add(ans);
		while (path[now] != 0) {
			now = path[now];
			stack.add(now);
		}
		System.out.print(stack.size() + "\n");
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}

	}
}

package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class BOJ_9237_이장님초대 {
	static int n;
	static int ans;
	static Integer tree[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		tree = new Integer[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(tree, Collections.reverseOrder());
		
		for(int i=0; i<n; i++) {
			tree[i] = tree[i]+i+2;
			ans = Math.max(ans, tree[i]);
		}
		System.out.println(ans);
	}

}

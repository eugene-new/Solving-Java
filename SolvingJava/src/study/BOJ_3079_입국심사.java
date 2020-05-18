package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3079_입국심사 {
	static int n, m;
	static long time[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		time = new long[n];
		for (int i = 0; i < n; i++) {
			time[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(time);
		System.out.println(bSearch());

	}

	static long bSearch() {
		long low = 0;
		long high = m * time[n - 1];
		long result = high;
		
		while (low <= high) {
			long mid = (low + high) / 2;
			long sum = 0;
			
			for (int i = 0; i < n; i++) {
				sum += (mid / time[i]);
			}

			if (sum >= m) {
				result = Math.min(result, mid);
				high = mid - 1;
			} else {
				low = mid + 1;
			}
			
		}
		return result;
	}
}

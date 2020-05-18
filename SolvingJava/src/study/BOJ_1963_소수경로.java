package study;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1963_소수경로 {
	static final int Max = 10000;
	static boolean prime[] = new boolean[Max];
	static int dist[] = new int[Max];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int start, end;
		for(int tc=0; tc<T; tc++) {
			start = sc.nextInt();
			end = sc.nextInt();
			erato();
			bfs(start, end);
			
			// 만들 수 있음 
			if(dist[end]!=-1) {
				System.out.println(dist[end]);
			}
			else {
				System.out.println("Impossible");
			}
		}

	}
	static void erato() {
		Arrays.fill(prime, true);
		for(int i=2; i*i<Max; i++) {
			for(int j=i*i; j<Max; j+=i) {
				prime[j] = false;
			}
			
		}
	}
	static int[] toDigit(int num) {
		int[] numbers = new int[4];
		numbers[0] = num/1000;
		numbers[1] = num%1000/100;
		numbers[2] = num%100/10;
		numbers[3] = num%10;
		return numbers;
	}
	static int toInt(int[] num) {
		return num[0]*1000 + num[1]*100 + num[2]*10 + num[3];
	}
	static void bfs(int start, int end) {
		Queue<Integer> q = new LinkedList<Integer>();
		Arrays.fill(dist, -1);
		dist[start] = 0;
		int cnt = -1;
		while(!q.isEmpty()) {
			int now = q.poll();
			if(now==end) {
				if(cnt==-1 || cnt>dist[now]) cnt = dist[now];
				continue;
			}
			
			for(int i=0; i<4; i++) {
				for(int j=0; j<10; j++) {
					int digit[] = toDigit(now);
					digit[i] = j;
					int next = toInt(digit);
					if(next>=1000 && prime[next] && dist[next]==-1) {
						q.offer(next);
						dist[next] = dist[now]+1;
					}
				}
			}
		}
		
	}
}

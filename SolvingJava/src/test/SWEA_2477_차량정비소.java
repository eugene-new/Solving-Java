package test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class SWEA_2477_차량정비소 {
	static class Customer{
		int cNum, arrTime, wNum, a, b;

		public Customer(int cNum, int arrTime, int wNum, int a, int b) {
			super();
			this.cNum = cNum;
			this.arrTime = arrTime;
			this.wNum = wNum;
			this.a = a;
			this.b = b;
		}
		
		@Override
		public String toString() {
			return "Customer [cNum=" + cNum + ", arrTime=" + arrTime + ", wNum=" + wNum + ", a=" + a + ", b=" + b + "]";
		}
		
	}
	
	static class Worker{
		int workTime, when; // 걸리는 시간, 손님 받은 시간
		Customer customer;
		public Worker(int workTime, int when, Customer customer) {
			super();
			this.workTime = workTime;
			this.when = when;
			this.customer = customer;
		}
		@Override
		public String toString() {
			return "Worker [workTime=" + workTime + ", when=" + when + "]";
		}
		
	}
	static int n, m, k, A, B;
	static Queue<Customer> q0, q1, q2;
	static List<Customer> done;
	static Worker[] deskA, deskB;
	
	
	static void init() {
		deskA = new Worker[n+1];
		deskB = new Worker[m+1];
		done = new ArrayList<Customer>();
		q0 = new LinkedList<SWEA_2477_차량정비소.Customer>();
		q1 = new LinkedList<SWEA_2477_차량정비소.Customer>();
		q2= new PriorityQueue<SWEA_2477_차량정비소.Customer>(new Comparator<Customer>() {

			@Override
			public int compare(Customer o1, Customer o2) {

				if(o1.wNum>o2.wNum) return 1;
				else if(o1.wNum==o2.wNum) {
					if(o1.a>o2.a) return 1;
					return -1;
				}
				return -1;
			}
		});
	}
	public static void main(String args[]) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T;
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			init();
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=n; i++) {
				deskA[i] = new Worker(Integer.parseInt(st.nextToken()), -1,	null);
			}
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=m; i++) {
				deskB[i] = new Worker(Integer.parseInt(st.nextToken()), -1,	null);
			}
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=k; i++) {
				int arr = Integer.parseInt(st.nextToken());
				q0.offer(new Customer(i, arr, -1, -1, -1));
			}
			int curTime = 0;
			int finished = 0;
			while(finished!=k) {
				
				// 도착한 사람을 접수창구대기줄로 보내자 
				while(!q0.isEmpty() && q0.peek().arrTime==curTime) {
					q1.offer(q0.poll());
				}
				
				// 일하자 
				for(int i=1; i<=n; i++) {
					if(deskA[i].customer!=null && curTime-deskA[i].when==deskA[i].workTime) {
						Customer c = deskA[i].customer;
						deskA[i].customer = null;
						deskA[i].when = -1;
						c.wNum = curTime;
						q2.offer(c);
					}
				}
				for(int i=1; i<=m; i++) {
					if(deskB[i].customer!=null && curTime-deskB[i].when==deskB[i].workTime) {
						Customer c = deskB[i].customer;
						deskB[i].customer = null;
						deskB[i].when = -1;
						// 끝나는 고객!
						finished++;
						done.add(c);
					}
				}
				//접수창구 확인 
				while(!q1.isEmpty()) {
					int res = isAvailable(deskA, n);
					if(res==-1) break;
					Customer now = q1.poll();
					now.a = res;
					deskA[res].customer = now;
					deskA[res].when = curTime;
				}
				// 정비창구 확인 
				while(!q2.isEmpty()) {
					int res = isAvailable(deskB, m);
					if(res==-1) break;
					Customer now = q2.poll();
					now.b = res;
					deskB[res].customer = now;
					deskB[res].when = curTime;
				}
				curTime++;
			}
			
			int ans = 0;
			for (Customer c : done) {
				if(c.a==A && c.b==B) {
					ans += c.cNum;
				}
			}
			if(ans!=0) {
				System.out.println("#"+test_case+" "+ans);
			}
			else {
				System.out.println("#"+test_case+" "+("-1"));
			}
			
		}
	}
	// 가능한 작은 수의 창구번호로 
		static int isAvailable(Worker[] desk, int total) {
			for(int i=1; i<=total; i++) {
				if(desk[i].customer==null) return i;
			}
			return -1;
		}
}
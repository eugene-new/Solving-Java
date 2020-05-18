package homework;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 암호문3 {
	static class Node{
		int data;
		Node next;
		Node() {};
		Node(int data) {this.data=data;};
	}
	static class List{
		Node head = new Node();
		public boolean isEmpty()  {
			return head.next==null;
		}
		// x번째 노드 찾기
		Node find(int x) {
			Node now = head;
			for(int i=0; i<x; i++) {
				now = now.next;
			}
			return now;
		}
		void I(int x, int y, int[] nums) {
			
			Node now = find(x);
			Node tmp = now.next;
			for(int i=0; i<y; i++) {
				Node n = new Node(nums[i]);
				now.next = n;
				now = now.next;
			}
			now.next = tmp;
		}
		
		void D(int x, int y) {
			if(isEmpty()) return;
			Node start = find(x);
			Node now = start.next;
			for(int i=0; i<y; i++) {
				now = now.next;
			}
			start.next = now;
		}
		
		void A(int y, int[] nums) {
			Node now = head.next;
			while(now.next!=null) {
				now = now.next;
			}
			for(int i=0; i<y; i++) {
				now.next = new Node(nums[i]);
			}
		}
		
		void addOne(int x) {
			Node now = head.next;
			if(isEmpty()) {
				head.next = new Node(x);
				return;
			}
			while(now.next!=null) {
				now = now.next;
			}
			now.next = new Node(x);
		}
		void print() {
			Node now = head.next;
			for(int i=0; i<10; i++) {
				System.out.print(now.data+" ");
				now = now.next;
			}
		}
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/암호문3.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for(int tc=1; tc<=10; tc++) {
			List list = new List();
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int i=0; i<n; i++) {
				int input = Integer.parseInt(st.nextToken());
				list.addOne(input);
			}
			
			int order = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int i=0; i<order; i++) {
				String s = st.nextToken();
				if(s.equals("I")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					int ch[] = new int[y];
					for(int j=0; j<y; j++) {
						ch[j] = Integer.parseInt(st.nextToken());
					}
					list.I(x, y, ch);
				}
				else if(s.equals("D")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					list.D(x,  y);
				}
				else if(s.equals("A")) {
					int y = Integer.parseInt(st.nextToken());
					int ch[] = new int[y];
					for(int j=0; j<y; j++) {
						ch[j] = Integer.parseInt(st.nextToken());
					}
					list.A(y, ch);
				}
			}
			System.out.println("#"+tc+" ");
			list.print();
			System.out.println();
		}
	}

}

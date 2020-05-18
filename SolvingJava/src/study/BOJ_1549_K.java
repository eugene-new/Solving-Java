package study;

import java.util.Scanner;

public class BOJ_1549_K {
	static long INF = 1000000000*3000+1;
	static int n;
	static long ans[];
	static int A[];
	static long P[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		A = new int[n+1];
		P = new long[n+1];
		ans = new long[2];
		ans[0] = INF; ans[1] = INF;
		long sum = 0;
	    for(int i=0; i<n; i++) {
	        A[i] = sc.nextInt();
	        sum += A[i];
	        P[i] = sum;
	    }

	    for(int k=1; k<=n/2; k++) {
	        for(int i=0; i<n; i++) {
	            long one;
	            if(i==0) {
	                one = P[i+k-1];
	            }
	            else {
	                one = P[i+k-1]-P[i-1];
	            }
	            for(int j=i+k; j<n; j++) {
	                if(j+k>n) break;
	                long two = P[j+k-1]-P[j-1];
	                long tmp = Math.abs(one-two);
	                if(tmp < ans[1]) {
	                    ans[0] = k; ans[1] = tmp;
	                }
	                else if(tmp==ans[1]) {
	                    ans[0] = k>ans[0]?k:ans[0];
	                }
	            }
	        }
	    }
	    
	    System.out.println(ans[0]+"\n"+ans[1]);
	    

	}

}

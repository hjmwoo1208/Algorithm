import java.util.*;
public class BJ_2579 {
	public static void main(String[] args) {	
		int N,temp1, temp2;
		int[] score,dp;
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dp	 = new int[N+2];
		score = new int[N+2];
		
		for(int i=1;i<=N;i++)
			score[i] = sc.nextInt();
		
		dp[0] = 0;
		dp[1] = score[1];
		dp[2] = score[1] + score[2];
		
		for(int i=3;i<=N;i++){
			temp1 = score[i] + score[i-1] + dp[i-3]; //1칸 아래에서 올라왔을 때
			temp2 = score[i] + dp[i-2]; // 2칸 아래에서 올라왔을 때 
			dp[i] = Math.max(temp1, temp2);
			}
		System.out.println(dp[N]);
	}//main
}//class
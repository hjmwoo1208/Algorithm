import java.io.*;
public class BOJ_1562 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long	dp[][][] = new long[N+1][10][1024];
		int mod = 1000000000;
		for(int i=1;i<10;i++) dp[1][i][0|1<<i] = 1;
		for(int i=1; i<N;i++)
			for(int bit=1;bit<1024;bit++)
				for(int num=0;num<10;num++) {
					if(num==0) dp[i+1][1][bit|1<<1] = (dp[i+1][1][bit|1<<1] + dp[i][0][bit])%mod;
					else if(num==9) dp[i+1][8][bit|1<<8] = (dp[i+1][8][bit|1<<8]+ dp[i][9][bit])%mod;
					else{
						dp[i+1][num-1][bit|1<<(num-1)] = (dp[i+1][num-1][bit|1<<(num-1)] + dp[i][num][bit])%mod;
						dp[i+1][num+1][bit|1<<(num+1)] = ( dp[i+1][num+1][bit|1<<(num+1)] +dp[i][num][bit])%mod;
					}
				}
		long sum = 0;
		for(int i=0;i<10;i++) sum += (dp[N][i][1023]%mod);
		System.out.println(sum%mod);
	}
}
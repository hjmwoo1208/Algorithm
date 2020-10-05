import java.io.*;
public class BOJ_1562 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long	dp[][][] = new long[N+1][10][1024]; //0~9의 flag를 나타내려면 1111111111(2) = 1023(10)
		int mod = 1000000000;
		for(int i=1;i<10;i++) dp[1][i][0|1<<i] = 1; //0으로 시작할 수 없다.
		for(int i=1; i<N;i++)
			for(int bit=1;bit<1024;bit++)
				for(int num=0;num<10;num++) {
					if(num==0) dp[i+1][1][bit|1<<1] = (dp[i+1][1][bit|1<<1] + dp[i][0][bit])%mod; //0일 때는 1만 가능
					else if(num==9) dp[i+1][8][bit|1<<8] = (dp[i+1][8][bit|1<<8]+ dp[i][9][bit])%mod; //9일때는 8만가능
					else{//1~8은 각각의 -1,+1한 숫자가 가능
						dp[i+1][num-1][bit|1<<(num-1)] = (dp[i+1][num-1][bit|1<<(num-1)] + dp[i][num][bit])%mod;
						dp[i+1][num+1][bit|1<<(num+1)] = ( dp[i+1][num+1][bit|1<<(num+1)] +dp[i][num][bit])%mod;
					}
				}
		long sum = 0;
		for(int i=0;i<10;i++) sum += (dp[N][i][1023]%mod); //0~9로 끝나는 숫자의 갯수
		System.out.println(sum%mod);
	}
}
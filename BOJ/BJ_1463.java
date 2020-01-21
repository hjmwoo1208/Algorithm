import java.util.*;
/*
 * 1로 만들기
 * */
public class BJ_1463 {

//	public static int min = -1;
//	public static void dp (int n, int cnt, int tmp)
//	{
//		if(n == tmp)
//		{
//			if(min < 0) min = cnt;
//			else min = Math.min(cnt, min);
//		}
//		else
//		{
//			if(tmp*3 <= n)
//				dp(n,cnt+1,tmp*3);
//			if(tmp*2 <= n)
//				dp(n,cnt+1,tmp*2);
//			if(tmp+1 <=n)
//				if((min >0) && (min >= cnt))	dp(n,cnt+1,tmp+1);
//		}//else
//	}

		
//	public static int dp(int n)
//	{
//		if((n==2) || (n==3))
//			return 1;
//		else
//		{
//			int temp =-1;
//			
//			if(n%3 == 0) temp =  dp(n/3) +1 ;
//			
//			if(n%2 == 0)
//			{
//				if (temp == -1) temp = dp(n/2) +1 ;
//				else temp  = Math.min(dp(n/2)+1, temp);
//			}
//			
//			int temp1 = dp(n-1) +1 ;
//			
//			if(temp == -1) temp = temp1;
//			else temp = Math.min(temp, temp1);
//				
//			return temp;
//		}//else
//	}//dp

//	public static int minSet[];
//	
//	public static int dp(int n)
//	{
////		if(n == 2 || n==3)
////			return 1;
////		else
////		{
//		
//		if (n == 1)
//			return 0;
//		
//		if(minSet[n]>0)
//			return minSet[n];
//		
//			int temp = 0;
//			int result = 0;
//			
//			if(n%3 == 0)
//			{
//				if(minSet[n/3] == 0)
//					temp = dp(n/3) +1;
//				else
//					temp = minSet[n/3] +1;
//			}
//			
//			if(n%2 == 0)
//			{
//				if(minSet[n/2] ==0) // 없을 때
//				{
//					int tmp = dp(n/2) +1;
//					if (temp == 0) temp =tmp;
//					else temp = Math.min(tmp, temp);
//				}
//				else //최솟값 있을 때
//				{
//					if (temp == 0) 
//						temp = minSet[n/2] +1;
//					else
//						temp = Math.min(minSet[n/2]+1, temp); //temp가 0일 때 고려 안함
//				}
//			}
//			
//			if(minSet[n-1] !=0)
//			{
//				if(temp != 0)
//					temp =Math.min(temp, minSet[n-1]+1);
//				else
//					temp = minSet[n-1] +1;
//			}
//			else
//			{
//				int tmp = dp(n-1) +1;
//				if(temp != 0)
//					temp =Math.min(temp, tmp);
//				else
//					temp = tmp;
//			}
//			minSet[n] = temp;
//			return minSet[n];
////		}
//		
//	}//dp
	
	
	
//	static int[] min;
//	
//	private static int dp (int n)
//	{
//		if(n == 1)
//			return 0;
//		
//		if(min[n] > 0) //<--이거 추가했다고 시간이 확 줄었음....
//			return min[n];
//		
//		int temp = dp(n-1) +1;
//		if(min[n]==0) min[n] = Math.min(temp,min[n]);
//		
//		if (n % 3 == 0) 
//		{
//			int temp = dp(n / 3) +1 ;
//			min[n] = Math.min(temp, min[n]);
//		}
//		if (n % 2 == 0) 
//		{
//			int temp = dp(n / 2) +1;
//			min[n] = Math.min(temp,min[n]);
//		}
//		
//		return min[n];
//
//	}
/* int temp = dp(n/2) +1 ;  --> 이렇게 했을 땐 안남
 * int temp = 1 + dp(n/2) ; --> 이렇게 했을 때 stack overflow 났는데
 * 
 * */
	
	
////	//다시 짜보자
//	private static int[] min;
//	private static int dp(int n) //<--맞음... 왜 맞는지 모르겠음
//	{
//		if(n == 0 || n==1)
//			return 0;
//		if(min[n] > 0)
//			return min[n];
//		
//		if(n%3==0)
//		{
//			if(min[n/3] > 0)
//				if(min[n] == 0) 	min[n] = min[n/3] +1;
//				else min[n] = Math.min(min[n/3]+1, min[n]);
//			else
//			{
//				int tmp = dp(n/3) +1;
//				if(min[n]> 0) min[n] = Math.min(tmp, min[n]);
//				else min[n] = tmp;
//			}
//		}
//		if(n%2 == 0)
//		{
//			if(min[n/2] > 0)
//				if(min[n]== 0)	min[n] = min[n/2] +1;
//				else min[n] = Math.min(min[n/2]+1, min[n]);
//			else
//			{
//				int tmp =  dp(n/2) +1;
//				if(min[n]>0) 	min[n] = Math.min(tmp, min[n]);
//				else min[n] = tmp;
//			}
//		}
//		
//		if(min[n-1] > 0)
//			if(min[n] == 0) min[n] = min[n-1] +1;
//			else min[n] =  Math.min(min[n-1] +1, min[n]);
//		else
//		{
//			int tmp = dp(n-1) +1;
//			if(min[n]>0) min[n] = Math.min(tmp, min[n]	);
//			else min[n]=tmp;
//		}
//		
//		return min[n];
//		
//	} //<-- 100000에선 오류

	public static int[] min;
	
	private static int dp(int n) //<<---얘도 맞음....
	{
//		try
//		{
		if(n == 1)
			return 0;
		
		if(min[n] > 0)
			return min[n];
		

		int tmp;
		if(n%3 == 0)
		{
			//int temp = min[n/3]+1;
			//if(min[n] > temp)	min[n] = temp;
			tmp =  dp(n/3) +1;
			if(min[n] > 0) 
				{if(min[n]>tmp) {min[n] = tmp;}}
				//min[n] = Math.min(tmp,min[n]);
			else min[n] = tmp;
		}
		if(n%2 == 0)
		{
//			int temp = min[n/2]+1;
//			if(min[n]>temp) min[n] = temp;
			tmp = dp(n/2) +1;
			if(min[n]>0)
				{if(min[n]>tmp) {min[n] = tmp;}}
				// min[n] = Math.min(tmp, min[n]);
			else min[n] = tmp;
		}
		
		
		
		//min[n] = dp(n-1) +1;
		tmp = dp(n-1)+1;
		if(min[n]>0) //min[n]	= Math.min(tmp, min[n]);
			{if(min[n]>tmp){min[n] = tmp;}}
		else min[n] = tmp;
		
//		}
//		catch(StackOverflowError e)
//		{
//			System.out.println(e.toString());
//			System.out.println(n);
//		}
		return min[n];
	}
	
	

	public static void main(String[] args) {
//		minSet[2] = 1;
//		minSet[3] = 1;
		
		int N;
	
		Scanner sc = new Scanner(System.in);
		N = Integer.parseInt(sc.nextLine());
		
		//d = new int[N+1];
		min = new int [N+1];
		//dp(N, 0, 1);
		System.out.println(dp(N));

	}

}

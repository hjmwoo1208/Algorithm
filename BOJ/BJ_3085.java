/*
 * 백준 3085 : 사탕게임
 * 맞음
 * for문의 범위 확인해야
 */

import java.util.*;

public class BJ_3085 {

	public static void main(String[] args) {
		int N;
		int max = 1;
		char candy[][] = new char[51][51];
		Scanner sc = new Scanner(System.in);
		
		N = Integer.parseInt(sc.nextLine());
		
		String str;
		for(int n = 0;n<N;n++)
		{
			str = sc.nextLine();
			for(int m=0;m<N;m++)
				candy[n][m] = str.charAt(m);
		}
		
		
		
		//아래랑 같을 때
		//----입력 끝
		
		int cnt = 1;
		for(int n =0;n<N;n++)
		{
			for(int m=0;m<N-1;m++)
			{
				if(candy[n][m] == candy[n][m+1]) //오른쪽이랑 같을 때
				{
					cnt = 1;
//					for(int c = 1; c<N-m;c++)
//					{
//						if(candy[n][m] == candy[n][m+c])
//							//cnt += c;
//						else 
//						{
//							m += c;
//							break;
//						}
//					}
					for(int d = m+1;d<N;d++)
					{
						if(candy[n][m]==candy[n][d])
							cnt++;
						else
						{
							m = d;
							break;
						}
					}
					if(cnt>max)
						max = cnt;
				}				
			}
			for(int m=0;m<N-1;m++)
			{
				if(candy[m][n] == candy[m+1][n]) //아래
				{
					cnt = 1;
//					for(int c = 1; c<N-m;c++)
//					{
//						if(candy[m][n] == candy[m+c][n])
//							cnt += c;
//						else 
//						{
//							m += c;
//							break;
//						}
//					}
					for(int d = m+1;d<N;d++)
					{
						if(candy[m][n] == candy[d][n])
							cnt++;
						else
						{
							m = d;
							break;
						}
					}
					if(max<cnt)
						max = cnt;
				}
			}
		} //같은 거 먼저 확인
		
		//-----바꾸기 시작
		
		char temp;
		int sum;
		for(int n=0;n<N;n++) //<---여기서 틀렸음 n<N-1이라고함 그럼 끝에는 못 세니깐 틀림
		{
			for(int m=0;m<N-1;m++)
			{
				//----------------------------------가로로 바꿨을 때 
				temp = candy[n][m];
				candy[n][m] = candy[n][m+	1];
				candy[n][m+1] = temp;
				
				for(int s=0;s<2;s++) //위아래
				{
					cnt =1;
					if((n-1) >= 0) //위로
					{
						for(int a = n-1; a >=0;a--)
						{
							if(candy[n][m+s] == candy[a][m+s])
							{
								cnt++;
							}
							else
								break;
						}
					}//위에
					if((n+1) <N) //아래
					{
						for(int a = n+1; a<N;a++)
						{
							if(candy[n][m+s] == candy[a][m+s])
							{
								cnt++;
							}
							else
								break;
						}
					}//아래
					
					if(max<cnt)
						max = cnt;
				}//
				
				
				for(int s=0;s<2;s++) //왼,오
				{
					cnt =1;
					if((m+s-1) >= 0) //왼
					{
						for(int a = m+s-1; a >=0;a--)
						{
							if(candy[n][m+s] == candy[n][a])
								cnt++;
							else
								break;
						}
					}//위에
					if(candy[n][m] != candy[n][m+1])
					{
						if(max<cnt)
							max=cnt;
						cnt=1;
					}
					if((m+s+1) <N) //오
					{
						for(int a = m+s+1; a<N;a++)
						{
							if(candy[n][m+s] == candy[n][a])
							{
								cnt++;
							}
							else
								break;
						}
					}//아래
					if(max<cnt)
						max = cnt;
				}//
				
				temp = candy[n][m];
				candy[n][m] = candy[n][m+	1];
				candy[n][m+1] = temp;
				//--------------------------------------------가로로

			} //갯수 for m
			
			for(int m=0;m<N-1;m++)
			{
				//-----------------------------------------세로로
				temp = candy[m][n];
				candy[m][n] = candy[m+1][n];
				candy[m+1][n] = temp;
				
				for(int s=0; s<2;s++) //왼, 오
				{
					cnt=1;
					if((n-1)>=0)//왼
					{
						for(int d=n-1;d>=0;d--)
						{
							if(candy[m+s][n]==candy[m+s][d])
								cnt++;
							else
								break;
						}
					}
					if(n+1<N)
					{
						if(candy[m+s][n] != candy[m+s][n+1]) //왼쪽이랑 같은지 판단
						{
							if(max<cnt)
								max = cnt;
							cnt=1;
						}
						
						for(int d=n+1;d<N;d++)
						{
							if(candy[m+s][n]==candy[m+s][d])
								cnt++;
							else
								break;
						}
						
						if(max< cnt)
							max = cnt;
					}
				}
				
				if((m-1)>=0)//위
				{
					cnt =1;
					for(int d = m-1; d>=0;d--) //위로 
					{
						if(candy[m][n] == candy[d][n])
							cnt++;
						else
							break;
					}
					if(candy[m][n] != candy[m+1][n]) //위아래 같지 않으면
					{
						if(max<cnt)
							max = cnt;
						cnt=1;
					}
					
					for(int d=m+1;m<N;d++) //아래
					{
						if(candy[m][n] == candy[d][n])
							cnt++;
						else
							break;
					}
					if(max<cnt)
						max = cnt;
				}
				
				temp = candy[m][n];
				candy[m][n] = candy[m+1][n];
				candy[m+1][n] = temp;
				
				//--------------------------------------------
			}
			
		}//갯수 for n
		
		System.out.println(max);
		
	}//main

}

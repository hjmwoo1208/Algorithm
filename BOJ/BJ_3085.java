/*
 * ���� 3085 : ��������
 * ����
 * for���� ���� Ȯ���ؾ�
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
		
		
		
		//�Ʒ��� ���� ��
		//----�Է� ��
		
		int cnt = 1;
		for(int n =0;n<N;n++)
		{
			for(int m=0;m<N-1;m++)
			{
				if(candy[n][m] == candy[n][m+1]) //�������̶� ���� ��
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
				if(candy[m][n] == candy[m+1][n]) //�Ʒ�
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
		} //���� �� ���� Ȯ��
		
		//-----�ٲٱ� ����
		
		char temp;
		int sum;
		for(int n=0;n<N;n++) //<---���⼭ Ʋ���� n<N-1�̶���� �׷� ������ �� ���ϱ� Ʋ��
		{
			for(int m=0;m<N-1;m++)
			{
				//----------------------------------���η� �ٲ��� �� 
				temp = candy[n][m];
				candy[n][m] = candy[n][m+	1];
				candy[n][m+1] = temp;
				
				for(int s=0;s<2;s++) //���Ʒ�
				{
					cnt =1;
					if((n-1) >= 0) //����
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
					}//����
					if((n+1) <N) //�Ʒ�
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
					}//�Ʒ�
					
					if(max<cnt)
						max = cnt;
				}//
				
				
				for(int s=0;s<2;s++) //��,��
				{
					cnt =1;
					if((m+s-1) >= 0) //��
					{
						for(int a = m+s-1; a >=0;a--)
						{
							if(candy[n][m+s] == candy[n][a])
								cnt++;
							else
								break;
						}
					}//����
					if(candy[n][m] != candy[n][m+1])
					{
						if(max<cnt)
							max=cnt;
						cnt=1;
					}
					if((m+s+1) <N) //��
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
					}//�Ʒ�
					if(max<cnt)
						max = cnt;
				}//
				
				temp = candy[n][m];
				candy[n][m] = candy[n][m+	1];
				candy[n][m+1] = temp;
				//--------------------------------------------���η�

			} //���� for m
			
			for(int m=0;m<N-1;m++)
			{
				//-----------------------------------------���η�
				temp = candy[m][n];
				candy[m][n] = candy[m+1][n];
				candy[m+1][n] = temp;
				
				for(int s=0; s<2;s++) //��, ��
				{
					cnt=1;
					if((n-1)>=0)//��
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
						if(candy[m+s][n] != candy[m+s][n+1]) //�����̶� ������ �Ǵ�
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
				
				if((m-1)>=0)//��
				{
					cnt =1;
					for(int d = m-1; d>=0;d--) //���� 
					{
						if(candy[m][n] == candy[d][n])
							cnt++;
						else
							break;
					}
					if(candy[m][n] != candy[m+1][n]) //���Ʒ� ���� ������
					{
						if(max<cnt)
							max = cnt;
						cnt=1;
					}
					
					for(int d=m+1;m<N;d++) //�Ʒ�
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
			
		}//���� for n
		
		System.out.println(max);
		
	}//main

}

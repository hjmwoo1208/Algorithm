import java.util.*;

// ���� ��ȣ ���̱� - ����
// BFS �������

public class BJ_2667 {

	public static void main(String[] args) {
		int N;
		int map[][] = new int[26][26];
		boolean check[][] = new boolean[26][26];
		for(int a=0;a<26;a++)
			for(int b=0;b<26;b++)
				check[a][b] = false;
		int gCnt = 0;//���� ����
		int totalCnt[] = new int[500]; //���� �� ���� �� ����
		int cnt = 0;//������ ���� �� �� �� 
		
		Scanner sc = new Scanner(System.in);
		N = Integer.parseInt(sc.nextLine());
		
		//StringTokenizer stz;
		String str;
		for(int i=0;i<N;i++)
		{
			str = sc.nextLine();
			//stz = new StringTokenizer(str," ");
			for(int j=0;j<N;j++)
			{
				map[i][j] = str.charAt(j)-'0';
				if(map[i][j] == 1)
					check[i][j] = true;
			}
		}
		
		Queue<int[]> q;
		int temp[] = {0,0};
		int dx[] = {0,1,0,-1};
		int dy[] = {-1,0,1,0};
		int temp2[];
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				if((map[i][j] == 1) && (check[i][j] == true))
				{
					cnt =0; // ���� �� ���� �� �� �� ; �ʱ�ȭ
					temp[0] = i;
					temp[1] = j;
					q = new LinkedList<int[]>();
					q.offer(temp);
					check[i][j] = false;
					cnt ++;
					
					while(!(q.isEmpty()))
					{
						temp =  q.poll();
						for(int a=0;a<4;a++)
						{
							if(((temp[0]+dy[a])>=0)&&((temp[0]+dy[a])<N)&&((temp[1]+dx[a])>=0)&&((temp[1]+dx[a])<N))
								if((map[temp[0]+dy[a]][temp[1]+dx[a]]==1)&&(check[temp[0]+dy[a]][temp[1]+dx[a]])==true)
								{
									temp2 = new int[2];
									temp2[0] = temp[0]+dy[a];
									temp2[1] = temp[1]+dx[a];
									q.offer(temp2);
									check[temp[0]+dy[a]][temp[1]+dx[a]] = false;
									cnt++;
								}
						}
					}
					totalCnt[gCnt] = cnt;
					gCnt++;
				}
			}
		}// i
		
		System.out.println(gCnt);
		//totalCnt ����
		int swapTemp;
		for(int a=(gCnt-1);a>0;a--)
			for(int b=0;b<a;b++)
				if(totalCnt[b]>totalCnt[b+1])
				{
					swapTemp = totalCnt[b+1];
					totalCnt[b+1] = totalCnt[b];
					totalCnt[b] = swapTemp;
				}
		//println
		for(int a=0;a<gCnt;a++)
			System.out.println(totalCnt[a]);

	}//main
}

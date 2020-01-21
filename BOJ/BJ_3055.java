import java.util.*;

/* 백준 3055 : 탈출
 *  queue나 stack에 배열 삽입할 때 무조건 새로 선언해서 넣어줘야됨!!!!!!!!!!!!
 */

public class BJ_3055 {
	
	public static void main(String[] args) {
		int map[][] = new int[51][51];
		int min  = Integer.MAX_VALUE;
		int R, C; 
		boolean check[][] = new boolean[51][51];
		int water[][] = new int[2501][2];
		int S[]= new int[2];
		int watercnt = 0;
		
		Scanner sc = new Scanner(System.in);
		StringTokenizer stz;
		
		stz = new StringTokenizer(sc.nextLine()," ");
		
		R = Integer.parseInt(stz.nextToken());
		C = Integer.parseInt(stz.nextToken());
				
		/* 
		 * 1 = 물
		 * 2 = 돌
		 * 3 = 비버
		 * 4 = 고슴도치
		 */
		String str;
		for(int i = 0; i<R;i++)
		{
			str = sc.nextLine();
			for(int j=0;j<C;j++)
			{
				if(str.charAt(j) == 'S')
				{
					map[i][j] = -1;
					S[0] = i;
					S[1] = j;
					check[i][j]	 = true;
				}
				else if(str.charAt(j) == '.')
					map[i][j] = -1;
				else if(str.charAt(j) == 'X')
					map[i][j] = -2;
				else if(str.charAt(j) == '*')
				{
					map[i][j] = 0;
					water[watercnt][0] = i;
					water[watercnt][1] = j;
					watercnt++;
				}
				else if(str.charAt(j) == 'D')
					map[i][j] = -3;
			}
		}
		//---- 입력 끝 ------
		
		//long start = System.currentTimeMillis();

		Queue<int[]> waterq = new LinkedList<int[]>();
		
		int sec = 0;
		int dx[] = {0,-1,0,1};
		int dy[] = {-1,0,1,0};
		
		int tempint;
		int tempw[] = new int[3];
		int temp[] = new int[3];
		
		for(int w=0;w<watercnt;w++)
		{
			tempw = new int[3]; //여기 선언안해서 에러남!!!!!!!!!!!
			tempw[0] = 0;
			tempw[1] = water[w][0];
			tempw[2]	 = water[w][1];
			
			waterq.offer(tempw);
		}
		
		while(!(waterq.isEmpty()))
		{
			tempw = new int[3];
			tempw = waterq.poll();
			
			for(int d=0;d<4;d++)
			{
				if ( ((tempw[2] + dx[d]) >= 0) && ((tempw[2] + dx[d]) < C) && ((tempw[1] + dy[d]) >= 0) && ((tempw[1] + dy[d]) < R) )
					if(map[(tempw[1] + dy[d])][(tempw[2] + dx[d])] == -1)
					{
						map[(tempw[1] + dy[d])][(tempw[2] + dx[d])] = tempw[0]+1;
						
						temp = new int[3];
						temp[0] = tempw[0]+1;
						temp[1] = tempw[1] + dy[d];
						temp[2] = tempw[2] + dx[d];
						
						waterq.offer(temp);
					}
			}//for						
		}//Water while
		
		//long end = System.currentTimeMillis();
		//System.out.println( "Time1 : " + ( end - start )/1000.0 );
		
		Queue<int[]> routeQ = new LinkedList<int[]>();
		temp[0] = 0;
		temp[1] = S[0];
		temp[2] = S[1];
		
		routeQ.offer(temp);
		
		check[S[0]][S[1]] = true;
		
		while(!(routeQ.isEmpty()))
		{
			temp = new int[3];
			temp = routeQ.poll();
			
			for(int d = 0;d<4;d++)
			{
				if (((temp[2] + dx[d]) >= 0) && ((temp[2] + dx[d]) < C) && ((temp[1] + dy[d]) >= 0) && ((temp[1] + dy[d]) < R))
					if(((temp[0]+1)<map[temp[1] + dy[d]][temp[2] + dx[d]]) || map[temp[1] + dy[d]][temp[2] + dx[d]] == -1)
					{
						if(check[temp[1] + dy[d]][temp[2] + dx[d]] == false)
						{
							tempw = new int[3];
							tempw[0] = temp[0] +1;
							tempw[1] = temp[1] + dy[d];
							tempw[2] = temp[2] + dx[d];
							
							routeQ.offer(tempw);
							check[temp[1] + dy[d]][temp[2] + dx[d]] = true;
						}
					}
					else if(map[temp[1] + dy[d]][temp[2] + dx[d]] == -3)//비버 동굴
					{
						if(min> (temp[0] +1))
							min = temp[0] +1;
					}
			}
		}
		
		if(min == Integer.MAX_VALUE)
			System.out.println("KAKTUS");
		else
			System.out.println(min);
		
	}//main
}
/*  14503번 - 로봇청소기 -성공 */

import java.util.*;

public class BJ_14503_2019 {

	public static void main(String[] args) {
		int N, M,r,c;
		int d;
		int map[][] = new int[52][52]; //0 빈칸, 1 벽, 2 청소
		int py[]	 = {-1,0,1,0};
		int px[] = {0,1,0,-1};
		int cnt = 0;
		Scanner sc = new Scanner(System.in);
		StringTokenizer stz;
		
		stz = new StringTokenizer(sc.nextLine()," ");
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		stz = new StringTokenizer(sc.nextLine()," ");
		r = Integer.parseInt(stz.nextToken());
		c = Integer.parseInt(stz.nextToken());
		d = Integer.parseInt(stz.nextToken());
		for(int n=0;n<N;n++)
		{
			stz = new StringTokenizer(sc.nextLine()," ");
			for(int m=0;m<M;m++)
				map[n][m] = Integer.parseInt(stz.nextToken());
		}
		
		int possible = 0;
		while(true)
		{
			if(possible == 4)//4번 돌음
			{
				if(map[r+py[(d+2)%4]][c+px[(d+2)%4]] == 1) //뒤가 벽
					break;
				else 
				{
					possible = 0;
					r = r+py[(d+2)%4];
					c = c+px[(d+2)%4];
				}
			}
			else
			{
				if(map[r][c] == 0)
				{
					map[r][c] = 2;
					cnt++;
				}
				d = (d+3)%4;
				if(map[r+py[d]][c+px[d]] == 0)
				{
					possible = 0;
					r = r+py[d];
					c = c+px[d];
				}
				else
					possible++;
			}
		}//while
		System.out.println(cnt);
	}//main
}

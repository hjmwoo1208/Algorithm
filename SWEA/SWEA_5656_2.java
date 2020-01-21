/* 5656번 - 벽돌 깨기 */

import java.util.*;
public class SWEA_5656_2 {
	static int map[][];
	static int MIN=-1;
	static int N, W, H;
	
	static void dfs(int cnt, int[] list){
		if(N== cnt) {
//			if(list[0] ==2 && list[1] ==2 && list[2] ==6)
//			{
//				System.out.println(226);
//			}
			game(list);
		}
		else
		{
			int[] thisList = new int[cnt+2];
			for(int i =0;i<cnt;i++)
				thisList[i] = list[i];
			
			for(int i=0;i<W;i++)
			{
				thisList[cnt] = i;
				dfs(cnt+1,thisList);
			}
		}
	}//dfs
	
	static void game(int[] list)
	{
		int py[] = {-1,0,1,0};
		int px[] = {0,1,0,-1};
		
		Vector<Integer> vec = new Vector<Integer>();
		int thisMap[][];
		thisMap = new int[H+1][W+1];
		for(int h=0;h<H;h++)
			for(int w=0;w<W;w++)
				thisMap[h][w] = map[h][w];
		
		for(int n=0;n<N;n++)
		{
			for(int h=0;h<H;h++)
			{
				if(thisMap[h][list[n]]>0)
				{
					if(thisMap[h][list[n]] == 1)
						thisMap[h][list[n]] = 0;
					else
					{
						int tempIn[] = new int[3]; 
						tempIn[0] = h;
						tempIn[1] = list[n];
						tempIn[2] = thisMap[h][list[n]];
						Queue<int[]> q = new LinkedList<int[]>();
						q.offer(tempIn);
						thisMap[h][list[n]]=0;
						
						while(!(q.isEmpty()))
						{
							int tempOut[] = q.poll();
							for(int i=1;i<=tempOut[2]-1;i++)
							{
								for(int p=0;p<4;p++)
								{
									if((tempOut[0]+(py[p]*i)) >= 0 && (tempOut[0]+(py[p]*i)) < H &&  (tempOut[1]+ (px[p]*i)) >=0 && (tempOut[1]+( px[p]*i)) <W)//범위 안일때
									{
										if(thisMap[(tempOut[0]+(py[p]*i))][(tempOut[1]+ (px[p]*i))] == 1)
											thisMap[(tempOut[0]+(py[p]*i))][(tempOut[1]+ (px[p]*i))] = 0;
										else if(thisMap[(tempOut[0]+(py[p]*i))][(tempOut[1]+ (px[p]*i))] > 1)
										{
											tempIn = new int[3];
											tempIn[0] = tempOut[0]+(py[p]*i);
											tempIn[1] = tempOut[1]+ (px[p]*i);
											tempIn[2] = thisMap[(tempOut[0]+(py[p]*i))][(tempOut[1]+ (px[p]*i))];
											q.offer(tempIn);
											thisMap[(tempOut[0]+(py[p]*i))][(tempOut[1]+ (px[p]*i))] = 0;
										}
									}
										
								}//p for
							}
						}
						
						//벽돌 재배치
						for(int thisw=0;thisw<W;thisw++)
						{
							vec.clear();
							for(int thish=H-1;thish>=0;thish--)
								if(thisMap[thish][thisw] > 0)
									vec.add(thisMap[thish][thisw]);
							
							int pointer  = 0;
							for(int thish= H-1;thish>=0;thish--)
							{
								if(pointer < vec.size())
								{
									thisMap[thish][thisw] = vec.get(pointer);
									pointer++;
								}
								else
									thisMap[thish][thisw] = 0;
							}
						}
						
						
					}
					break;
				}
			}
			
			
		}//list n
		
		//다 끝남
		int cnt= 0;
		for(int h=0;h<H;h++)
			for(int w=0;w<W;w++)
				if(thisMap[h][w] >0)
					cnt++;
		
		if(MIN<0)
			MIN = cnt;
		else
			MIN = Math.min(MIN, cnt);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer stz;
		
		int result[];
		
		int T; //N 벽돌 갯수

		T = Integer.parseInt(sc.nextLine());
		result = new int[T+2];
		for(int test_case=1; test_case<=T;test_case++)
		{
			stz =new StringTokenizer(sc.nextLine()," ");
			N = Integer.parseInt(stz.nextToken());
			W= Integer.parseInt(stz.nextToken());
			H = Integer.parseInt(stz.nextToken());
			
			map = new int[H+1][W+1];
			MIN = -1;
			
			for(int h=0;h<H;h++)
			{
				stz = new StringTokenizer(sc.nextLine()," ");
				for(int w=0;w<W;w++)
					map[h][w] = Integer.parseInt(stz.nextToken());
			}
			
			dfs(0, new int[2]);
			
			result[test_case] = MIN;
		}//tc
		for(int i=1;i<=T;i++)
			System.out.println("#"+i+" "+result[i]);
	}//main

}

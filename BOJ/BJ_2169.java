import java.util.*;
/*
 * DFS로 풀었더니 메모리 초과
 * DP로 풀어야함
 * 
 */

public class BJ_2169 {
	public static void main(String[] args) {
		int N,M;
		int map[][] = new int[1005][1005];
		int result[][] = new int[1005][1005];
		
		Scanner sc = new Scanner(System.in);

		StringTokenizer stz = new StringTokenizer(sc.nextLine()," ");
		
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		
		for(int i=1;i<=N;i++)
		{
			stz = new StringTokenizer(sc.nextLine()," ");
			for(int j=1;j<=M;j++)
				map[i][j] = Integer.parseInt(stz.nextToken());
		}
		
		int temp[][] = new int[2][M+3];
		
		result[1][1] = map[1][1];
		for(int i =2; i<=M;i++)
			result[1][i] = result[1][i-1] + map[1][i];
		
		for(int i=2;i<=N;i++)
		{
			 //위 VS 오른
			temp[0][1] = result[i-1][1] + map[i][1];
			for(int j=2;j<=M;j++)
				temp[0][j] = Math.max(result[i-1][j] + map[i][j], temp[0][j-1]+map[i][j]);
			
			//위 VS 왼쪽
			temp[1][M] = result[i-1][M] + map[i][M];
			for(int j=M-1;j>=1;j--)
				temp[1][j] = Math.max(result[i-1][j] + map[i][j], temp[1][j+1]+map[i][j]);
			
			for(int j=1;j<=M;j++)
				result[i][j] = Math.max(temp[0][j], temp[1][j]);
		}
		
		System.out.println(result[N][M]);
	}//main
}




/*
public class BJ_2169 {
	static int max = -10000000;
	static int N;
	static int M;
	static int matrix[][] = new int[1005][1005];
	static int[] py = {0,1,0}; //오, 아, 왼
	static int[] px = {1,0,-1};
	
	static void dfs(int x, int y, int sum, boolean[][] chk)
	{
		sum += matrix[y][x];
		
		if((x == M-1) && (y == N-1))
		{
			max = Math.max(max, sum);
		}
		else
		{
//			boolean[][] tempB = new boolean[N+1][M+1];
//			for(int i=0;i<N;i++)
//				for(int j=0;j<M;j++)
//					tempB[i][j] = chk[i][j];
			
			for(int i=0;i<3;i++)
			{
				if((x+px[i] >= 0) && (x+px[i]<M) && (y+py[i] >= 0) && (y+py[i] <N))
					if(chk[y+py[i]][x+px[i]] == false )
					{
//						tempB[y+py[i]][x+px[i]] = true;
						chk[y+py[i]][x+px[i]] = true;

						dfs(x+px[i],y+py[i],sum,chk);
						chk[y+py[i]][x+px[i]] = false;
					}
			}
		}//else
	}//dfs
	
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		StringTokenizer stz = new StringTokenizer(str," ");
		
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		
		matrix	= new int[N+1][M+1];
		
		for(int i=0;i<N;i++)
		{
			str = sc.nextLine();
			stz = new StringTokenizer(str," ");
			for(int j=0;j<M;j++)
				matrix[i][j] = Integer.parseInt(stz.nextToken());
		}
		
		
		boolean check[][] = new boolean[N+1][M+1];
		check[0][0] = true;
		dfs(0,0,0,check);
		
		System.out.println(max);
	}//main
	
}
*/

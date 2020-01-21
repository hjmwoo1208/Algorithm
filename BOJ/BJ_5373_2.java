/* 백준 - 큐빙
 * 20191017 -- 맞음
 */
import java.util.*;
public class BJ_5373_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer stz;
		
		int T,N;
		char[][][] cube;
		char[] color = {'w','y','r','o','g','b'};
		String how;
		
		int[][] myC = {{0,0,0,1,2,2,2,1},{0,1,2,2,2,1,0,0}}; //+일때 내가 회전 1: y , 2: x
		int[][] myNC = {{0,0,0,1,2,2,2,1},{2,1,0,0,0,1,2,2}}; //-방향일 때 내가 회전
		//U+,U-,D+,D-, F+,F-,B+,B-, L+,L+, R+R-
		int[][] beforY = {{0,0,0,0,0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0,0,0,0,0},  {2,2,2,2,2,2,2,2,2,2,2,2}, {2,2,2,2,2,2,2,2,2,2,2,2,2},
									{2,2,2,0,1,2,0,0,0,2,1,0},{2,2,2,0,1,2,0,0,0,2,1,0},   {0,0,0,0,1,2,2,2,2,2,1,0}, {0,0,0,0,1,2,2,2,2,2,1,0},
									{0,1,2,0,1,2,0,1,2,2,1,0}, {2,1,0,0,1,2,2,1,0,2,1,0},  {2,1,0,0,1,2,2,1,0,2,1,0}, {0,1,2,0,1,2,0,1,2,2,1,0}}; //U+,U-,D+,D-, F+,F-,B+,B-, L+,L+,
		
		int[][] beforX = {{2,1,0,2,1,0,2,1,0,2,1,0}, {0,1,2,0,1,2,0,1,2,0,1,2},   {0,1,2,0,1,2,0,1,2,0,1,2}, {2,1,0,2,1,0,2,1,0,2,1,0},
									{0,1,2,0,0,0,2,1,0,2,2,2},{2,1,0,2,2,2,0,1,2,0,0,0},   {2,1,0,0,0,0,0,1,2,2,2,2},{0,1,2,2,2,2,2,1,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,2,2,2},{0,0,0,2,2,2,0,0,0,0,0,0},  {2,2,2,0,0,0,2,2,2,2,2,2},{2,2,2,2,2,2,2,2,2,0,0,0}}; //U+,U-,D+,D-
		
		char[] tempSet ;
		
		//U,D,F,B,L,R
		int[][] swing	 = {{3,5,2,4},{3,4,2,5}, {2,5,3,4},{2,4,3,5},   {0,5,1,4},{0,4,1,5},  {0,4,1,5},{0,5,1,4}, {0,2,1,3},{0,3,1,2}, {0,3,1,2},{0,2,1,3}};
		
		T = Integer.parseInt(sc.nextLine());
		
		for(int test=0;test<T;test++)
		{
			N = Integer.parseInt(sc.nextLine());
			cube = new char[7][4][4]; //[위치][y][x];
			
			for(int c=0;c<6;c++) //색칠
				for(int i=0;i<3;i++)
					for(int j=0;j<3;j++)
						cube[c][i][j] = color[c];
		
			// U- 0,w || D-1,y || F-2,r || B-3,o || L-4,g || R-5,b
			stz = new StringTokenizer(sc.nextLine()," ");
			for(int n=0;n<N;n++)//N번 회전
			{
				how = stz.nextToken();
				
				int index = 0;
				if(how.equals("U+")) index = 0;
				else if(how.equals("U-")) index = 1;
				else if(how.equals("D+"))index =2;
				else if(how.equals("D-")) index = 3;
				else if(how.equals("F+")) index = 4;
				else if(how.equals("F-")) index = 5;
				else if(how.equals("B+")) index = 6;
				else if(how.equals("B-")) index = 7;
				else if(how.equals("L+")) index = 8;
				else if(how.equals("L-") ) index = 9;
				else if(how.equals("R+")) index = 10;
				else if(how.equals("R-")) index = 11;
				
				//내가 회전 myC:+ myNC:-
				tempSet = new char[10];
				if(how.charAt(1) == '+')
				{
					for(int i =0;i<8;i++)
						tempSet[i] = cube[index/2][myC[0][i]][myC[1][i]];
					for(int i=0;i<8;i++)
						cube[index/2][myC[0][((i+2)%8)]][myC[1][((i+2)%8)]] = tempSet[i];
				}
				else if(how.charAt(1)=='-')
				{
					for(int i =0;i<8;i++)
						tempSet[i] = cube[index/2][myNC[0][i]][myNC[1][i]];
					for(int i=0;i<8;i++)
						cube[index/2][myNC[0][((i+2)%8)]][myNC[1][((i+2)%8)]] = tempSet[i];
				}
				
				//주변 회전
				tempSet = new char[15];
				for(int i=0;i<4;i++)
					for(int j=0;j<3;j++)
						tempSet[j+(3*i)] = cube[swing[index][i]][beforY[index][j+(3*i)]][beforX[index][j+(3*i)]];
				
				for(int i=0;i<4;i++)
				{
					int thisi = (i+1)%4;
					for(int j=0;j<3;j++)
						 cube[swing[index][thisi]][beforY[index][j+(3*thisi)]][beforX[index][j+(3*thisi)]] = tempSet[j+(3*i)];
				}				
			}
			
			//String temps = cube[0][i][0]+cube[0][i][1]+cube[0][i][2];
			for(int i =0;i<3;i++)
			{
				String temps = String.valueOf(cube[0][i][0])+String.valueOf(cube[0][i][1])+String.valueOf(cube[0][i][2]) ;
				//System.out.println(cube[0][i][0]+cube[0][i][1]+cube[0][i][2]);
				System.out.println(temps);
			}
		}//test
		
		
	}//main

}

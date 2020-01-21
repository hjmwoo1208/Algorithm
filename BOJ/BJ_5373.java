import java.util.*;
/* 큐빙
 * 
 */

class Cube{
	char color[][] = new char[3][3];
	
	public Cube(char c) {
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				this.color[i][j] = c;
	}

	public void setcolor(char c)
	{
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				this.color[i][j] = c;
	}
	
	public char getcolor(int y, int x)
	{
		return this.color[y][x];
	}
	
	public void chagecolor(int y, int x, char c)
	{
		this.color[y][x] = c;
	}
	
}

public class BJ_5373 {

	public static void main(String[] args) {
		int N;
		int cnt;
		//Object cube[] = new Object[6];
		
		char position[] = {'U','D','F','B','R','L'};  //'U-0','D-1','F-2','B-3','R-4','L-5'
		char color[] = {'w','y','r','o','b','g'}; // UDFBRL
		
		int py[][] = {{0,0,0},{0,0,0},{2,2,2},{2,2,2},{2,1,0,},{0,1,2},	{2,1,0},{0,1,2}};
		int px[]	[] = {{2,1,0},{0,1,2},{0,1,2},{2,1,0},{2,2,2},{2,2,2},{0,0,0},{0,0,0}};
		
		int mypx[] = {0,1,2,2,2,1,0,0};
		int mypy[] = {0,0,0,1,2,2,2,1};
		
		int pluspy[] = {2,1,0,0,0,1,2,2};
		int pluspx[] = {0,0,0,1,2,2,2,1};
		
		int minuspy[] = {0,1,2,2,2,1,0,0};
		int minuspx[] = {2,2,2,1,0,0,0,1};
		
		Scanner sc = new Scanner(System.in);
		StringTokenizer stz;
		
		N = Integer.parseInt(sc.nextLine());
		
		Cube cube[];
		String turn;
		Vector<Character> vec;
		char tempChar;
		

		
		
		for(int n=0;n<N;n++) //N번 돌림
		{
			cube = new Cube[6];
			for(int i=0;i<6;i++)
				cube[i] = new Cube(color[i]);
			//---큐브 입력 끝
			
			cnt = Integer.parseInt(sc.nextLine());
			
			stz = new StringTokenizer(sc.nextLine()," ");
			
			for(int c=0;c<cnt;c++)
			{
				turn = stz.nextToken();
				if(turn.equals("U+")== true)
				{
					int seq_c[] = {3,4,2,5};
					int seq_p[] = {0,0,0,0};
					vec = new Vector<Character>();
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							vec.add(cube[seq_c[d]].color[py[seq_p[d]][dc]][px[seq_p[d]][dc]]);
					
					for(int ch=0;ch<3;ch++)
					{
						tempChar = vec.get(11);
						vec.remove(11);
						vec.add(0, tempChar);
					}
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							cube[seq_c[d]].chagecolor(py[seq_p[d]][dc], px[seq_p[d]][dc], vec.get(dc+3*d));
					
					vec.removeAllElements();//벡터 내용 클리어
					for(int a=0;a<8;a++)
						vec.add(cube[0].color[pluspy[a]][pluspx[a]]);
					for(int a=0;a<8;a++)
						cube[0].chagecolor(mypy[a], mypx[a], vec.get(a));
					
				}
				else if(turn.equals("U-")== true)
				{
					int seq_c[] = {3,5,2,4};
					int seq_p[] = {1,1,1,1};
					vec = new Vector<Character>();
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							vec.add(cube[seq_c[d]].color[py[seq_p[d]][dc]][px[seq_p[d]][dc]]);
					
					for(int ch=0;ch<3;ch++)
					{
						tempChar = vec.get(11);
						vec.remove(11);
						vec.add(0, tempChar);
					}
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							cube[seq_c[d]].chagecolor(py[seq_p[d]][dc], px[seq_p[d]][dc], vec.get(dc+3*d));
					
					vec.removeAllElements();//벡터 내용 클리어
					for(int a=0;a<8;a++)
						vec.add(cube[0].color[minuspy[a]][minuspx[a]]);
					for(int a=0;a<8;a++)
						cube[0].chagecolor(mypy[a], mypx[a], vec.get(a));
				}
				else if(turn.equals("D+")== true)
				{
					int seq_c[] = {2,4,3,5 };
					int seq_p[] = { 2,2,2,2};
					vec = new Vector<Character>();
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							vec.add(cube[seq_c[d]].color[py[seq_p[d]][dc]][px[seq_p[d]][dc]]);
					
					for(int ch=0;ch<3;ch++)
					{
						tempChar = vec.get(11);
						vec.remove(11);
						vec.add(0, tempChar);
					}
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							cube[seq_c[d]].chagecolor(py[seq_p[d]][dc], px[seq_p[d]][dc], vec.get(dc+3*d));
					
					vec.removeAllElements();//벡터 내용 클리어
					for(int a=0;a<8;a++)
						vec.add(cube[1].color[pluspy[a]][pluspx[a]]);
					for(int a=0;a<8;a++)
						cube[1].chagecolor(mypy[a], mypx[a], vec.get(a));
					
				}
				else if(turn.equals("D-")== true)
				{
					int seq_c[] = {2,5,3,4 };
					int seq_p[] = {3,3,3,3 };
					vec = new Vector<Character>();
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							vec.add(cube[seq_c[d]].color[py[seq_p[d]][dc]][px[seq_p[d]][dc]]);
					
					for(int ch=0;ch<3;ch++)
					{
						tempChar = vec.get(11);
						vec.remove(11);
						vec.add(0, tempChar);
					}
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							cube[seq_c[d]].chagecolor(py[seq_p[d]][dc], px[seq_p[d]][dc], vec.get(dc+3*d));
					
					vec.removeAllElements();//벡터 내용 클리어
					for(int a=0;a<8;a++)
						vec.add(cube[1].color[minuspy[a]][minuspx[a]]);
					for(int a=0;a<8;a++)
						cube[1].chagecolor(mypy[a], mypx[a], vec.get(a));
					
				}
				else if(turn.equals("F+")== true)
				{
					int seq_c[] = {0,4,1,5 };
					int seq_p[] = {2,7,0,4 };
					vec = new Vector<Character>();
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							vec.add(cube[seq_c[d]].color[py[seq_p[d]][dc]][px[seq_p[d]][dc]]);
					
					for(int ch=0;ch<3;ch++)
					{
						tempChar = vec.get(11);
						vec.remove(11);
						vec.add(0, tempChar);
					}
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							cube[seq_c[d]].chagecolor(py[seq_p[d]][dc], px[seq_p[d]][dc], vec.get(dc+3*d));
					
					vec.removeAllElements();//벡터 내용 클리어
					for(int a=0;a<8;a++)
						vec.add(cube[2].color[pluspy[a]][pluspx[a]]);
					for(int a=0;a<8;a++)
						cube[2].chagecolor(mypy[a], mypx[a], vec.get(a));
				}
				else if(turn.equals("F-")== true)
				{
					int seq_c[] = { 0,5,1,4};
					int seq_p[] = {3,5,1,6 };
					vec = new Vector<Character>();
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							vec.add(cube[seq_c[d]].color[py[seq_p[d]][dc]][px[seq_p[d]][dc]]);
					
					for(int ch=0;ch<3;ch++)
					{
						tempChar = vec.get(11);
						vec.remove(11);
						vec.add(0, tempChar);
					}
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							cube[seq_c[d]].chagecolor(py[seq_p[d]][dc], px[seq_p[d]][dc], vec.get(dc+3*d));
					
					vec.removeAllElements();//벡터 내용 클리어
					for(int a=0;a<8;a++)
						vec.add(cube[2].color[minuspy[a]][minuspx[a]]);
					for(int a=0;a<8;a++)
						cube[2].chagecolor(mypy[a], mypx[a], vec.get(a));
				}
				else if(turn.equals("B+")== true)
				{
					int seq_c[] = { 0,5,1,4};
					int seq_p[] = { 0,7,2,4};
					vec = new Vector<Character>();
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							vec.add(cube[seq_c[d]].color[py[seq_p[d]][dc]][px[seq_p[d]][dc]]);
					
					for(int ch=0;ch<3;ch++)
					{
						tempChar = vec.get(11);
						vec.remove(11);
						vec.add(0, tempChar);
					}
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							cube[seq_c[d]].chagecolor(py[seq_p[d]][dc], px[seq_p[d]][dc], vec.get(dc+3*d));
					
					vec.removeAllElements();//벡터 내용 클리어
					for(int a=0;a<8;a++)
						vec.add(cube[3].color[pluspy[a]][pluspx[a]]);
					for(int a=0;a<8;a++)
						cube[3].chagecolor(mypy[a], mypx[a], vec.get(a));
				}
				else if(turn.equals("B-")== true)
				{
					int seq_c[] = {0,4,1,5 };
					int seq_p[] = {1,5,3,6 };
					vec = new Vector<Character>();
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							vec.add(cube[seq_c[d]].color[py[seq_p[d]][dc]][px[seq_p[d]][dc]]);
					
					for(int ch=0;ch<3;ch++)
					{
						tempChar = vec.get(11);
						vec.remove(11);
						vec.add(0, tempChar);
					}
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							cube[seq_c[d]].chagecolor(py[seq_p[d]][dc], px[seq_p[d]][dc], vec.get(dc+3*d));
					
					vec.removeAllElements();//벡터 내용 클리어
					for(int a=0;a<8;a++)
						vec.add(cube[3].color[minuspy[a]][minuspx[a]]);
					for(int a=0;a<8;a++)
						cube[3].chagecolor(mypy[a], mypx[a], vec.get(a));
				}
				else if(turn.equals("R+")== true)
				{
					int seq_c[] = {0,3,1,2 };
					int seq_p[] = {4,7,4,4 };
					vec = new Vector<Character>();
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							vec.add(cube[seq_c[d]].color[py[seq_p[d]][dc]][px[seq_p[d]][dc]]);
					
					for(int ch=0;ch<3;ch++)
					{
						tempChar = vec.get(11);
						vec.remove(11);
						vec.add(0, tempChar);
					}
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							cube[seq_c[d]].chagecolor(py[seq_p[d]][dc], px[seq_p[d]][dc], vec.get(dc+3*d));
					
					vec.removeAllElements();//벡터 내용 클리어
					for(int a=0;a<8;a++)
						vec.add(cube[4].color[pluspy[a]][pluspx[a]]);
					for(int a=0;a<8;a++)
						cube[4].chagecolor(mypy[a], mypx[a], vec.get(a));
				}
				else if(turn.equals("R-")== true)
				{
					int seq_c[] = {0,2,1,3 };
					int seq_p[] = {5,5,5,6 };
					vec = new Vector<Character>();
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							vec.add(cube[seq_c[d]].color[py[seq_p[d]][dc]][px[seq_p[d]][dc]]);
					
					for(int ch=0;ch<3;ch++)
					{
						tempChar = vec.get(11);
						vec.remove(11);
						vec.add(0, tempChar);
					}
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							cube[seq_c[d]].chagecolor(py[seq_p[d]][dc], px[seq_p[d]][dc], vec.get(dc+3*d));
					
					vec.removeAllElements();//벡터 내용 클리어
					for(int a=0;a<8;a++)
						vec.add(cube[4].color[minuspy[a]][minuspx[a]]);
					for(int a=0;a<8;a++)
						cube[4].chagecolor(mypy[a], mypx[a], vec.get(a));
				}
				else if(turn.equals("L+")== true)
				{
					int seq_c[] = {0,2,1,3 };
					int seq_p[] = {7,7,7,4 };
					vec = new Vector<Character>();
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							vec.add(cube[seq_c[d]].color[py[seq_p[d]][dc]][px[seq_p[d]][dc]]);
					
					for(int ch=0;ch<3;ch++)
					{
						tempChar = vec.get(11);
						vec.remove(11);
						vec.add(0, tempChar);
					}
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							cube[seq_c[d]].chagecolor(py[seq_p[d]][dc], px[seq_p[d]][dc], vec.get(dc+3*d));
					
					vec.removeAllElements();//벡터 내용 클리어
					for(int a=0;a<8;a++)
						vec.add(cube[5].color[pluspy[a]][pluspx[a]]);
					for(int a=0;a<8;a++)
						cube[5].chagecolor(mypy[a], mypx[a], vec.get(a));
				}
				else if(turn.equals("L-")== true)
				{
					int seq_c[] = {0,3,1,2 };
					int seq_p[] = {6,5,6,6 };
					vec = new Vector<Character>();
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++)
							vec.add(cube[seq_c[d]].color[py[seq_p[d]][dc]][px[seq_p[d]][dc]]);
					
					for(int ch=0;ch<3;ch++)
					{
						tempChar = vec.get(11);
						vec.remove(11);
						vec.add(0, tempChar);
					}
					
					for(int d=0;d<4;d++)
						for(int dc=0;dc<3;dc++) 
							//System.out.println( py[seq_p[d]][dc] +" /  "+px[seq_p[d]][dc] +" /  "+vec.get(dc+3*d));
							cube[seq_c[d]].chagecolor(py[seq_p[d]][dc], px[seq_p[d]][dc], vec.get(dc+3*d));
					
					vec.removeAllElements();//벡터 내용 클리어
					for(int a=0;a<8;a++)
						vec.add(cube[5].color[minuspy[a]][minuspx[a]]);
					for(int a=0;a<8;a++)
						cube[5].chagecolor(mypy[a], mypx[a], vec.get(a));
				}
			}// c for
			
			//--출력
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
					System.out.print(cube[0].color[i][j]);
				System.out.println();
			}
					
		}// N for
		
		
		
		
	}//main

}

/*
char U[][] = new char[4][4];
for(int i=0;i<3;i++)
	for(int j=0;j<3;j++)
		U[i][j] = 'w';

char D[][] = new char[4][4];
for(int i=0;i<3;i++)
	for(int j=0;j<3;j++)
		D[i][j] = 'y';

char F[][] = new char[4][4];
for(int i=0;i<3;i++)
	for(int j=0;j<3;j++)
		F[i][j] = 'r';

char B[][] = new char[4][4];
for(int i=0;i<3;i++)
	for(int j=0;j<3;j++)
		B[i][j] = 'o';

char L[][] = new char[4][4];
for(int i=0;i<3;i++)
	for(int j=0;j<3;j++)
		L[i][j] = 'g';

char R[][] = new char[4][4];
for(int i=0;i<3;i++)
	for(int j=0;j<3;j++)
		R[i][j] = 'b';
*/

/*
HashMap<Character,char[][]> cube = new HashMap<Character,char[][]>();


char temp[][];

for(int a=0;a<6;a++)
{
	temp = new char[3][3];
	for(int y=0;y<3;y++)
		for(int x=0;x<3;x++)
			temp[y][x] = color[a];
	cube.put(position[a], temp);
	
}
int x = cube.get('U')[2][2];
temp = cube.get('U');
System.out.println(temp[1][1]);
System.out.println( cube.get('U')[2][2]);

//System.out.println(R[2][1]);
*/


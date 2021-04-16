import java.util.*;
import java.io.*;
/* 미네랄 - 성공
 * 11% 틀림 -> 해결방법 : visited를 int로 받아서 클러스트에 번호를 매겨준다*/
public class BOJ_2933 {
	static char[][] Down(char map[][], int visited[][],int p, int lenC[], List<int[]> list,int R, int C){
		int min = 1000;
		for(int c=lenC[0];c<=lenC[1];c++) {
			int length = 0;
			for(int r=R-1;r>=0;r--) {
				if(visited[r][c]==p) break;
				if(map[r][c]=='x') length=0;
				else length++;
			}
			if(min>length) 
				min = length;
		}
		int size = list.size();
		for(int i=0;i<size;i++) {
			int out[] = list.get(i);
			map[out[0]][out[1]] = '.';
		}
		for(int i=0;i<size;i++) {
			int out[] = list.get(i);
			map[out[0]+min][out[1]] = 'x';
		}
		return map;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(stz.nextToken());
		int C = Integer.parseInt(stz.nextToken());
		
		char map[][] = new  char[R][C];
		for(int i=0;i<R;i++) map[i] = br.readLine().toCharArray();
		int N = Integer.parseInt(br.readLine());
		stz = new StringTokenizer(br.readLine());
		int py[] = {-1,0,1,0}, px[] = {0,1,0,-1};
		
		for(int n=1;n<N+1;n++) { //game
			int high = R - Integer.parseInt(stz.nextToken());
			int thisc = -1;
			if(n%2==0) {//오른쪽
				for(thisc=C-1;thisc>=0;thisc-- ) {
					if(map[high][thisc]=='x') { 
						map[high][thisc] = '.';
						break;
					}
				}
			}else {//왼쪽
				for( thisc=0;thisc<C;thisc++)
					if(map[high][thisc]=='x') {
						map[high][thisc] = '.';
						break;
					}
			}//왼쪽
			if(thisc>-1) { //맞은 미네랄이 있을 때
				 List<int[]> save = new ArrayList<int[]>();
				 boolean  flag = false;
				 int visited[][] = new int[R][C];
				 int lenC[] = new int[2]; //min ,max
				 for(int pi=0;pi<4;pi++) {
					 flag = false;
					 save = new ArrayList<>();
					 int tmpY = high + py[pi];
					 int tmpX = thisc + px[pi]; 
					 if(tmpY<0 || tmpX<0 || tmpY>=R || tmpX >=C || visited[tmpY][tmpX]>0 || map[tmpY][tmpX]=='.') continue;
					 visited[tmpY	][tmpX] = pi+1;
					 save.add(new int[] {tmpY,tmpX});
					 int qpointer = 0;
					 lenC = new int[] {C-1, 0};
					 if(tmpX<lenC[0]) lenC[0] = tmpX;
					 if(tmpX>lenC[1]) lenC[1] = tmpX;
					 while(qpointer<save.size()) {
						 int out[] = save.get(qpointer);
						 qpointer++;
						 for(int p=0;p<4;p++) {
							 int y = out[0] + py[p];
							 int x = out[1] + px[p];
							 if(y<0 || x<0 || y>=R || x>=C || visited[y][x]>0 || map[y][x] =='.') continue;
							 if(x<lenC[0]) lenC[0] = x;
							 if(x>lenC[1]) lenC[1] = x;
							 if(y ==R-1) flag = true; // 바닥에 붙음
							 save.add(new int[] {y,x});
							 visited[y][x] = pi+1;
						 }
					 }//while
					 if(save.size()>0 && !flag) {
						 map = Down(map, visited,pi+1, lenC, save, R, C);
						 break;
					 }
				 }
			}
		}//game
		for(int i=0;i<R;i++) {
			StringBuilder sb = new StringBuilder();
			for(int j=0;j<C;j++) sb.append(map[i][j]);
			System.out.println(sb);
		}
	}
}
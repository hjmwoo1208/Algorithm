import java.util.*;
import java.io.*;
/* 가스관  - 성공
 * 
 *  * 10달만에 성공...
 *  * NullPointerException 주의 .... */
public class BOJ_2931 {
	public static void main(String[] args) throws IOException, NullPointerException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine().trim());
		int R = Integer.parseInt(stz.nextToken());
		int C = Integer.parseInt(stz.nextToken());
		char map[][] = new char[R][C];
		int M[] = new int[2], Z[] = new int[2];
		boolean flag = false;
		for(int i=0;i<R;i++	) {
			String str = br.readLine().trim();
			map[i] = str.toCharArray();
			for(int j=0;j<C;j++) {
				if(map[i][j] == 'Z') Z= new int[] {i,j};
				else if(map[i][j]=='M') M = new int[] {i,j};
				else if(map[i][j]=='.') continue;
				else  flag = true;
			}
		}
		int ZM = Math.abs(Z[0]-M[0]) + Math.abs(Z[1]-M[1]);
		if(ZM==2 && !flag) {
			if(M[0]==Z[0]) {
				if(M[1]>Z[1]) System.out.println((M[0]+1)+" "+M[1]+" -");
				else System.out.println((M[0]+1)+" "+(M[1]+2)+" -");
			}else {
				if(M[0]>Z[0]) System.out.println(M[0]+" "+(M[1]+1)+" |");
				else System.out.println((M[0]+2)+" "+(M[1]+1)+" |");
			}
		}else {
			Map<Character,boolean[]> pipe = new HashMap<>();
			pipe.put('|', new boolean[] {true,false,true,false}); 
			pipe.put('-', new boolean[] {false,true,false,true}); 
			pipe.put('+', new boolean[] {true,true,true,true}); 
			pipe.put('1', new boolean[] {false,true,true,false}); 
			pipe.put('2', new boolean[] {true,true,false,false}); 
			pipe.put('3', new boolean[] {true,false,false,true}); 
			pipe.put('4', new boolean[] {false,false,true,true});
			Queue<int[]> q = new LinkedList<>();
			boolean visited[][] = new boolean[R][C];
			int py[] = {-1,0,1,0}, px[] = {0,1,0,-1};
			
			visited[Z[0]][Z[1]] = true; //입장전 방문체크 필수...ㅠㅠ
			for(int p=0;p<4;p++) {
				int y = Z[0] + py[p];
				int x = Z[1] + px[p];
				if(y<0 || x<0 || y>=R || x>=C || map[y][x] == '.' ||map[y][x]=='M'||map[y][x]=='Z'||visited[y][x]) continue; //map[y][x]=='M'||map[y][x]=='Z' 주의 부분...ㅠㅠ
				boolean tmp[] = pipe.get(map[y][x]);
				if(tmp[(p+2)%4]) {
					q.offer(new int[] {y,x});
					visited[y][x] = true;
					break;
				}
			}
			
			visited[M[0]][M[1]] = true;
			for(int p=0;p<4;p++) {
				int y = M[0] + py[p];
				int x = M[1] + px[p];
				if(y<0 || x<0 || y>=R || x>=C || map[y][x] == '.' ||map[y][x]=='M'||map[y][x]=='Z'||visited[y][x]) continue;
				boolean tmp[] = pipe.get(map[y][x]);
				if(tmp[(p+2)%4]) {
					q.offer(new int[] {y,x});
					visited[y][x] = true;
					break;
				}
			}
			visited = new boolean[R][C];
			while(!(q.isEmpty())) {
				int out[] = q.poll();
				if(map[out[0]][out[1]] == '.') {
					int bit = 0; //
					for(int p = 0;p<4;p++) {
						int y = out[0] + py[p];
						int x = out[1] + px[p];
						if(y<0 || x<0 || y>=R || x>=C || map[y][x] == '.') continue;
						if(map[y][x] == 'Z'|| map[y][x] == 'M') {
							if(!visited[y][x]) 
								bit = bit|(1<<p);
						}else {
							boolean tmp[] = pipe.get(map[y][x]);
							int pp = (p+2)%4;
							if(tmp[pp]) 
								bit = bit|(1<<p);
						}
					}
					char c = ' ';
					if(bit == 5) c = '|';
					else if(bit == 10) c = '-';
					else if(bit == 15) c = '+';
					else if(bit == 6) c = '1';
					else if(bit == 3) c = '2';
					else if(bit == 9) c = '3';
					else if(bit == 12) c = '4';
					System.out.println((out[0]+1)+" "+(out[1]+1)+" "+c);
					break;
				}
				if(map[out[0]][out[1]] == 'Z'|| map[out[0]][out[1]] == 'M') continue;
				
				boolean tmp[] = pipe.get(map[out[0]][out[1]]);
				for(int p=0;p<4;p++) {
					if(!tmp[p]) continue;
					int y = out[0] + py[p];
					int x = out[1] + px[p];
					if(y<0 || x<0 || y>=R || x>=C  || visited[y][x]) continue;
					q.offer(new int[] {y,x});
					visited[y][x] = true;
				}
			}
		}//else
	}//main
}
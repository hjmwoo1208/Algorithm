import java.util.*;
import java.io.*;

public class BOJ_1113 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int map[][] = new int[N][M];
		
		List<int[]> list[] = new ArrayList[10];
		for(int i=0;i<10;i++) list[i] = new ArrayList<int[]>();
		
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = str.charAt(j) -'0';
				list[map[i][j]].add(new int[] {i,j});
			}
		}

		int py[] = {-1,0,1,0};
		int px[] = {0,1,0,-1};
		int water = 0;
		for(int i=1;i<9;i++) {
			
			int size = list[i].size();
			for(int s=0;s<size;s++) {
				int out[] = list[i].get(s);
				
				if(map[out[0]][out[1]]==0) 
					continue;
				
				if(map[out[0]][out[1]]>i) // 물채우기가 이미 완료된 곳
					continue;
				
				List<int[]> temp = new ArrayList<int[]>();
				Queue<int[]> q = new LinkedList<int[]>();
				boolean flag = false;	// 외부로 흐르는 곳 true, 고인 곳 false
				boolean visited[][] = new boolean[N][M];
				q.offer(new int[] {out[0],out[1]});
				temp.add(new int[] {out[0],out[1]});
				visited[out[0]][out[1]] = true;
				
				while(!(q.isEmpty())) {
					int qout[] = q.poll();
					for(int p=0;p<4;p++) {
						int y = qout[0] + py[p];
						int x = qout[1] + px[p];
						if(y<0||x<0||y>N-1||x>M-1) {
							flag = true;
							continue;
						}
						if(visited[y][x]) 
							continue;
						if(map[y][x] < i) { //<--이전에 흐르는 곳이라서 숫자가 안 커진곳
							flag = true;
							continue;
						}
						if(map[y][x] == i) {
							q.offer(new int[] {y,x});
							temp.add(new int[] {y,x});
							visited[y][x] = true;
						}
					}
				}
				
				if(flag) { //물채우기 불가능한
					for (int[] t : temp)
						map[t[0]][t[1]] = 0;
				}else { // 물채우기 가능한
					water += temp.size();
					for (int[] t : temp) {
						map[t[0]][t[1]] += 1;
						list[i+1].add(t);
					}
				}
			}
		}
		System.out.println(water);
	}
}
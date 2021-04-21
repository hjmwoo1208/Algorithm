import java.util.*;
import java.io.*;
/* 벽돌 깨기 - 성공
 * DFS + BFS*/
public class SWEA_5656 {
	static int MIN = 10000;
	static void DFS(int cnt, int map[][],int W, int H, int N) {
		if(MIN==0) return; //가지치기
		if(cnt==N) {
			int count=0;
			for(int i=0;i<H;i++)
				for(int j=0;j<W;j++)
					if(map[i][j]>0) count++;
			MIN = MIN>count?count:MIN;
			return;
		}
		int py[] = {-1,0,1,0}, px[] = {0,1,0,-1};
		for(int j=0;j<W;j++)
			for(int i=0;i<H;i++) {
				if(i==H-1 && map[i][j]==0) DFS(cnt+1,map,W,H,N); //끝까지 떨어졌는데 벽돌이 없을 땐 그대로 다음 단계로 간다. 
				if(map[i][j]>0) { //벽돌이 있을 때
					int tmp[][] = new int[H][W];
					boolean visited[][] = new boolean[H][W];
					Queue<int[]> q = new LinkedList<int[]>();
					q.offer(new int[] {i,j,map[i][j]});
					visited[i][j] = true;
					while(!(q.isEmpty())) {
						int out[] = q.poll();
						int len = out[2];
						for(int p=0;p<4;p++) //방향
							for(int n=1;n<len;n++) { //(숫자-1) 만큼 벽돌 탐색
								int y = out[0]+py[p]*n;
								int x = out[1]+px[p]*n;
								if(y<0||x<0||y>=H|| x>=W) break; //경계가 넘으면 다음 길이에서도 넘으므로 break
								if(visited[y][x] || map[y][x] == 0) continue;
								q.offer(new int[] {y,x,map[y][x]});
								visited[y][x] = true;
							}
					}
					for(int x=0;x<W;x++) {
						List<Integer> list = new ArrayList<>();
						for(int y=H-1;y>=0;y--) //벽돌찾기
							if(map[y][x]>0 && !visited[y][x]) list.add(map[y][x]);
						for(int index=0;index<list.size();index++) tmp[H-1-index][x] = list.get(index); //벽돌 순서대로 배치
					}
					DFS(cnt+1,tmp,W,H,N);
					break;
				}
			}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(stz.nextToken());
			int W = Integer.parseInt(stz.nextToken());
			int H = Integer.parseInt(stz.nextToken());
			MIN = 10000;
			int map[][] = new int[H][W];
			for(int i=0;i<H;i++) {
				stz = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++) map[i][j] = Integer.parseInt(stz.nextToken());
			}
			DFS(0,map,W,H,N);
			System.out.println("#"+tc+" "+MIN);
		}
	}
}
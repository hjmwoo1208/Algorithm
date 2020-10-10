import java.util.*;
import java.io.*;
public class BOJ_1600 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(stz.nextToken());
		int H = Integer.parseInt(stz.nextToken());
		int map[][] = new int[H][W];
		for(int i=0;i<H;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=0;j<W;j++) map[i][j] = Integer.parseInt(stz.nextToken());
		}
		int min = -1; //최솟값 -> 도착하지 못하면 -1출력
		int py[] = {-1,0,1,0}; //원숭이 이동 : 상우하좌 순서 
		int px[] = {0,1,0,-1};
		int knightY[] = {-2,-2,-1,1,2,2,1,-1}; //나이트 이동할 때 상,우,하,좌 순서
		int knightX[] = {-1,1,2,2,1,-1,-2,-2};
		Queue<int[]> q = new LinkedList<int[]>();
		boolean check[][][] = new boolean[H][W][K+1]; //K개의 사용 횟수 여부에 따라서 체크
		for(int i=0;i<K+1;i++) check[0][0][i] = true; //출발지는 전부 방문처리 도로 돌아오는 일 없도록
		q.offer(new int[] {0,0,0,0}); //y,x,k횟수,이동 횟수
		while(!(q.isEmpty())) {
			int[] out = q.poll();
			if(out[0]==H-1 && out[1] ==W-1) { //오른쪽 맨아래 도착
				min = out[3];
				break;
			}
			if(out[2]<K) //나이트 이동이 가능할 때
				for(int p=0;p<8;p++) {
					int y = out[0] + knightY[p];
					int x = out[1] + knightX[p];
					if(y<0 ||x<0 || y>H-1 || x>W-1 || map[y][x] == 1 || check[y][x][out[2]+1]) continue;
					check[y][x][out[2]+1] = true;
					q.offer(new int[] {y,x,out[2]+1,out[3]+1});
				}
			for(int p=0;p<4;p++) { //원숭이 이동
				int y = out[0] + py[p];
				int x = out[1] + px[p];
				if(y<0 ||x<0 || y>H-1 || x>W-1 || map[y][x] == 1 || check[y][x][out[2]]) continue;
				check[y][x][out[2]] = true;
				q.offer(new int[] {y,x,out[2],out[3]+1});
			}
		}
		System.out.println(min);
	}//main
}
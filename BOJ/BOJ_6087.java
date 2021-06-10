import java.util.*;
import java.io.*;

/* 백준 - 레이저 통신
 * - 거울의 갯수 = 90도로 변경된 횟수
 * - 방문 체크는 Queue에서 나올 때 한다!!
 */

public class BOJ_6087 {
	static class Node implements Comparable<Node>{
		int y, x, count, p;
		public Node(int y, int x, int count, int p) {
			super();
			this.y = y;
			this.x = x;
			this.count = count; //턴의 횟수
			this.p = p;
		}
		@Override
		public int compareTo(Node o) {
			return this.count - o.count;
		}
	}
	//진출 가능 여부 판별 메소드
	static boolean possible(int y, int x, int p, int H, int W,boolean check[][][], char map[][]) {
		if(y<0 || x<0 || y>=H || x>=W || map[y][x] == '*'||check[p][y][x] ) return false;
		return true;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(stz.nextToken());
		int H = Integer.parseInt(stz.nextToken());
		char map[][] = new char[H][W];
		boolean check[][][] = new boolean[4][H][W];
		
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		int C[] = new int[2];
		for(int i=0;i<H;i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0;j<W;j++)
				if(map[i][j] == 'C') {
					if(q.isEmpty()) 
						for(int p=0;p<4;p++) q.offer(new Node(i,j,0,p));
					else C = new int[] {i,j};
				}
		}
		
		int py[] = {-1,0,1,0}, px[] = {0,1,0,-1} ;
		while(!q.isEmpty()) {
			Node out = q.poll();
			if(check[out.p][out.y][out.x]) continue;
			check[out.p][out.y][out.x] = true; //방문 체크는 Queue에서 나왔을 때 한다 -> why? 뒤에서 나오는 루트가 턴을 더 적게 쓰고 왔을 수 있으므로
			
			if(out.y==C[0] && out.x==C[1]){
				System.out.println(out.count);
				break;
			}
			//기존 방향 진출
			int y = out.y + py[out.p];
			int x = out.x	+ px[out.p];
			if(possible(y, x, out.p, H, W, check,map)) q.offer(new Node(y,x,out.count,out.p));
			
			//오른쪽 90도
			y = out.y + py[(out.p+1)%4];
			x = out.x + px[(out.p+1)%4];
			if(possible(y, x, (out.p+1)%4, H, W, check,map)) q.offer(new Node(y,x,out.count+1,(out.p+1)%4));
			
			//왼쪽 90도
			y = out.y + py[(out.p+3)%4];
			x = out.x + px[(out.p+3)%4];
			if(possible(y, x, (out.p+3)%4, H, W, check,map)) q.offer(new Node(y,x,out.count+1,(out.p+3)%4));
		}
	}
}
import java.util.*;
import java.io.*;
public class BOJ_19237 {
	static class shark implements Comparable<shark>{
		int y ,x, p,num;			
		public shark(int y, int x, int p, int num) {
			this.y = y;
			this.x = x;
			this.p = p;
			this.num = num;
		}
		@Override
		public int compareTo(shark o) {
			return this.num-o.num;
		}
	}
	static class Next implements Comparable<Next>{
		int pp, smell, sharkNum ,y,x;
		public Next(int pp, int smell, int sharkNum, int y, int x) {
			this.pp = pp;
			this.smell = smell;
			this.sharkNum = sharkNum;
			this.y = y;
			this.x = x;
		}
		@Override
		public int compareTo(Next o) { //this의 우선순위가 높으면 -1
			if(this.smell==o.smell) {
				if(this.pp<o.pp) return -1;
				return 1;
			}
			if(this.smell==0 && o.smell>0) return -1;
			if(this.smell==this.sharkNum) {
				if(o.smell==0) return 1;
				return -1;
			}
			return 1;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int k = Integer.parseInt(stz.nextToken());
		int map[][][]	= new int[N][N][2]; //map[][][0] = 냄새의 남은 시간 , map[][][1] = 냄새 주인 상어
		PriorityQueue<shark> sharkQ = new PriorityQueue<shark>();
		List<shark> sharkList = new ArrayList<shark>();
		for(int i=0;i<N;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j][1] = Integer.parseInt(stz.nextToken());
				if(map[i][j][1]>0) {
					map[i][j][0] = k;
					sharkList.add(new shark(i, j, 0, map[i][j][1]));
				}
			}
		}
		Collections.sort(sharkList); //상어의 번호 순으로 정렬
		//각 상어의 현재 바라보는 방향
		stz = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) sharkList.get(i).p = Integer.parseInt(stz.nextToken())-1;

		int priorityPosition[][][] = new int[M+1][4][4];
		for(int i=1;i<M+1;i++) 
			for(int j=0;j<4;j++) {
				stz = new StringTokenizer(br.readLine());
				for(int p=0;p<4;p++) priorityPosition[i][j][p] = Integer.parseInt(stz.nextToken())-1;
			}
		int time = 0;
		int py[] = {-1,1,0,0};
		int px[] = {0,0,-1,1};
		
		for (int i = 0; i < sharkList.size(); i++) sharkQ.offer(sharkList.get(i)); 
		boolean check[][] = new boolean[N][N];
		while(!(sharkQ.isEmpty())) {
			if(sharkQ.size()==1) break; //처음부터 1명일 경우를 고려하여 맨 처음에 판별
			int qSize = sharkQ.size();
			check = new boolean[N][N];
			sharkList.clear(); //살아남의 상어의 목록 비어준다.
			for(int size=0;size<qSize;size++) {
				shark out = sharkQ.poll();
				PriorityQueue<Next> q = new PriorityQueue<Next>();
				for(int p=0;p<4;p++) {
					int y = out.y+py[priorityPosition[out.num][out.p][p]];
					int x = out.x+px[priorityPosition[out.num][out.p][p]];
					if(y<0|| x<0||y>N-1 ||x>N-1) continue;
					q.offer(new Next(p, map[y][x][1], out.num,y,x));
				}
				Next n = q.poll();
				if(check[n.y][n.x]) continue; //이동한 자리가 이미 주인이 있으면 아웃
				sharkList.add(new shark(n.y, n.x, priorityPosition[out.num][out.p][n.pp], out.num));
				check[n.y][n.x] = true;
			}//size
			for(int i=0;i<N;i++)
				for(int j=0;j<N;j++)
					if(map[i][j][0]>0 && --map[i][j][0] ==0) map[i][j][1] = 0; //냄새=0이 되면 냄새 주인도 0
			for(int i=0;i<sharkList.size();i++) {
				sharkQ.offer(sharkList.get(i)); 
				map[sharkList.get(i).y][sharkList.get(i).x][0] = k; //냄새 마킹 --> 동시에 이동하므로 냄새는 모두 움직이고나서 마킹
				map[sharkList.get(i).y][sharkList.get(i).x][1] = sharkList.get(i).num;
			}
			time++;
			if(time>1000) break; //1000이 넘으면 이동X
		}
		System.out.println(time>1000?-1:time);
	}//main	
}
import java.util.*;
import java.io.*;
public class BOJ_16235 {
	static class Tree implements Comparable<Tree>{
		int r,c,age;
		public Tree(int r, int c, int age) {
			super();
			this.r = r;
			this.c = c;
			this.age = age;
		}
		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());
		int A[][] = new int[N+1][N+1], map[][][] = new int[2][N+1][N+1]; //map[0][][] 양분, map[1][][] 죽은 나무
		for(int i=1;i<N+1;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=1;j<N+1;j++) A[i][j] = Integer.parseInt(stz.nextToken());
		}
		PriorityQueue<Tree> treeQ = new PriorityQueue<Tree>(); //나무는 나이가 작은 순으로 양분을 먹기때문에 우선순위큐
		for(int i=0;i<M;i++) {
			stz = new StringTokenizer(br.readLine());
			treeQ.offer(new Tree(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken())));
		}
		for(int i=1;i<N+1;i++) Arrays.fill(map[0][i], 5);
		int py[] = {-1,-1,-1,0,1,1,1,0}, px[]= {-1,0,1,1,1,0,-1,-1};
		for(int k=0;k<K;k++) {
			//봄
			PriorityQueue<Tree> tmpQ = new PriorityQueue<Tree>();//살아있는 나무들을 임시 저장
			while(!(treeQ.isEmpty())) {
				Tree out = treeQ.poll();
				if(map[0][out.r][out.c]-out.age<0)	map[1][out.r][out.c] += (out.age/2);
				else {
					map[0][out.r][out.c]-=out.age;
					tmpQ.offer(new Tree(out.r, out.c, out.age+1));
					if((out.age+1)%5==0)  //가을
						for(int p=0;p<8;p++) {
							int y = out.r + py[p];
							int x = out.c + px[p];
							if(y<1 || x<1 || y>N || x>N) continue;
							tmpQ.offer(new Tree(y, x, 1));
						}
				}
			}
			treeQ = tmpQ;
			//여름,겨울
			for(int i=1;i<N+1;i++)
				for(int j=1;j<N+1;j++) {
					map[0][i][j] += (map[1][i][j]+A[i][j]); //양분과 죽은 나무 양분을 더해준다.
					map[1][i][j] = 0;
				}
		}
		System.out.println(treeQ.size());
	}
}
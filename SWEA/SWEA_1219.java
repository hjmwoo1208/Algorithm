import java.util.*;
import java.io.*;
/* 길찾기 - 성공*/
public class SWEA_1219 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int tc=1;tc<11;tc++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			stz.nextToken();
			int N = Integer.parseInt(stz.nextToken());
			int node[][] = new int[100][100], res=0; //node[i][0] : i에서 갈 수 있는 정점의 갯수 , node[i][1<=num<=node[i][0] : 다음 정점 번호
			boolean check[] = new boolean[100];
			stz = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				int s = Integer.parseInt(stz.nextToken());
				node[s][++node[s][0]] = Integer.parseInt(stz.nextToken());
			}
			Queue<Integer> q = new LinkedList<Integer>();
			q.offer(0);
			check[0] = true;
			while(!(q.isEmpty())) {
				int out = q.poll();
				if(out==99) {
					res = 1;
					break;
				}
				int size = node[out][0];
				for(int i=1;i<=size;i++) 
					if(!check[node[out][i]]) {
						q.offer(node[out][i]);
						check[node[out][i]] = true;
					}
			}
			System.out.println("#"+tc+" "+res);
		}
	}
}
import java.util.*;
import java.io.*;
/* 분할 정복 - 통과 */
public class BOJ_2630 {
	static int count[] = new int[2];
	static void Count(int N, int len, int[][] paper,  int startY, int startX) {
		if(len==1) { //
			count[paper[startY][startX]]++;
			return;
		}
		int color = paper[startY][startX];
		for(int i=0;i<len;i++)
			for(int j=0;j<len;j++)
				if(paper[startY+i][startX+j]!=color) { 
					Count(N,len/2,paper,startY,startX);
					Count(N,len/2,paper,startY,startX+len/2);
					Count(N,len/2,paper,startY+len/2,startX);
					Count(N,len/2,paper,startY+len/2,startX+len/2);
					return;
				}
		count[color]++;
		return;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int paper[][] = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) paper[i][j] = Integer.parseInt(stz.nextToken());
		}
		Count(N,N,paper,0,0);
		for(int i=0;i<2;i++) System.out.println(count[i]);
	}
}
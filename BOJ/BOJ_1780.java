import java.util.*;
import java.io.*;
/* 종이의 개수 : 분할 정복 */
public class BOJ_1780 {
	static int count[] = new int[3]; // -1, 0, 1 
	static void cutting(int N, int[][] paper, int startY, int startX, int len) {
		if(len==1) { //종이의 길이가 1일 때
			count[paper[startY][startX]+1]++;
			return;
		}
		int color = paper[startY][startX];
		for(int i=0;i<len;i++) 
			for(int j=0;j<len;j++)
				if(paper[startY+i][startX+j]!=color) { //종이의 색이 같지 않을 때
					for(int y=0;y<len;y+=(len/3)) //9등분으로 나누기 : 행
						for(int x=0;x<len;x+=(len/3)) //9등분으로 나누기 : 열
							cutting(N, paper, startY+y, startX+x, len/3); //다음 작은 조각으로 재귀
					return;
				}
		count[color+1]++;
		return;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int paper[][] =  new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) paper[i][j] = Integer.parseInt(stz.nextToken());
		}
		cutting(N, paper, 0, 0, N);
		for(int i=0;i<3;i++) System.out.println(count[i]);
	}
}
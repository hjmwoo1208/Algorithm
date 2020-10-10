import java.util.*;
import java.io.*;
public class BOJ_1018 {
	static int check(int y, int x, String[] board) {
		String match[] = {"WBWBWBWB","BWBWBWBW"};
		int countW =0, countB=0;
		for(int i=0;i<8;i++) 
			for(int j=0;j<8;j++) {
				if(i%2==0) {
					if(board[y+i].charAt(x+j)!=match[0].charAt(j)) countW++; //"WBWBWBWB","BWBWBWBW" 진행
					if(board[y+i].charAt(x+j)!=match[1].charAt(j)) countB++; //"BWBWBWBW","WBWBWBWB" 진행
				}else if(i%2==1) {
					if(board[y+i].charAt(x+j)!=match[1].charAt(j)) countW++; //"WBWBWBWB","BWBWBWBW" 진행
					if(board[y+i].charAt(x+j)!=match[0].charAt(j)) countB++; //"BWBWBWBW","WBWBWBWB" 진행
				}
			}
		return (countW<countB)?countW:countB;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		String board[] = new String[N];
		for(int i=0;i<N;i++) board[i] = br.readLine();
		int min = Integer.MAX_VALUE;
		for(int i=0;i<=N-8;i++)
			for(int j=0;j<=M-8;j++) {
				int res = check(i,j,board);
				min = res<min?res:min;
			}
		System.out.println(min);
	}
}
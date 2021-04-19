import java.util.*;
import java.io.*;
public class BOJ_12100 {
	static int MAX = 0;
	static void DFS(int cnt, int N, int board[][]) {
		if(cnt == 5) {
			for(int i=0;i<N;i++)
				for(int j=0;j<N;j++) MAX = MAX < board[i][j]? board[i][j] : MAX;
			return;
		}
		//왼
		int tmpBoard[][] = new int[N][N];
		for(int i=0;i<N;i++) {
			int save[] = new int[N], index = 0;
			boolean check[] = new boolean[N];
			for(int j=0;j<N;j++) {
				if(board[i][j]>0) {
					if(index>0 && board[i][j] == save[index-1] && !check[index-1]) {
						save[index-1] = save[index-1]*2;
						check[index-1]=true;
					}
					else {
						save[index]=board[i][j];
						index++;
					}
				}
			}
			System.arraycopy(save, 0, tmpBoard[i], 0, index);
		}
		DFS(cnt+1,N,tmpBoard);
		//오
		tmpBoard = new int[N][N];
		for(int i=0;i<N;i++) {
			int save[] = new int[N], index = 0;
			boolean check[] = new boolean [N];
			for(int j=N-1;j>-1;j--) {
				if(board[i][j]>0) {
					if(index>0 && board[i][j] == save[index-1] && !check[index-1]) {
						save[index-1] = save[index-1]*2;
						check[index-1]=true;
					}
					else {
						save[index]=board[i][j];
						index++;
					}
				}
			}
			for(int j=0;j<index;j++) tmpBoard[i][N-1-j] = save[j];
		}
		DFS(cnt+1,N,tmpBoard);
		//위
		tmpBoard = new int[N][N];
		for(int i=0;i<N;i++) {
			int save[] = new int[N], index = 0;
			boolean check[] = new boolean[N];
			for(int j=0;j<N;j++) {
				if(board[j][i]>0) {
					if(index>0 && board[j][i]== save[index-1] && !check[index-1]) {
						save[index-1] = save[index-1]*2;
						check[index-1]=true;
					}
					else {
						save[index]=board[j][i];
						index++;
					}
				}
			}
			for(int j=0;j<index;j++) tmpBoard[j][i] = save[j];
		}
		DFS(cnt+1,N,tmpBoard);
		//아래
		tmpBoard = new int[N][N];
		for(int i=0;i<N;i++) {
			int save[] = new int[N], index = 0;
			boolean check[] = new boolean [N];
			for(int j=N-1;j>-1;j--) {
				if(board[j][i]>0) {
					if(index>0 && board[j][i] == save[index-1] && !check[index-1]) {
						save[index-1] = save[index-1]*2;
						check[index-1]=true;
					}
					else {
						save[index]=board[j][i];
						index++;
					}
				}
			}
			for(int j=0;j<index;j++) tmpBoard[N-1-j][i] = save[j];
		}
		DFS(cnt+1,N,tmpBoard);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int board[][] = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) board[i][j] = Integer.parseInt(stz.nextToken());
		}
		DFS(0,N,board);
		System.out.println(MAX);
	}
}
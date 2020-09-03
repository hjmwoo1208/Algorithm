import java.util.*;
import java.io.*;
public class BOJ_14889 {
	static int MIN = Integer.MAX_VALUE;
	static void go(int N, int index, int start, int[][] S, int[] setArr) {
		if(index==N/2) {
			List<Integer> A = new ArrayList<Integer>(); //조합으로 선택된 팀
			List<Integer> B =  new ArrayList<Integer>(); //조합에서 배제된 팀
			for(int i : setArr) A.add(i); //조합에 선택된 팀 contains사용을 위해서 list에 추가
			for(int i=0;i<N;i++)
				if(A.contains(i)==false) B.add(i); //조합에서 배제된 팀을 B list에 추가
			int sumA = 0, sumB = 0;
			for(int i=0;i<N/2;i++) //능력치 계산
				for(int j=0;j<N/2;j++) 
					if(i!=j) {
						sumA += S[A.get(i)][A.get(j)];
						sumB += S[B.get(i)][B.get(j)];
					}
			MIN = Math.min(MIN, Math.abs(sumA - sumB)); //능력치 비교
			return;
		}
		for(int i=start;i<=N-(N/2)+index;i++) {
			setArr[index] = i;
			go(N,index+1,i+1,S,setArr);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MIN = Integer.MAX_VALUE;
		int N = Integer.parseInt(br.readLine());
		int[][] S = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) S[i][j] = Integer.parseInt(stz.nextToken());
		}
		go(N,0,0,S,new int[N/2]);
		System.out.println(MIN);
	}
}

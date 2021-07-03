import java.util.*;
import java.io.*;
/* 최대상금 - 성공*/
public class SWEA_1244 {
	static int MAX = 0;
	static Set<Integer> set[]; //각 교환횟수에서 발생한 숫자들 저장 -> 가지치기용
	static void DFS(char num[], int cnt, int N) {
		if(N==cnt) {
			int price = Integer.parseInt(new String(num));
			MAX = MAX<price? price : MAX;
			return; 
		}
		int len = num.length;
		for(int s=0;s<len-1;s++)
			for(int e=s+1;e<len;e++) {
				char tmp = num[s];
				num[s] = num[e];
				num[e] = tmp;
				int price = Integer.parseInt(new String(num));
				if(set[cnt].contains(price)) { //가지치기 : 현재 교환 횟수에서 나왔던 숫자라면 
					num[e] = num[s];
					num[s] = tmp;
					continue;
				}
				set[cnt].add(price);
				DFS(num,cnt+1,N);
				num[e] = num[s];
				num[s] = tmp;
			}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T;tc++) {
			MAX = 0;			
			StringTokenizer stz = new StringTokenizer(br.readLine());
			char num[] = stz.nextToken().toCharArray();
			int N = Integer.parseInt(stz.nextToken());
			set = new HashSet[N+1];
			for(int i=0;i<N;i++) set[i] = new HashSet<>();
			DFS(num,0,N);
			System.out.println("#"+tc+" "+MAX);
		}
	}
}
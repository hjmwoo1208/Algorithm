import java.util.*;
import java.io.*;
/* 되돌리기 - 성공 
 * **일반적인 구현 문제** */
public class BOJ_1360 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int time[] = new int[N+1];
		StringBuilder str[] = new StringBuilder[N+1];
		str[0] = new StringBuilder();
		for(int i=1;i<=N;i++) { 
			str[i] = new StringBuilder();
			StringTokenizer stz = new StringTokenizer(br.readLine());
			String mod = stz.nextToken();
			if("type".equals(mod)) {
				str[i].append(str[i-1]).append(stz.nextToken());
				time[i] = Integer.parseInt(stz.nextToken());
			}else { //undo
				int t = Integer.parseInt(stz.nextToken());
				time[i] = Integer.parseInt(stz.nextToken());
				int undoT = time[i] -t;
				if(undoT>1) {
					for(int j=i-1;j>0;j--) {
						if(time[j]==undoT) {//같을 땐 이전 결과 가져오기 -> 문제 예제 1번
							str[i].append(str[j-1]);
							break;
						}
						if(time[j]<undoT) { //저장한 값 이전일 때는 그 시간대의 값을 가져오기
							str[i].append(str[j]);
							break;
						}
					}//for	
				}
			}
		}
		System.out.println(str[N]);
	}
}
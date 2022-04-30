import java.util.*;
/**
 * [백준] 3678번 - 카탄의 개척자
 * @author WOOHJ
 * - 시뮬레이션
 * - 메모이제이션
 */
public class BOJ_3678 {
	static void makeConnecting(List<Integer> connect[], int i) {
		connect[i].add(i+1);
		if(i+1 <= 10000) connect[i+1].add(i);
		
		int start = connect[i-1].get(5);
		int end = start + (6 - connect[i].size());
		for(int c=start;c<end;c++) {
			connect[i].add(c);
			if(c <= 10000) connect[c].add(i);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		// 타일별 연결된 타일 정보
		List<Integer> connect[] = new ArrayList[10002];
		for(int i=1;i<10002;i++) connect[i] = new ArrayList<Integer>();
		// 타일의 자원
		int NUM[] = new int[10002];
		// 게임판의 각 자원의 갯수
		int COUNT[] = new int[6];
		
		// 중앙점 1일 때 연결
		for(int i=2;i<=7;i++) {
			connect[1].add(i);
			connect[i].add(1);
		}
		for(int i=1;i<=5;i++) {
			NUM[i] = i;
			COUNT[i]++;
		}
		// 2~5까지 연결 -> 이미 타일의 자원이 배정되어 있음
		for(int i=2;i<=5;i++) makeConnecting(connect, i);
		int max = 5; // 현재까지 저장된 순서
		for(int tc=0;tc<TC;tc++) {
			int n = sc.nextInt();
			if(n<=max) System.out.println(NUM[n]);
			else {
				int start = max+1;
				for(int turn=start;turn<=n;turn++) {
					// 자원 찾기
					boolean check[] = new boolean[6];
					for(int con : connect[turn]) 
						if(con < turn) check[NUM[con]] = true;
					List<Integer> candidate = new ArrayList<Integer>();
					for(int i=1;i<6;i++)
						if(!check[i]) candidate.add(i);
					if(candidate.size()==1) NUM[turn] = candidate.get(0);
					else {
						// 자원이 적은 
						int min = COUNT[candidate.get(0)];
						for(int can : candidate)
							if(min > COUNT[can]) min = COUNT[can];
						List<Integer> candidate2 = new ArrayList<Integer>();
						for(int can : candidate)
							if(COUNT[can] == min) candidate2.add(can);
						// 게임판에서 적은 자원이거나 가능한 자원중 숫자가 작은 자원
						NUM[turn] = candidate2.get(0);
					}
					// 보드 자원 업데이트
					COUNT[NUM[turn]]++;
					// 타일 연결
					makeConnecting(connect, turn);
				}
				max = Math.max(n, max);
				System.out.println(NUM[n]);
			}
		}
	}
}
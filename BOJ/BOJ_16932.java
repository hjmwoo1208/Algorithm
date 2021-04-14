import java.util.*;
import java.io.*;
/* 모양 만들기 - 성공
 * <주의>
 * !!! 시간초과 주의 !!
 * !!! 무조건 완탐은 안됨 !!!
 * 
 * <풀이 방식>
 * 1. 숫자를 바꾸기 전에 기본 배열에서 모양별로 번호 매기기고 각 모양별 사이즈 구하기 + 현재 가장 큰 모양의 크기 구하기
 * 2. 0자리에서 상하좌우 인접한 그룹을 구해서 각 그룹의 크기를 더한다
 * 3. 2에서 구한 크기 +1을 한다(0->1로 바뀐 크기 추가) 
 * 4. 변환된 크기와 MAX값을 비교한다.
 * */
public class BOJ_16932 {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());

		List<int[]> zero = new ArrayList<>();
		int map[][] = new int[N][M];
		for(int i=0;i<N;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(stz.nextToken());
				if(map[i][j] == 0) zero.add(new int[] {i,j});
			}
		}	
		int MAX = 0, py[] = {-1,0,1,0}, px[] = {0,1,0,-1}, groupNum = 2; // 그룹 번호는 2부터 시작. 배열에 0,1이 존재하므로
		List<Integer> groupSize = new ArrayList<>(); //각 모양별 사이즈 저장, 그룹 변호 2 -> index = 0

		for(int i=0;i<N;i++) 
			for(int j=0;j<M;j++)
				if(map[i][j]==1) {
					Queue<int[]> q = new LinkedList<int[]>();
					q.offer(new int[] {i,j});
					map[i][j] = groupNum;
					int cnt = 1;
					while(!q.isEmpty()) {
						int out[] = q.poll();
						for(int p=0;p<4;p++) {
							int y = out[0] + py[p];
							int x = out[1] + px[p];
							if(y<0 || x<0|| y>=N || x>= M || map[y][x] == 0 || map[y][x] > 1 ) continue; //map[y][x]의 값이 1보다 크면 이미 모양에 포함된 자리
							q.offer(new int[] {y,x});
							map[y][x] = groupNum;
							cnt++;
						}
					}
					groupSize.add(cnt);
					if(cnt>MAX) MAX = cnt;
					groupNum++;
				}	
		for(int[] z : zero) {
			Set<Integer> set = new HashSet<>(); //같은 그룹에 둘러싸여있을 경우를 고려
			for(int p=0;p<4;p++) {
				int y = z[0] + py[p];
				int x = z[1] + px[p];
				if(y<0 || x<0 || y>=N || x>=M || map[y][x] == 0) continue;
				set.add(map[y][x]);
			}
			int sum = 1; // 0 -> 1로 바뀐 자리의 크기도 포함
			for(int num : set) sum += groupSize.get(num-2); //주변 모양의 사이즈를 더한다.
			if(sum>MAX) MAX = sum; //현재의 최댓값과 비교
		}
		System.out.println(MAX);
	}
}
import java.io.*;
import java.util.*;
/* 벽 부수고 이동하기 4 - 성공 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * - 풀이 방식
 * 		* 먼저, 0을 기준으로 BFS하여 그룹화한다.
 * 		* 0 그룹화를 바탕으로 1을 기준으로 상하좌우 그룹의 사이즈를 더해준다. 
 * - 시간초과 이유
 * 		* 각 칸에서 프린트를 해주었다. ->  StringBulider로 문자열을 만들어 한행씩 출력하여 시간을 줄인다.
 * 		* 처음에 BFS할 때마다 각 칸의 위치를 저장했는데 그럴 필요없이 바로 그룹번호를 마킹하면서 시간을 줄인다.
 *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class BOJ_16946 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int M = Integer.parseInt(stz.nextToken());
		String map[] = new String[N];
		for(int i=0;i<N;i++) map[i] = br.readLine();
		
		boolean visited[][] = new boolean[N][M]; // 방문 여부
		int groupNumber[][] = new int[N][M]; // 0 그룹들의 각 그룹 번호 마킹
		int gNum = 1; // 0 그룹 번호는 1부터 시작
		int py[] = {-1,0,1,0};
		int px[] = {0,1,0,-1};
		Map<Integer,Integer> groupCount = new HashMap<>(); //0 그룹별 그룹 사이즈를 저장
		for(int i=0;i<N;i++) 
			for(int j=0;j<M;j++) 
				if(!visited[i][j] && map[i].charAt(j) == '0') {
					Queue<int[]> q = new LinkedList<>();
					q.offer(new int[] {i,j});
					visited[i][j] = true;
					groupNumber[i][j] = gNum; // 시작점에 해당 그룹 번호 마킹
					int size = 1; //그룹의 사이즈는 현재 위치를 시작이므로 1부터 시작
					while(!(q.isEmpty())) {
						int[] out = q.poll();
						for(int p=0;p<4;p++) {
							int y = out[0] + py[p];
							int x = out[1] + px[p];
							if(y<0|| x<0||y>=N||x>=M || visited[y][x] || map[y].charAt(x)=='1') continue;
							q.offer(new int[] {y,x});
							visited[y][x] = true;
							groupNumber[y][x] = gNum; // 그룹번호 마킹
							size++; //현재 0그룹 사이즈 증가
						}
					}
					groupCount.put(gNum, size); //  map에 그룹 번호, 사이즈 저장
					gNum++; //그룹 번호 증가
				}
		for(int i=0;i<N;i++) {
			StringBuilder sb = new StringBuilder(); // 시간초과 이유 : System.out.print()로 한칸한칸 출력 ->   문자열 만들어서 한번에 출력해야함
			for(int j=0;j<M;j++) {
				if(map[i].charAt(j)=='1') { // 1일 때
					Set<Integer> group = new HashSet(); 
					for(int p=0;p<4;p++) {
						int y = i + py[p];
						int x = j + px[p];
						if(y<0|| x<0||y>=N||x>=M || map[y].charAt(x)=='1') continue;
						group.add(groupNumber[y][x]);
					}
					int sum = 0;
					for(int idx : group) sum += groupCount.get(idx);
					sb.append((sum+1)%10);
				}else sb.append(0);
			}
			System.out.println(sb);
		}	
	}
}
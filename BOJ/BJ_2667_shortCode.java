import java.util.*;
import java.io.*;
public class BJ_2667_shortCode {
	static int[] py = {-1,0,1,0}; //상 우 하 좌
	static int[] px = {0,1,0,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] map = new String[N];
		for(int i=0;i<N;i++)
			map[i] = br.readLine(); //---입력끝
		boolean[][] check = new boolean[N][N]; //방문체크
		Queue<int[]> q = new LinkedList<int[]>();//BFS용 queue
		List<Integer> list = new LinkedList<Integer>(); //단지 내 세대 수 저장
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				if(map[i].charAt(j) == '1' && check[i][j] == false ) { //방문하지 않은(단지번호가 붙지 않은) 집이면
					q.offer(new int[] {i,j}); //queue에 추가
					check[i][j]=true; //방문 체크
					int cnt=1; //세대 수 카운트
					while(!(q.isEmpty())) {
						int[] out = q.poll();
						for(int p=0;p<4;p++) {
							int y = out[0] +py[p];
							int x = out[1] +px[p];
							if(y<0 || x<0 ||y>=N ||x>=N) continue; //경계벗어남
							if(map[y].charAt(x)=='1' && check[y][x] == false) { //집이면
								q.offer(new int[] {y,x});
								check[y][x]=true;
								cnt++;
							}
						}
					}
					list.add(cnt); //단지내 세대수 저장
				}
		Collections.sort(list); //오름차순 sort
		System.out.println(list.size()); //단지수
		for(int i=0;i<list.size();i++) System.out.println(list.get(i)); //각 단지내 세대수 출력
	}//main
}
import java.util.*;

public class BJ_2606_2 {

	public static void main(String[] args) {
		int C; //컴퓨터 갯수
		int L; // 연결된 선
		boolean map[][] = new boolean [101][101];
		boolean visit[] = new boolean[101]; //스택에 들어갔었는지 확인 및 감염 확인
		int cnt = 0;
		
		Arrays.fill(visit, false);
		
		Scanner sc = new Scanner(System.in);
		
		C = Integer.parseInt(sc.nextLine());
		L = Integer.parseInt(sc.nextLine());
		
		StringTokenizer stz;
		
		int n,m; //temp
		for(int i=0;i<L;i++)
		{
			stz = new StringTokenizer(sc.nextLine()," ");
			n = Integer.parseInt(stz.nextToken());
			m = Integer.parseInt(stz.nextToken());
			
			map[n][m] = true; //연결됐으면 true
			map[m][n] = true;
		}
		//--------------------------입력 끝----------------------------------
		Stack<Integer> stk = new Stack<Integer>();
		
		n = 1; //첫번째 컴퓨터
		stk.push(n);
		visit[n] = true; // 첫번째 컴터 방문해서
		
		while(!(stk.isEmpty()))
		{
			n = stk.pop(); //하나 꺼내서 
			for(int v=1;v<=C;v++)
				if(map[n][v] ==true && visit[v] == false) //연결된 컴퓨터이면서 방문하지 않았던 컴퓨터
				{
					stk.push(v); //스택에 넣음
					visit[v] = true; //방문 표시
				}
		}
		
		for(int i=2;i<C+1;i++) //첫번째 컴퓨터 빼고 확인
			if(visit[i]==true)
				cnt++;
		
		System.out.println(cnt);

	}
}

import java.util.*;

public class BJ_2606_2 {

	public static void main(String[] args) {
		int C; //��ǻ�� ����
		int L; // ����� ��
		boolean map[][] = new boolean [101][101];
		boolean visit[] = new boolean[101]; //���ÿ� �������� Ȯ�� �� ���� Ȯ��
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
			
			map[n][m] = true; //��������� true
			map[m][n] = true;
		}
		//--------------------------�Է� ��----------------------------------
		Stack<Integer> stk = new Stack<Integer>();
		
		n = 1; //ù��° ��ǻ��
		stk.push(n);
		visit[n] = true; // ù��° ���� �湮�ؼ�
		
		while(!(stk.isEmpty()))
		{
			n = stk.pop(); //�ϳ� ������ 
			for(int v=1;v<=C;v++)
				if(map[n][v] ==true && visit[v] == false) //����� ��ǻ���̸鼭 �湮���� �ʾҴ� ��ǻ��
				{
					stk.push(v); //���ÿ� ����
					visit[v] = true; //�湮 ǥ��
				}
		}
		
		for(int i=2;i<C+1;i++) //ù��° ��ǻ�� ���� Ȯ��
			if(visit[i]==true)
				cnt++;
		
		System.out.println(cnt);

	}
}

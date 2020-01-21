import java.util.*;
//백준 토마토

public class BJ_7576 {

	public static void main(String[] args) {
		int box[][] = new int[1001][1001];
		int N, M;
		int day = 0; // day = 다 익는 날
		int zerocnt = 0; // 그냥 토마토 갯수
		int onecnt = 0; // 썩은 토마토 갯수
		// int qcnt =0;
		int tomato[][] = new int[1001][2]; // 썩은 토마토 좌표
		Queue<int[]> q = new LinkedList<int[]>();

		Scanner sc = new Scanner(System.in);

		StringTokenizer stz;

		stz = new StringTokenizer(sc.nextLine(), " ");

		M = Integer.parseInt(stz.nextToken());
		N = Integer.parseInt(stz.nextToken());

		for (int i = 0; i < N; i++) {
			stz = new StringTokenizer(sc.nextLine(), " ");
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(stz.nextToken());
				if (box[i][j] == 1) {
					int temp[] = { i, j };
					q.offer(temp);
					onecnt++;
				} else if (box[i][j] == 0)
					zerocnt++;
			}
		}

		// ----------------------------------------------------
		int dn[] = { -1, 0, 1, 0 };
		int dm[] = { 0, 1, 0, -1 };

		int n, m;
		// int temp[];
		// int qtemp[] = new int [2];
		int tomatocnt = 0;
		int qcnt = 0;
		boolean zeroflag = false;

		// int temp[] = {0,0};
		int temp[] = new int[2];
		int qtemp[];

		if (zerocnt != 0) {
			while (!(q.isEmpty())) {
				qcnt = q.size();
				day++;
				for (int i = 0; i < qcnt; i++) {
					// temp = new int[2];
					temp = (int[]) q.poll();

					for (int a = 0; a < 4; a++) {
						if ((temp[0] + dn[a]) >= 0 && (temp[0] + dn[a]) < N && (temp[1] + dm[a]) >= 0
								&& (temp[1] + dm[a]) < M)
							if (box[temp[0] + dn[a]][temp[1] + dm[a]] == 0) 
							{
								qtemp = new int[2];
								qtemp[0] = temp[0] + dn[a];
								qtemp[1] = temp[1] + dm[a];
								q.offer(qtemp);
								box[temp[0] + dn[a]][temp[1] + dm[a]] = 1;
								tomatocnt++;
							}
					}
				}
			} // while
		}
		if (tomatocnt != zerocnt)
			System.out.println(-1);
		else if (zerocnt == 0)
			System.out.println(0);
		else
			System.out.println(day - 1);

	}// main
}

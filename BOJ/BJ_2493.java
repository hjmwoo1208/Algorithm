import java.io.*;
import java.util.*;
public class BJ_2493 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer stz = new StringTokenizer(br.readLine());
		Stack<int[]> stk = new Stack<int[]>(); //탑 번호, 높이
		for(int i=1;i<=N;i++)
		{
			int top = Integer.parseInt(stz.nextToken());
			while(!(stk.isEmpty()))
			{
				if(stk.peek()[1]>top) {
					System.out.print(stk.peek()[0]+" ");
					stk.push(new int[] {i,top});
					break;
				}
				else stk.pop();
			}
			if(stk.isEmpty())
			{
				System.out.print("0 ");
				stk.push(new int[] {i,top}); //현재 내가 제일 높음
			}
		}
	}//main
}
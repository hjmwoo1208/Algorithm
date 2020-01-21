import java.util.*;

// 백준 2309 : 일곱 난쟁이 
// 9명중 7명 키의 합이 100인 경우 
// 완전 탐색 기초?

public class BJ_2309 {

	public static void main(String[] args) {
		int tall[] = new int[10];
		int sortTall[] = new int[7];
		int sum =0;
		int total = 9;
		int cal =0;
		boolean flag = false;
		int l1 = 0;
		int l2 = 0;
		Scanner sc = new Scanner(System.in);
		
		for(int i=0;i<9;i++)
		{
			tall[i] = Integer.parseInt(sc.nextLine());
			sum += tall[i];
		}
		
		for(int a = 0; a< (total-1);a++ )
		{
			for(int b = (a+1);b<total;b++)
				if((sum - tall[a] - tall[b])==100)
				{
					int chk =0;
					for(int i=0;i<total;i++)
						if((i != a) && (i != b)) 
						{
							sortTall[chk] = tall[i];
							chk++;
						}
					flag = true;
					break;
				}
			if(flag == true)
				break;
		}
		
		
		int temp;
		//버블 정렬
		for(int a = (7-1);a>0;a--)
		{
			for(int b=0;b<a;b++)
				if(sortTall[b]>sortTall[b+1])
				{
					temp = sortTall[b+1];
					sortTall[b+1] = sortTall[b];
					sortTall[b] =  temp;
				}
		}
		
		for(int j=0;j<7;j++)
			System.out.println(sortTall[j]);
		
	}//main

}

import java.util.*;
/* 성공
 * 쉽게 for문으로 돌려서 끝내려하면 메모리 초과가 난다.
 * 자릿수 계산을 해서 계산을 최대한 적게 해야한다.
 */
public class BJ_1790 {
	public static void main (String[] args) {
		Scanner sc =new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int result = -1;
		if( K <= 9)
		{
			if(N<K)
				System.out.println(result);
			else
				System.out.println(K);
		}
		else//2자리수이상
		{
			int len=0 , tmpK=0;
			//double 범위 15자리까지 가능
			double idx=9; //len = K의 자릿수, tmpK = 이전 자릿수제거
			for(int i=2;i<=10;i++) //몇자리수 숫자인지 찾기
			{
				double tmp = 9 * Math.pow(10, i-1)*i; // *i 로 해야 되는데 *2로 해서 틀림 (두 자리수로 테스트하다가 일반화시킴)
				if( K<= (tmp+idx))
				{
					len = i;
					tmpK = (int) (K -idx); //int 캐스팅 해야지 뒤에 깔끔하게 떨어짐
					break;
				}
				else
					idx += tmp;
			}
			
			String str ="";
			if(tmpK%len == 0)
			{
				int start = (int) Math.pow(10, len-1) + (tmpK/len) -1;  //int 캐스팅 해야지 뒤에 깔끔하게 떨어짐
				if(start<= N)
				{
					str = Integer.toString(start);
					System.out.println(str.charAt(str.length()-1)-'0');
				}
				else
					System.out.println(-1);
			}
			else
			{
				int start = (int) Math.pow(10, len-1) + (tmpK/len);
				if(start <= N)
				{	
					str = Integer.toString(start);
					System.out.println(str.charAt((tmpK%len)-1)-'0');
				}
				else
					System.out.println(-1);
			}
			
		}
		
	}//main
}
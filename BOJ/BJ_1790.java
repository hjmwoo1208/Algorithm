import java.util.*;
/* ����
 * ���� for������ ������ �������ϸ� �޸� �ʰ��� ����.
 * �ڸ��� ����� �ؼ� ����� �ִ��� ���� �ؾ��Ѵ�.
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
		else//2�ڸ����̻�
		{
			int len=0 , tmpK=0;
			//double ���� 15�ڸ����� ����
			double idx=9; //len = K�� �ڸ���, tmpK = ���� �ڸ�������
			for(int i=2;i<=10;i++) //���ڸ��� �������� ã��
			{
				double tmp = 9 * Math.pow(10, i-1)*i; // *i �� �ؾ� �Ǵµ� *2�� �ؼ� Ʋ�� (�� �ڸ����� �׽�Ʈ�ϴٰ� �Ϲ�ȭ��Ŵ)
				if( K<= (tmp+idx))
				{
					len = i;
					tmpK = (int) (K -idx); //int ĳ���� �ؾ��� �ڿ� ����ϰ� ������
					break;
				}
				else
					idx += tmp;
			}
			
			String str ="";
			if(tmpK%len == 0)
			{
				int start = (int) Math.pow(10, len-1) + (tmpK/len) -1;  //int ĳ���� �ؾ��� �ڿ� ����ϰ� ������
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
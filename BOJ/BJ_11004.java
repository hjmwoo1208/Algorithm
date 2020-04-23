import java.util.*;
import java.io.*;
/* ����
 * 1. Scanner ����ϸ� �ð� �ʰ� -> BufferedReader ���
 * 2. int[] �迭�� sort ����ϸ� --> 88%���� �ð� �ʰ�
 *     int[]�迭 ��� List ����Ͽ����� ����.
 */
public class BJ_11004 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(stz.nextToken());
		int K = Integer.parseInt(stz.nextToken());
		
		stz = new StringTokenizer(br.readLine());
		List<Integer> A = new ArrayList<Integer>();
		for(int i =0;i<N;i++)
			A.add(Integer.parseInt(stz.nextToken()));
        
		Collections.sort(A);
		System.out.println(A.get(K-1));
	}
}
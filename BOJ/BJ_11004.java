import java.util.*;
import java.io.*;
/* 성공
 * 1. Scanner 사용하면 시간 초과 -> BufferedReader 사용
 * 2. int[] 배열의 sort 사용하면 --> 88%에서 시간 초과
 *     int[]배열 대신 List 사용하였더니 정답.
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
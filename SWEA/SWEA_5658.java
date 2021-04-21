import java.util.*;
import java.io.*;
/* [모의 SW 역량테스트] 보물상자 비밀번호 - 성공
 * <풀이방식>
 * 포인터를 옮겨가면서 문자열을 만든다!
 * set에 10진수로 바꿔서 저장한다.
 * sort하고 다시 reverse해서 내림차순으로 list를 만든다*/
public class SWEA_5658 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(stz.nextToken());
			int K = Integer.parseInt(stz.nextToken());
			int side = N/4;
			String str = br.readLine();
			Set<Long> password = new HashSet<>();
			for(int r=0;r<side;r++) {
				for(int n=0;n<4;n++) {
					StringBuilder sb = new StringBuilder();
					for(int i=0;i<side;i++) {
						int index = (side*n + r + i)%N;
						sb.append(str.charAt(index));
					}
					password.add(Long.parseLong(sb.toString(), 16));
				}
			}
			List<Long> list = new ArrayList<>(password);
			Collections.sort(list);
			Collections.reverse(list);
			System.out.println("#"+tc+" "+list.get(K-1));
		}
	}
}
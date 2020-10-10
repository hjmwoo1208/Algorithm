import java.io.*;
import java.util.*;
public class BOJ_1525 {
	static class puzzle {
		int cnt, zeroIndex;
		String str;
		public puzzle(int cnt,int zeroIndex, String str) {
			this.cnt = cnt;
			this.zeroIndex = zeroIndex;
			this.str = str;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<3;i++) {
			StringTokenizer stz = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) sb.append(stz.nextToken()); //한줄로 만들어준다. 
		}
		int count =-1; //이동이 불가능할 경우 -1출력
		String matching = "123456780"; //정렬된 문자열
		Queue<puzzle> q = new LinkedList<puzzle>();
		Set<String> visited = new HashSet<String>(); //중복으로 이동하지 안도록 visited 처리
		for(int i=0;i<9;i++) 
			if(sb.charAt(i)=='0') {
				q.offer(new puzzle(0,i, sb.toString())); //0의 위치를 찾아준다.
				break; }
		visited.add(sb.toString()); //입력받은 모양을 넣어준다.
		int pz[]  = {-1,1,-3,3};
		while(!(q.isEmpty())) {
			puzzle out = q.poll();
			if(matching.equals(out.str)) { // 처음부터 정렬되있던 상태까지 포함하기 위해서 꺼내자마자 검사
				count = out.cnt;
				break; //queue는 순서대로 출력되므로 처음 나온 일치문자의 이동 횟수가 최솟값
			}
			for(int p=0;p<4;p++) {
				int nextIndex = out.zeroIndex+pz[p]; //빈곳이랑 바꿀 위치
				if(nextIndex<0 || nextIndex >8) continue; //범위에 넘어가지 않도록
				if(p<2 && nextIndex/3 != out.zeroIndex/3) continue; //같은 행일때만 움직일 수 있도록
				String tmpStr = new String(out.str); //임시 문자열
				char tmp = tmpStr.charAt(nextIndex); //빈 자리(0)과 바꿀 자리의 문자
				tmpStr = tmpStr.replace(tmp, '9'); //replace는 모두 바꿔버리므로 우선 0과 바꿀 문자를 9로 바꾸고
				tmpStr = tmpStr.replace('0', tmp); // 빈곳을 바꿀 문자로 바꾼다.
				tmpStr = tmpStr.replace('9', '0'); // 9로 바꾼 자리를 다시 빈곳(0)로 만들어준다.
				if(visited.contains(tmpStr)) continue; //이미 시도해본 배치라면 queue에 넣지 않는다.
				visited.add(tmpStr); //처음 시도하는 배치일 때
				q.offer(new puzzle(out.cnt+1, tmpStr.indexOf("0"), tmpStr));
			}
		}
		System.out.println(count);
	}//main
}
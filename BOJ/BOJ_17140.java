import java.util.*;
import java.io.*;
public class BOJ_17140 {
	static class Item implements Comparable<Item>{
		int num, cnt;
		public Item(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Item o) {
			if(this.cnt==o.cnt) return this.num-o.num;
			return this.cnt-o.cnt;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(stz.nextToken());
		int c = Integer.parseInt(stz.nextToken());
		int k = Integer.parseInt(stz.nextToken());
		int A[][] = new int[101][101],count[] = new int[101], time=0, R=3,C=3; //배열안의 수를 카운트하는  count (1~100)
		for(int i=1;i<4;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=1;j<4;j++) A[i][j] = Integer.parseInt(stz.nextToken());
		}
		while(time<101) {
			if(A[r][c] == k) break;
			if(R<C) { //C연산
				int thisR = 0;
				for(int j=1;j<C+1;j++) {
					Set<Integer> set = new HashSet<Integer>();
					for(int i=1;i<R+1;i++) {
						if(A[i][j] == 0) continue;
						set.add(A[i][j]);
						count[A[i][j]]++;
					}
					List<Item> list= new ArrayList<Item>();
					Iterator<Integer> it = set.iterator();
					while(it.hasNext()) {
						int num = it.next();
						list.add(new Item(num, count[num]));
						count[num]= 0;
 					}
					Collections.sort(list);
					A[0][j] = (list.size()>50)?100:list.size()*2;
					if(thisR<A[0][j]) thisR = A[0][j];
					for(int a=0;a<A[0][j]/2;a++) {
						A[1+(2*a)][j] = list.get(a).num;
						A[2+(2*a)][j] = list.get(a).cnt;
					}
					if(R>A[0][j]) 
						for(int zero=A[0][j]+1;zero<=R;zero++) A[zero][j] = 0; //R보다 크면 0으로 채워준다.
				}//c연산
				R = thisR; //최대 R을 구함
			}else { //R연산
				int thisC = 0;
				for(int i=1;i<R+1;i++) {
					Set<Integer> set = new HashSet<Integer>(); //카운트 된 숫자들의 종류를 저장
					for(int j=1;j<C+1;j++) {
						if(A[i][j]==0) continue;
						set.add(A[i][j]);
						count[A[i][j]]++; //갯수를 셈
					}
					List<Item> list = new ArrayList<Item>();
					Iterator<Integer> it = set.iterator();
					while(it.hasNext()) {
						int num=it.next();
						list.add(new Item(num, count[num])); //숫자, 반복횟수
						count[num]=0; //반복횟수 초기화
					}
					Collections.sort(list); //[등장횟수 순 --> 숫자가 커지는 순]으로 정렬
					Arrays.fill(A[i],0); //크기를 맞춰주기 위해서 0으로 초기화해줌
					A[i][0] = (list.size()>50)? 100: list.size()*2;
					if(thisC<A[i][0]) thisC = A[i][0];
					for(int a=0;a<A[i][0]/2;a++) {
						A[i][1+(2*a)] = list.get(a).num;
						A[i][2+(2*a)] = list.get(a).cnt;
					}
				}//for
				C = thisC; //최대 C를 구함
			}//r연산
			time++;
		}
		System.out.println(time>100?-1:time); //100초가 넘으면 -1을 출력한다.
	}//main
}
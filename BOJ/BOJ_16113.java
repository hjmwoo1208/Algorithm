import java.util.*;
import java.io.*;
/* 시그널 - 성공
 * <풀이방식>
 *  - BFS를 통해서 숫자 그룹을 구한다.
 *  - 그룹을  y작은순 -> x작은순으로 정렬한다.
 *  - 그룹의 크기를 통해서 숫자를 분류한다.
 *  - 그룹의 크기가 같으면 크기가 같은 숫자들의 특이한 인덱스를 비교해서 분류한다.
 * */
public class BOJ_16113 {
	static class node implements Comparable<node>{
		int y,x;
		public node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		@Override
		public int compareTo(node n) {
			if(this.y<n.y) return -1;
			else if(this.y>n.y) return 1;
			else {
				if(this.x<n.x) return -1;
				else return 1;
			}
		}
	}
	static char ChangeNumber(List<node> list, int start) {
		if(list.size()==5) return '1';
		if(list.size()==7) return '7';
		if(list.size()==9) return '4';
		if(list.size()==13) return '8';
		if(list.size()==12) {
			if(list.get(4).y==1 && list.get(4).x -start == 2	) {
				if(list.get(7).y==3) return '0';
				else return '9';
			}else return '6';
		}
		if(list.size()==11) {
			if(list.get(3).x-start==0) return '5';
			else {
				if(list.get(7).x-start == 0) return '2';
				else return '3';
			}
		}
		return ' ';
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int strLength = Integer.parseInt(br.readLine());
		String str = br.readLine();
		int signalLength = strLength/5;
		char signal[][] = new char[5][signalLength];
		for(int i=0;i<5;i++) {
			int start = i*signalLength;
			signal[i] = str.substring(start, start+signalLength).toCharArray();
		}
		int py[] = {0,1,0,-1}, px[] = {1,0,-1,0};
		boolean check[][] = new boolean[5][signalLength];
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<signalLength;i++) 
			if(signal[0][i] == '#' && !check[0][i]) {
				List<node> q = new ArrayList<>();
				q.add(new node(0, i));
				check[0][i] = true;
				int pointer = 0;
				while(pointer<q.size()) {
					node out = q.get(pointer);
					pointer++;
					for(int p=0;p<4;p++) {
						int y = out.y +py[p];
						int x = out.x + px[p];
						if(y<0 || x<0 || y>=5 || x>=signalLength || check[y][x] || signal[y][x] =='.') continue;
						q.add(new node(y,x));
						check[y][x] =  true;
					}
				}
				Collections.sort(q);
				sb.append(ChangeNumber(q,i));
			}
		System.out.println(sb);
	}
}
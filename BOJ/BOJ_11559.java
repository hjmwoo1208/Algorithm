import java.util.*;
import java.io.*;

public class BOJ_11559 {
/* Puyo Puyo - 성공
 * Java8 ShortCode 1st!!!!!!!!!!
 * 터트림 -> 중력 -> 터트림 -> 중력 
 * */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char field[][] = new char[12][6];
		for(int i=0;i<12;i++) field[i] = br.readLine().toCharArray();
		int count = 0, py[] = {-1,0,1,0}, px[] = {0,1,0,-1};
		
		while(true) {
			boolean visited[][] = new boolean[12][6], stop = false;
			for(int i=11;i>-1;i--)
				for(int j=0;j<6;j++)
					if(field[i][j] != '.' && !visited[i][j]) {
						ArrayList<int[]> save = new ArrayList<int[]>();
						visited[i][j] = true;
						save.add(new int[] {i,j});
						char color = field[i][j];
						int pointer = 0;
						while(pointer<save.size()) {
							int out[] = save.get(pointer);
							pointer++;
							for(int p=0;p<4;p++) {
								int y = out[0] + py[p];
								int x = out[1] + px[p];
								if(y<0 || x<0 ||y>11 || x>5 || visited[y][x] || field[y][x] != color) continue;
								save.add(new int[] {y,x});
								visited[y][x] = true;
							}
						}
						if(save.size()>=4) {
							stop = true;
							for(int[] out : save) field[out[0]][out[1]] = '.';
						}
					}
			if(!stop) break;
			// 중력
			for(int j=0;j<6;j++) {
				List<Character> list = new ArrayList<Character>();
				for(int i=11;i>-1;i--)
					if(field[i][j] != '.') {
						list.add(field[i][j]);
						field[i][j] = '.';
					}
				int idx = 11;
				for(char c : list) {
					field[idx][j] = c;
					idx--;
				}
			}
			
			count++;
		}
		
		System.out.println(count);
	}
}
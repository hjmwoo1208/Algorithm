import java.util.*;
/*
 * ���� �˰��� : �ִܰŸ�
 * 
 * ��� Ŭ������ �����ߵ� --> 20000*20000 int �迭����� �޸� �ʰ� --> ��Ʈ Ŭ������ Array�� �־ �����ϴ� ���
 * 
 * Compareable �������̽� ��ӹ޾Ƽ� �����ϴ� ���
 * 
 * �¾���
 * http://zoonvivor.tistory.com/99 ����
 * 
 * http://www.gisdeveloper.co.kr/?p=4452
 */
public class BJ_1753_3 {
	//public static int dist[] = new int[20002];
	//��ó: http://minemi.tistory.com/39 [�̳׹̺�α�]
	
	public static class Edge
	{
		int end;
		int value;
		
		Edge(int end, int value)
		{
			this.end = end;
			this.value = value;
		}
	}
	
	public static class distance implements Comparable<distance>
	{

		int index;
		int dist;	
		
		distance(int index, int dist)
		{
			this.index = index;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(distance d) {
			//if (this.getF() > target.getF()) {
			if(this.dist>d.dist) 
			{
	            return 1;
	        } 
			else if (this.dist  < d.dist) 
			{
	            return -1;
	        }
	 
	        return 0;
		}
		/*
		 * ���� ��ü�� �񱳴�󺸴� ������ --> ����
		 * ���� ��ü�� �񱳴���̶� ������ --> 0
		 * ���� ��ü�� �񱳴�󺸴� ũ��    --> ���
		 */
	}
	
	public static void main(String[] args) {
		int V,E,K;
		int d[][];
		int uvw[][];
		boolean distB[];
		int dist[];
		Scanner sc = new Scanner(System.in);
		
		StringTokenizer stz;
		stz = new StringTokenizer(sc.nextLine());
		V = Integer.parseInt(stz.nextToken());
		E = Integer.parseInt(stz.nextToken());
		K = Integer.parseInt(sc.nextLine());
		
		dist = new int[V+2];
		distB = new boolean[V+2];
		
		Arrays.fill(distB, true);
		Arrays.fill(dist, Integer.MAX_VALUE);

		ArrayList<Edge>[] dList = new ArrayList[V + 2];
		for(int i=0;i<=V;i++)
			dList[i] = new ArrayList<Edge>();
		
		int u,v,w;
		boolean flag = true;
		for(int i =0;i<E;i++)
		{
			stz = new StringTokenizer(sc.nextLine());
			dList[Integer.parseInt(stz.nextToken())].add(new Edge(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken())));
		}
		
		dist[K] = 0;
		
		distance tempdist;
		tempdist = new distance(K,dist[K]);
		
		PriorityQueue<distance> q = new PriorityQueue<distance>();
		q.offer(tempdist);
		
		
		int temp;
		distance tempdist2;
		while(!(q.isEmpty()))
		{
			tempdist = q.poll();
			
			if(distB[tempdist.index] == true)
			{
				distB[tempdist.index] = false;
				for(int i = 0;i<dList[tempdist.index].size();i++)
				{
					temp = dist[tempdist.index] + dList[tempdist.index].get(i).value;
					if(temp<dist[dList[tempdist.index].get(i).end])
					{
						dist[dList[tempdist.index].get(i).end] = temp;
						tempdist2 = new distance(dList[tempdist.index].get(i).end, temp);
						q.offer(tempdist2);
					}
					
				}
			}
			
		}//while
		
		for(int i  = 1; i<=V; i++)
		{
			if(distB[i] == true)
				System.out.println("INF");
			else
				System.out.println(dist[i]);
		}
	}//main
	
	
	
}

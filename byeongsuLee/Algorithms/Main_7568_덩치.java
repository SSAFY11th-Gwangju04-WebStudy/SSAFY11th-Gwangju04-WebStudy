package day0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;

public class Main_7568_µ¢Ä¡{
	static class Num{
		int weight ;
		int height;
		int rank;
		@Override
		public String toString() {
			return "Num [weight=" + weight + ", heihgt=" + height + ", rank=" + rank + "]";
		}
		public Num(int weight, int height, int rank) {
			super();
			this.weight = weight;
			this.height = height;
			this.rank = rank;
		}
	
		
	}

	public static void main(String[] args)throws IOException{
		// TODO Auto-generated method stub

		//x = ¸ö¹«°Ô kg
		//y= cm Å°
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		int num =1;

		int mw = Integer.MIN_VALUE;
		int mk = Integer.MIN_VALUE;
		int [][] arr = new int[N][2];
	
		Num [] human = new Num[N]; 
		
		for(int i = 0 ;i< N ;i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int height= Integer.parseInt(st.nextToken());
			human[i] = new Num(weight,height,1);

			
		}
		
		for(int i = 0 ; i <N;i++) {
			for(int j = 0;j<N;j++) {
				if(i==j) continue;
				if(human[i].weight < human[j].weight && human[i].height<human[j].height) {
					human[i].rank++;
				}
			}
		}
		
	
		
		for(int i = 0 ; i<N; i++) {
			System.out.print(human[i].rank);
			if(i==N-1) break;
			System.out.print(" ");
		}
	}

}

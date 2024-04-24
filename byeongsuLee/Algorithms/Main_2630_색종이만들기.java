package day0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2630_색종이만들기 {

	private static int N;
	private static int[][] map;
	private static int bluePaperCnt;
	private static int whitePaperCnt;

	public static void main(String[] args) throws IOException {
		
		init();
		solution();
		print();
	
		

	}

	private static void print() {
		System.out.println(whitePaperCnt);
		System.out.println(bluePaperCnt);
	
	}

	private static void solution() {
		//색종이 나누기
		dividePaper(N,0,0);
	}

	private static void dividePaper(int cnt,int sr,int sc) {
		int value = map[sr][sc];
		if(cnt==1) {
			if(value==1) {
				bluePaperCnt++;
			}else {
				whitePaperCnt++;
			}
			return ;
		}
		
		if(check(sr,sc,cnt,value)) {
			if(value==1) {
				bluePaperCnt++;
			}else {
				whitePaperCnt++;
			}
		}else {
			int half = cnt/2;
			dividePaper(half,sr,sc);
			dividePaper(half,sr+half,sc);
			dividePaper(half,sr,sc+half);
			dividePaper(half,sr+half,sc+half);
		}
		
	}

	private static boolean check(int sr, int sc, int cnt,int value) {
		

		for(int i = sr; i<sr+cnt;i++) {
			for(int j = sc ;j<sc+cnt;j++) {
				if(map[i][j]!=value)
					return false;
			}
		}
		return true;
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		bluePaperCnt = 0;
		whitePaperCnt=0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

}

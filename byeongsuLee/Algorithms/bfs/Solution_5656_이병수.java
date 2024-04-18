package day0330;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656_이병수 {
	static class Location {
		int r,c,p;

		@Override
		public String toString() {
			return "Location [r=" + r + ", c=" + c + ", p=" + p + "]";
		}

		public Location(int r, int c, int p) {
			super();
			this.r = r;
			this.c = c;
			this.p = p;
		}
		
	}
	
	private static int N,C,R;
	private static int[][] map;
	private static int[][] gameMap;
	private static int[] selected;
	private static int[] dr= {-1,1,0,0};
	private static int[] dc= {0,0,-1,1};
	private static int answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			answer = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
 			C = Integer.parseInt(st.nextToken());  
			R =Integer.parseInt(st.nextToken());
			map = new int[R][C];
		
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				
				}
			}
			
			selected= new int[N];
			permutation(0);
			
			
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());

	}

	//구술의 위치를 중복 순열로 뽑고 게임 시작
	private static void permutation(int cnt) {
			if(	cnt==N) {
			
				gameMap= copyMap(map);
				gameStart(gameMap);
				int count = 0 ; 
				for(int i=0; i < R;i++) {
					for (int j = 0 ;j<C;j++) {
						if(gameMap[i][j]>0) {
							count++;
						}
					}
				}
				
				answer= Math.min(count, answer);
				
				
				return ;
			}
			for(int i = 0 ; i<C;i++) {
				
				selected[cnt] =i;
				permutation(cnt+1);
			}
	}

	private static void gameStart(int[][] gameMap) {
		//구슬을 n번던진다.
		for(int game = 0 ;game<N;game++) {

			//selected 된 열에서 맨위 좌표찾기 
			int sc = selected[game];
			int r=-1,c=-1;
			for(int i =0 ; i <R;i++) {
				if(gameMap[i][sc]>0) {
					r=i;
					c=sc;
					break;
				}
			}
			
			//좌표부터 구슬 뿌수기
			//부술구술이 없는경우 continue
			if(r==-1 && c==-1) continue;
			bfs(r,c);
			
			//구슬 내리고 구슬 개수 구하기
			//구슬 내리는 조건 : 해당열을 기준으로 아래의 값이 0일경우 위에 값이 있는지 확인
			//R-1~1까지  Game[i][j]>0보다클때 밑으로내리기 
			for(int j =0 ;j<C;j++) {
				for(int i =R-1 ; i>=1;i--) {
					//0일경우 j는고정 i
					if(gameMap[i][j]==0) {
						int up = i;
						int index= i;
						while(--up>=0) {
							if(gameMap[up][j]>0) {
								index=up;
								break;
							}
						}
						gameMap[i][j]= gameMap[index][j];
						gameMap[index][j]=0;
					}
				}
			}
	
			
			
		}

		
	}

	private static void bfs(int sr, int sc) {	
		
		Queue<Location> q = new ArrayDeque<>();
		q.offer(new Location(sr,sc,gameMap[sr][sc]));
		gameMap[sr][sc]=0;
		
		//벽돌이 깨지는부분을 0으로 만들고 (gameMap[nr][nc] 만큼 주변 벽돌뿌수기
		while(!q.isEmpty()) {
			Location cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int p= cur.p;
			
			for(int d=0; d<4;d++) {
				for(int t=1;t<p;t++) {
					int nr = r+dr[d]*t;
					int nc = c +dc[d]*t;
					
					if(isIn(nr,nc)&&gameMap[nr][nc]>0) {
						q.offer(new Location(nr,nc,gameMap[nr][nc]));
						gameMap[nr][nc]=0;
					}
				}
			}
			
		}
		
	}

	private static boolean isIn(int nr, int nc) {
		// TODO Auto-generated method stub
		return nr>=0 && nr<R && nc>=0 &&nc<C;
	}

	private static int[][] copyMap(int[][] map2) {
		
		int [][]copy = new int[R][C];
		for(int i = 0 ; i<R;i++) {
			copy[i] =	map[i].clone();
		}
		return copy;
	}

}

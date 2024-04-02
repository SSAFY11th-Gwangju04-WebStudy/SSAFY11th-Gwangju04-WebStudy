package ex0402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7576 {
    private static class Tomato{
        int x;
        int y;
        int time;
        public Tomato(int x,int y,int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    private static int n,m;
    private static int[][] arr;
    private static boolean[][] visited;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static Queue<Tomato> que = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j  = 0 ; j < m ; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(arr[i][j] == 1){
                    que.offer(new Tomato(i,j,arr[i][j]));
                    visited[i][j] = true;
                }
            }
        }
        bfs();

        if(result() == -1){
            System.out.println(-1);
        }else{
            System.out.println(result()-1);
        }
    }//main end

    private static void bfs(){
        while(!que.isEmpty()){
            Tomato now = que.poll();

            for(int d = 0; d < 4; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(check(nx,ny) && !visited[nx][ny] && arr[nx][ny] == 0){
                    que.offer(new Tomato(nx,ny,now.time+1));
                    visited[nx][ny] = true;
                    arr[nx][ny] = now.time+1;
                }
            }
        }
    }//bfs end

    private static boolean check(int x,int y){
        return x >= 0 && y >= 0 && x < n && y < m;
    }//check end

    private static int result(){
        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(arr[i][j] == 0){
                    return -1;
                }else{
                    cnt = Math.max(cnt,arr[i][j]);
                }
            }
        }
        return cnt;
    }
}//class end
